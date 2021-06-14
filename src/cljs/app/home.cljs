(ns app.home
		(:require
			[app.layoutc :as layoutc]
			[app.modal :as modal]
			[app.page :as page]
			[c3kit.wire.ajax :as ajax]
			[c3kit.apron.log :as log]
			[reagent.core :as reagent]))

(def name (reagent/atom nil))

(defn request-new-toy [name]
		(let [handler (fn [response]
																		(log/info (str (:new-toy response) " created")))]
				(ajax/post! "/api/toy-builder/request-toy" {:name name} handler)))

(defmethod modal/modal-content :home/build-toy [_]
		[:div.modal.card
			[:div#-new-toy-modal.container
				[:h2.margin-bottom "Enter Title"]
				[:input#-new-toy-title {:field :text
																												:id    "-toy-title"}]
				[:hr]
				[:fieldset
					[:div.button-group
						[:button#-build-toy-button.primary {:on-click request-new-toy} "OK!"]
						]]]])

(defn list-steps []
		[:div.container
			[:hr.margin-bottom]
			[:div.hgroup.medium-margin-bottom
				[:h2 "Welcome"]
				]
			[:ul {:id "-interactive" :class "interactive small-margin-bottom"}
				[:li {:id "step-1"}
					[:span {:class "name"} "1. In \"dev/app/sandbox\" create a new cljs file."]
					[:br]]
				[:li {:id "step-2"}
					[:span {:class "name"} "2. Give the file a title."]]
				[:li {:id "step-3"}
					[:span {:class "name"} "3. Require app.page:\r\n"
						[:br] "(:require [app.page :as page])"]]
				[:li {:id "step-4"}
					[:span {:class "name"} "4.  Create your render defmethod:\r\n"
						[:br] "(defmethod page/render :sandbox/{NAME}"]]
				[:li {:id "step-5"}
					[:span {:class "name"} "5. In main.cljs, require the file:\r\n"
						[:br] "(:require [app.sandbox.{NAME}])"]]
				[:li {:id "step-6"}
					[:span {:class "name"} "6. In the browser, go to:\r\n"
						[:br] "localhost:8082/pages/{NAME}"]]
				]
			[:div.hgroup.medium-margin-bottom
				[:input#-new-toy-title.text {:type "text" :on-change (fn [val] (reset! name (-> val .-target .-value)))}]
				[:button#-build-toy-button.primary {:on-click #(request-new-toy (if (nil? @name) "your-new-sandbox-toy" @name))} "Create a Sandbox Toy"]]])

(defmethod page/render :home [_]
		[:main#-home
			[:section.home
				(layoutc/home-card
						layoutc/home-logo
						(list-steps))]])
