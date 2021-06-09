(ns app.router
		(:require-macros [secretary.core :refer [defroute]])
		(:require
			[accountant.core :as accountant]
			[c3kit.apron.log :as log]
			[app.page :as store :refer [render]]
			[secretary.core :as secretary]))

(def default-title "A Clean Coders Studio Web-App Template")

(defn dispatch! [uri]
		(log/debug "dispatching: " uri)
		(secretary/dispatch! uri))

(defn locate-route [route]
		(let [result (secretary/locate-route route)]
				(log/debug "locate-route: " route " -> " result)
				result))

(defn- hook-browser-navigation! []
		(accountant/configure-navigation! {:nav-handler dispatch! :path-exists? locate-route}))

(defn- exit-enter-page [page]
		(let [current-page (:page @store/state)]
				(when-not (= current-page page)
						(log/debug "leaving page:" current-page)
						(store/leaving! current-page)
						(log/debug "entering page:" page)
						(store/entering! page))))

(defn load-page! [page]
		(exit-enter-page page)
		(.scrollTo js/window 0 0)
		(set! (.-title js/document) default-title)
		(swap! store/state assoc :page page))

(defn app-routes []
		(secretary/set-config! :prefix "")

		(defroute "/" [] (load-page! :home))
		(defroute "/pages/:page" [page] (load-page! (keyword (str "pages/" page))))

		(hook-browser-navigation!))

