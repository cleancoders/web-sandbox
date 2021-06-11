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



(defn websocket-open-get [request] (when (user/current request) (@websocket/get-handler request)))
(defn websocket-open-post [request]
		(let [session (-> request :session (assoc :user {:id (apply * (repeat 4 (rand-int 10000)))}))]
		(@websocket/post-handler (assoc request :session session))))

(defn authorize-user [request user]
		(-> (ajax/ok {:user user} "Welcome to the Clean Coders Web-App Template")
				(session/copy request)
				(user/add-destination-to-payload user)
				(user/authorize-user user)))

(defn ajax-auth [request]
		(let [connection (-> request :params :connection-id)
								token (-> request :params :csrf-token)
								user {:id (apply * (repeat 4 (rand-int 10000))) :connection connection :token token}]
				(authorize-user request user)))

(defn ws-auth-user [{:keys [request]}]
		(authorize-user request {:id (apply * (repeat 4 (rand-int 10000)))}))
