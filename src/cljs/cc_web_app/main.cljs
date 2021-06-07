(ns cc-web-app.main
		(:require
			;; MDM - the following namespaces contain multimethod implementations which decouples them nicely.
			;;   However they need to be required somewhere.
			[cc-web-app.pages.test-page]

			;; Normal requires
			[accountant.core :as accountant]
			[c3kit.apron.log :as log]
			[c3kit.apron.utilc :as utilc]
			[c3kit.wire.ajax :as ajax]
			[c3kit.wire.api :as api]
			[c3kit.wire.flash :as flash]
			[c3kit.wire.websocket :as websocket]
			[goog.events]
			[cc-web-app.config :as config]
			[cc-web-app.init :as init]
			[cc-web-app.layout :as layout]
			[cc-web-app.router :as router]
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
		(log/debug "loading app routes")
		(router/app-routes)
		(reset! flash/flash-timeout-millis 5000)                  ;; Remove on c3kit.wire 1.0.4+
		)

(defn dispatch-and-render []
		(router/app-routes)
		(accountant/dispatch-current!)
		(dom/render [layout/layout] (.getElementById js/document "app-root")))

(defn establish-session [config]
		(config/install! config)
		(websocket/start!))

(defn ^:export main [payload-src]
		(init/configure-api!)
		(let [payload (utilc/<-transit payload-src)]
				(load-config (:config payload))
				(load-flash (:flash payload))
				(dispatch-and-render)
				(establish-session {})))

(enable-console-print!)