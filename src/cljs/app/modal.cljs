(ns app.modal
  (:refer-clojure :exclude [pop!])
  (:require
    [c3kit.apron.corec :as ccc]
    [c3kit.apron.log :as log]
    [c3kit.wire.js :as wjs]
    [app.page :as page]
    [reagent.core :as reagent]
    ))

(def state (page/cursor [:modal]))

(defn install-state! [new-state]
  (log/info "installing modal state: " new-state)
  (reset! state new-state))

(defn install! [section & args]
  (let [state (ccc/->options args)]
    (install-state! (assoc state :modal/subject section))))

(defn push-state! [new-state] (install-state! (assoc new-state :modal/previous @state)))
(defn push! [section & args]
  (let [state (apply hash-map args)]
    (push-state! (assoc state :modal/subject section))))

(defn pop! [] (install-state! (:modal/previous @state)))
(defn back-link []
  (when (:modal/previous @state)
    [:a#-modal-back.plain-text.modal-back {:on-click pop!} [:h6 "Back"]]))

(defn subject
  ([] (:modal/subject @state))
  ([state] (:modal/subject state)))

(defn previous
  ([] (:modal/previous @state))
  ([state] (:modal/previous state)))

(defmulti modal-content :modal/subject)

(defn close! []
  (let [on-close (:on-close @state)]
    (reset! state nil)
    (when on-close (on-close))))

(defn open? [] (some? @state))

(defn- process-modal-keyup [close e]
  (when (wjs/ESC? e)
    (let [target-id (.-id (.-target e))]
      (if (or (= "-modal" target-id) (= "-modal-overlay" target-id))
        (close)
        (if-let [modal (wjs/element-by-id "-modal")]
          (wjs/focus! modal)
          (wjs/focus! (wjs/element-by-id "-modal-overlay")))))))

(defn- focus-modal! []
  (if-let [modal (wjs/element-by-id "-modal")]
    (wjs/focus! modal)
    (when-let [overlay (wjs/element-by-id "-modal-overlay")]
      (wjs/focus! overlay))))

(defn overlay [close]
  (reagent/create-class
    {:display-name           "modal-overlay"
     :component-did-mount    (fn [this]
                               (-> js/document .-body .-classList (.add "no-scroll"))
                               (focus-modal!))
     :component-will-unmount (fn [this]
                               (-> js/document .-body .-classList (.remove "no-scroll")))
     :reagent-render         (fn [close]
                               [:div#-modal-overlay.modal-overlay {:tab-index "0"
                                                                   :on-key-up (partial process-modal-keyup close)
                                                                   :on-click  close}
                                [:span.close-button-x-white]])}))

(defn modal []
  (let [state @state
        ajax-in-progress (:ajax-in-progress @page/state)]
    (when state
      [:div#-modal.modal-background.centered {:tab-index "0"
                                              :on-key-up (partial process-modal-keyup close!)}
       [overlay close!]

       ^{:key (:modal/subject state)} [modal-content state]

       (when ajax-in-progress
         [:div.spinner-overlay                              ;; TODO - MDM: class missing
          [:div.spinner-medium-grey]])])))


(defmethod modal-content :default [state]
  (log/warn "Unimplemented modal state:")
  (log/warn state)
  [:div#-default-modal.modal.card
   [:div.container
    [:h2 "Default Modal"]
    [:h3 "This should not happen.  How did this happen?"]
    [:pre (pr-str state)]]])

;; MDM - for testing
(defmethod modal-content :modal/hello [_]
  [:div#-hello-modal.modal.card
   [:div.container
    [:h2 "Hello"]]])

(defmethod modal-content :modal/confirm [{:keys [title description on-confirm]}]
  [:div.modal.card
   [:div#-confirm-modal.container
    (back-link)
    [:h3.small-margin-bottom title]
    [:p "Are you should you would like to " description " ?"]
    [:div.button-group.margin-top
     [:button#-confirm-modal-confirm-button.error {:on-click #(do (pop!) (on-confirm))} "Yes"]
     [:button#-confirm-modal-cancel-button.secondary {:on-click pop!} "Cancel"]]]])

(defn confirm! [title description on-confirm]
  (push! :modal/confirm :title title :description description :on-confirm on-confirm))
