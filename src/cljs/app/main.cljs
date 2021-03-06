(ns app.main
		(:require
			;; MDM - the following namespaces contain multimethod implementations which decouples them nicely.
			;;   However they need to be required somewhere.
			[app.home]
			[app.sandbox.example-toy]

			;; Normal requires
			[accountant.core :as accountant]
			[app.config :as config]
			[app.init :as init]
			[app.layout :as layout]
			[app.router :as router]
			[c3kit.apron.log :as log]
			[c3kit.apron.utilc :as utilc]
			[c3kit.wire.ajax :as ajax]
			[c3kit.wire.api :as api]
			[c3kit.wire.flash :as flash]
			[goog.events]
			[reagent.dom :as dom]
			))

;; MDM: Needed with advanced compilation so pages can load content
(goog/exportSymbol "goog.require" goog/require)

(defn load-flash [messages]
		(doseq [flash messages]
				(flash/add! flash)))

(defn load-config [config]
(api/configure! {:version            (:api-version config)
																	:anti-forgery-token (:anti-forgery-token config)
																	:csrf-token         (:csrf-token config)})
(config/install! config)
(if (config/production?)
		(log/warn!)
		(log/debug!))
(when (config/development?)
		(log/debug "loading dev routes")
		(router/dev-routes))
(reset! flash/flash-timeout-millis 5000)) ;; Remove on c3kit.wire 1.0.4+)

(defn dispatch-and-render []
		(router/app-routes)
		(accountant/dispatch-current!)
		(dom/render [layout/default] (.getElementById js/document "app-root")))

(defn establish-session [config]
		(config/install! config)
				 )

;(defn request-sandbox []
;		)

(defn ^:export main [payload-src]
		(init/configure-api!)
		(let [payload (utilc/<-transit payload-src)]
				(load-config (:config payload))
				(load-flash (:flash payload))
				(dispatch-and-render)
				;(establish-session {})
				))

(enable-console-print!)