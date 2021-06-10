(ns app.init
		(:require
			#?(:cljs [app.core :as cc])
			#?(:cljs [app.page :as page])
			#?(:cljs [reagent.core :as reagent])
			[app.config :as config]
			[c3kit.apron.legend :as legend]
			[c3kit.wire.api :as api]
			))

(defn configure-api! []
		(api/configure! #?(:clj  {:ws-handlers 'app.routes/ws-handlers
																												:version     (api/version-from-js-file (if config/development? "public/cljs/app_dev.js" "public/cljs/app.js"))}
																					:cljs {:redirect-fn       cc/goto!
																												:ws-on-reconnected page/reconnected!
																												:ws-uri-path       "/user/websocket"
																												})))