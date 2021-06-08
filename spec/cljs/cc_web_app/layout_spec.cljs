(ns cc-web-app.layout-spec
  (:require-macros [speclj.core :refer [describe context it should-not-be-nil should-be-nil should= should-not
                                        should-not= should-have-invoked after before before-all with-stubs with around
                                        stub should-contain should-not-contain should]]
                   [c3kit.wire.spec-helperc :refer [should-select should-not-select]])
  (:require
   [c3kit.wire.flash :as flash]
   [c3kit.wire.spec-helper :as wire-helper]
   [cc-web-app.layout :as sut]
   ;[cc-web-app.modal :as modal]
   [cc-web-app.page :as page]
   ))

(defmethod page/render :layout/test [_] "Layout Test")
;(defmethod modal/modal-content :layout/modal [] "Layout Modal Test")
;TODO add modal
(describe "Layout"

  (with-stubs)
  (wire-helper/with-root-dom)
  (before
    (page/clear!)
    (flash/clear!)
    (page/install-page! :layout/test)
    (wire-helper/render [sut/default]))

  (it "structure"
    (should-select "#content")
    (should-contain "Layout Test" (wire-helper/html)))

  (it "flash"
    (should-not-select ".flash-root")
    (flash/add-success! "Yes!")
    (wire-helper/flush)
    (should-select ".flash-root"))

  ;(it "modal"
  ;  (should-not-select "#-modal")
  ;  (modal/install! :layout/modal)
  ;  (wire-helper/flush)
  ;  (should-select "#-modal"))

  ;(it "spinner"
  ;  (should-not-select ".site-spinner")
  ;  (swap! ajax/active-ajax-requests inc)
  ;  (wire-helper/flush)
  ;  (should-select ".site-spinner"))
  )