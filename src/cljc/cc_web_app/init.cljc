(ns cc-web-app.init
		(:require
			#?(:cljs [cc-web-app.core :as cc])
			#?(:cljs [cc-web-app.page :as page])
			#?(:cljs [reagent.core :as reagent])
			[c3kit.wire.api :as api]
			))

(defn configure-api! []
		(api/configure! #?(:clj  {:ws-handlers 'cc-web-app.routes/ws-handlers
																												:version     (api/version-from-js-file"public/cljs/cc-web-app.js")}
																					:cljs {:redirect-fn       cc/goto!
																												:ws-on-reconnected page/reconnected!})))