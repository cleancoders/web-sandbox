(ns app.http
						(:require
							[c3kit.apron.log :as log]
							[c3kit.apron.util :as util]
							[compojure.core :refer [defroutes routes]]
							[compojure.route :as route]
							[org.httpkit.server :refer [run-server]]
							[c3kit.wire.assets :refer [wrap-asset-fingerprint]]
							[app.config :as config]
							[app.errors :as errors]
							[app.layouts :as layouts]
							[ring.middleware.anti-forgery :refer [wrap-anti-forgery]]
							[ring.middleware.content-type :refer [wrap-content-type]]
							[ring.middleware.cookies :refer [wrap-cookies]]
							[ring.middleware.flash :refer [wrap-flash]]
							[ring.middleware.head :refer [wrap-head]]
							[ring.middleware.keyword-params :refer [wrap-keyword-params]]
							[ring.middleware.multipart-params :refer [wrap-multipart-params]]
							[ring.middleware.nested-params :refer [wrap-nested-params]]
							[ring.middleware.not-modified :refer [wrap-not-modified]]
							[ring.middleware.params :refer [wrap-params]]
							[ring.middleware.resource :refer [wrap-resource]]
							[ring.middleware.session :refer [wrap-session]]
							))

		(defn refreshable [handler-sym]
				(util/resolve-var handler-sym))

		(defroutes web-handler
													(refreshable 'app.routes/handler)
													(route/not-found (layouts/not-found)))

		(defn app-handler []
				(let [wrap-verbose (util/resolve-var 'c3kit.wire.verbose/wrap-verbose)
										refresh-handler (util/resolve-var 'c3kit.wire.refresh/refresh-handler)]
						(-> (refresh-handler 'app.http/web-handler)
								wrap-verbose)))

;(defn wrap-session [handler]
;		(if config/development?
;				(fn [request]
;						(let [;store ((util/resolve-var 'app.session/db-session-store))
;												handler (ring.middleware.session/wrap-session handler {:cookie-name "app-session" :store store})]
;								(handler request)))
;				(ring.middleware.session/wrap-session handler {:cookie-name "app-session" :store (session/db-session-store)})))


		;; MDM - What's all this refresh/development hocus pocus?  An explanation owed.
		;;  In development, we want changed code to automatically reload when a request is made.  Although simple in
		;;  principle, the mechanics of it give me a headache sometimes.
		;;  1) When the app starts, some namespaces are loaded, like this one.  But the refresh code (app.refresh)
		;;      doesn't know which. As far as it knows, nothing has been loaded.  So on the first request, all the namespaces
		;;      are reloaded.
		;;  2) Some namespaces will/should never get reloaded. See app.refresh/excludes
		;;  3) The root-handler below is expensive to create.  Hence the defonce.  So we carefully pick pieces of the
		;;      root-handler to refresh:
		;;        - app.routes/handler - the essence of app.com
		;;        - wrap-session - because it uses the database connection which gets reloaded

		(defonce root-handler
											(-> (app-handler)
													wrap-anti-forgery
													wrap-flash
													wrap-session
													wrap-keyword-params
													wrap-multipart-params
													wrap-nested-params
													wrap-params
													wrap-cookies
													(wrap-resource "public")
													wrap-asset-fingerprint
													wrap-content-type
													wrap-not-modified
													wrap-head
													))

		(defn start [app]
								(let [port (if-let [port-str (System/getenv "PORT")] (Integer/parseInt port-str) 8082)]
										(log/info (str "Starting HTTP server: http://localhost:" port))
										(let [server (run-server root-handler {:port port})]
												(assoc app :http server))))

		(defn stop [app]
								(when-let [stop-server-fn (:http app)]
										(log/info "Stopping HTTP server")
										(stop-server-fn :timeout 1000))
								(dissoc app :http))

