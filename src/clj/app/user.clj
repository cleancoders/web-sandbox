(ns app.user
		(:import (java.net URL))
		(:require
			[clojure.string :as str]
			[c3kit.wire.ajax :as ajax]
			[c3kit.wire.apic :as apic]
			[c3kit.wire.flash :as flash]
			[app.session :as session]
			[ring.util.response :as response]
			[c3kit.apron.corec :as ccc]))

(defn current [request] (-> request :session :user))

(defn valid-user? [request]
		(boolean
				(when-let [user (current request)]
						(:id user))))

(defn referer-path [request]
		(when-let [referer (get-in request [:headers "referer"])]
				(let [url (URL. referer)]
						(.getPath url))))

(defn extract-destination [request]
		(let [method (or (:request-method request) :get)
								uri (:uri request)]
				(if (= :post method)
						{:method :post :uri uri :params (:params request)}
						(if (and uri (str/starts-with? uri "/api/"))
								{:method :get :uri (referer-path request)}
								{:method :get :uri uri :query-string (:query-string request)}))))

(defn preserve-destination [response request]
		(let [destination (extract-destination request)]
				(-> response
						(session/copy request)
						(session/destination= destination)
						)))

(defn web-redirect-warning [request url message]
		(-> (response/redirect url)
				(preserve-destination request)
				(flash/warn message)))

(defn ajax-redirect-warning [request url message]
		(-> (ajax/redirect url)
				(preserve-destination request)
				(update :body apic/flash-warn message)))

(defn uri-destination [dest]
		(when-let [uri (:uri dest)]
				(if-let [query-string (:query-string dest)]
						(str uri "?" query-string)
						uri)))

(defn default-user-destination [user] "/")

(defn calculate-destination-uri [request user]
		(let [dest (session/destination request)]
				(or (when (= :post (:method dest)) :post)
						(uri-destination dest)
						(default-user-destination user))))

(defn add-destination-to-payload [response user]
		;; MDM - assumes session has already been copied to response
		(let [dest (calculate-destination-uri response user)]
				(if (= :post dest)
						(assoc-in response [:body :payload :destination] "/redirect")
						(-> response
								(assoc-in [:body :payload :destination] dest)
								(update :session dissoc :destination)))))

(defn maybe-assign-picture [user]
		(if (:picture user)
				user
				(assoc user :picture (str "/images/avatars/0" (inc (rand-int 7)) ".jpg"))))

(defn create-user [cc-user]
		{:id (str (ccc/new-uuid))})

;(defn authorize-user [response user] (update response :session assoc :user user))

(defn forbid-user [response] (update response :session dissoc :user))

;(defn login-user [request user message]
;  (-> (ajax/ok {:user (legend/present! user)} message)
;      (session/copy request)
;      (add-destination-to-payload user)
;      (authorize-user user)))

