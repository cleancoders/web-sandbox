(ns app.home
		(:require
			[c3kit.apron.corec :as ccc]
			[c3kit.apron.log :as log]
			[c3kit.wire.js :as wjs]
			[c3kit.wire.websocket :as ws]
			[app.layoutc :as layoutc]
			[app.page :as page]
			[reagent.core :as reagent]
			))

(defn create-page []
		(ws/call! :page/create {:name "new-page"} #(log/info "page created!")))

(defn list-steps []
		[:div.container
			[:hr.margin-bottom]
			[:div.hgroup.medium-margin-bottom
				[:h2 "Welcome"]
				;[:button#-create-page-button.primary {:on-click create-page} "Create a Page"]
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

