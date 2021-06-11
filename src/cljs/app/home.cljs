(ns app.home
		(:require
			[app.layoutc :as layoutc]
			[app.page :as page]
			[c3kit.wire.ajax :as ajax]
			[c3kit.apron.log :as log]))

(defn request-new-toy []
		(let [handler (fn [response]
																		(log/info response))]
			(ajax/post! "/api/toy-builder/request-toy" {:name "your-new-toy"} handler)))

(defn list-steps []
		[:div.container
			[:hr.margin-bottom]
			[:div.hgroup.medium-margin-bottom
				[:h2 "Welcome"]
				[:button#-build-toy-button.primary {:on-click request-new-toy} "Create a Page"]
				]
			[:ul {:id "-interactive" :class "interactive small-margin-bottom"}
				[:li {:id "step-1"}
					[:span {:class "name"} "1. In \"src/cljs/pages\" create a new cljs file."]
					[:br]]
				[:li {:id "step-2"}
					[:span {:class "name"} "2. Give the page a title."]]
				[:li {:id "step-3"}
					[:span {:class "name"} "3. Require app.page:\r\n"
						[:br] "(:require [app.page :as page])"]]
				[:li {:id "step-4"}
					[:span {:class "name"} "4.  Create your render defmethod:\r\n"
						[:br] "(defmethod page/render :page/{PAGE}"]]
				[:li {:id "step-5"}
					[:span {:class "name"} "5. In the browser, go to:\r\n"
						[:br] "localhost:8082/pages/{PAGE}"]]
				]])

(defmethod page/render :home [_]
		[:main#-home
			[:section.home
				(layoutc/home-card
						layoutc/home-logo
						(list-steps))]])

