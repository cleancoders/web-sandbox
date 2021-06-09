(ns app.user-handlers
		(:require
			[c3kit.wire.ajax :as ajax]
			[c3kit.wire.websocket :as websocket]
			[app.session :as session]
			[app.user :as user]
			))


(defn ajax-csrf-token [request]
		(-> (ajax/ok {:csrf-token         (:session/key request)
																:anti-forgery-token (-> request :session :ring.middleware.anti-forgery/anti-forgery-token)})
				(session/copy request)
				(assoc-in [:session :csrf?] true)))


(defn websocket-open-get [request] (when (user/current request) (@websocket/get-handler request)))
;(defn websocket-open-post [request] (when (user/current request) (@websocket/post-handler request)))


