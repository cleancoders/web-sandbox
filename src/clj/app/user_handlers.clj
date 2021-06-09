(ns app.user-handlers
		(:require
			[c3kit.wire.ajax :as ajax]
			[c3kit.wire.websocket :as websocket]
			[app.session :as session]
			[app.user :as user]
			[c3kit.apron.corec :as ccc]
			[clojure.string :as string]))


(defn ajax-csrf-token [request]
		(-> (ajax/ok {:csrf-token         (:session/key request)
																:anti-forgery-token (-> request :session :ring.middleware.anti-forgery/anti-forgery-token)})
				(session/copy request)
				(assoc-in [:session :csrf?] true)))

(defn authorize-user [response user] (update response :session assoc :user user))

(defn websocket-open-get [request] (when (user/current request) (@websocket/get-handler request)))
;(defn websocket-open-post [request] (when (user/current request) (@websocket/post-handler request)))


