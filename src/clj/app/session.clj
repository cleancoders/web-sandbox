(ns app.session
		(:refer-clojure :exclude [load])
		(:require
			[c3kit.bucket.hashid :as hashid]
			[c3kit.apron.time :as time :refer [days ago]]
			[ring.middleware.session.store :as store]))

(def sessions (atom {}))
(def session-hash-fns (hashid/hashid-fns "silly poker session, using hashids" 30))
(def id->hash (:id->hash session-hash-fns))
(def hash->id (:hash->id session-hash-fns))

(defn session-key [session-entity] (id->hash (:id session-entity)))

(defn load-session [key]
		(when key
				(when-let [session (get @sessions (hash->id key))]
						(read-string (:data session)))))

(defn current [request] (-> request :session/key load-session))

(defn save-session [key data]
		(if (nil? key)
				(let [key     (apply * (repeat 4 (rand-int 10000)))
										session {:id key :kind :session :data (pr-str data) :updated-at (time/now)}]
						(swap! sessions assoc key session)
						(get-in @sessions [key]))
				(let [session         (get-in @sessions [(hash->id key)])
										session-data    (load-session key)
										updated-data    (if data (pr-str (merge session-data data)) (pr-str session-data))
										updated-session (assoc session :data updated-data :updated-at (time/now))]
						(swap! sessions assoc (hash->id key) updated-session)
						(get-in @sessions [(hash->id key)]))))

(defn delete-session [key]
		(when-let [session (get @sessions (hash->id key))]
				(swap! sessions dissoc (:id session))))

(defn update-session [key update-fn]
		(let [session (load-session key)
								updated (update-fn session)]
				(save-session key updated)))

(defn reload [session]
		(when-let [id (:id session)] (save-session (id->hash id) nil)))

(deftype DBStore []
		store/SessionStore

		;; Read a session map from the store. If the key is not found, nil
		;; is returned.
		(read-session [this key] (load-session key))

		;; Write a session map to the store. Returns the (possibly changed)
		;; key under which the data was stored. If the key is nil, the
		;; session is considered to be new, and a fresh key should be
		;; generated.
		(write-session [this key data] (-> (save-session key data) session-key))

		;; Delete a session map from the store, and returns the session
		;; key. If the returned key is nil, the session cookie will be
		;; removed.
		(delete-session [this key] (do (delete-session key) nil))
		)                                                         ;; end DBStore

(defn db-session-store [] (DBStore.))

(defn copy [response request] (assoc response :session (:session request)))
(defn destination= [response dest] (assoc-in response [:session :destination] dest))
(defn destination [request] (-> request :session :destination))


