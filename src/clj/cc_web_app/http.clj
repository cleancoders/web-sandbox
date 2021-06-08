(ns cc-web-app.http
						(:require
							[c3kit.apron.log :as log]
							[c3kit.apron.util :as util]
							[compojure.core :refer [defroutes routes]]
							[compojure.route :as route]
							[org.httpkit.server :refer [run-server]]
							[c3kit.wire.assets :refer [wrap-asset-fingerprint]]
							[cc-web-app.config :as config]
							;[cc-web-app.errors :as errors]
							[cc-web-app.layouts :as layouts]
							))

		(defn refreshable [handler-sym]
				(util/resolve-var handler-sym))

		(defroutes web-handler
													(refreshable 'cc-web-app.routes/handler)
													(route/not-found (layouts/not-found)))

		(defn app-handler []
				(let [wrap-verbose (util/resolve-var 'c3kit.wire.verbose/wrap-verbose)
										refresh-handler (util/resolve-var 'c3kit.wire.refresh/refresh-handler)]
						(-> (refresh-handler 'cc-web-app.http/web-handler)
								wrap-verbose)))

		;; MDM - What's all this refresh/development hocus pocus?  An explanation owed.
		;;  In development, we want changed code to automatically reload when a request is made.  Although simple in
		;;  principle, the mechanics of it give me a headache sometimes.
		;;  1) When the app starts, some namespaces are loaded, like this one.  But the refresh code (cc-web-app.refresh)
		;;      doesn't know which. As far as it knows, nothing has been loaded.  So on the first request, all the namespaces
		;;      are reloaded.
		;;  2) Some namespaces will/should never get reloaded. See cc-web-app.refresh/excludes
		;;  3) The root-handler below is expensive to create.  Hence the defonce.  So we carefully pick pieces of the
		;;      root-handler to refresh:
		;;        - cc-web-app.routes/handler - the essence of cc-web-app.com
		;;        - wrap-session - because it uses the database connection which gets reloaded

		(defonce root-handler
											(-> (app-handler)
													;errors/wrap-errors
													wrap-asset-fingerprint
													))

		(defn start [app]
								(let [port (if-let [port-str (System/getenv "PORT")] (Integer/parseInt port-str) 8081)]
										(log/info (str "Starting HTTP server: http://localhost:" port))
										(let [server (run-server root-handler {:port port})]
												(assoc app :http server))))

		(defn stop [app]
								(when-let [stop-server-fn (:http app)]
										(log/info "Stopping HTTP server")
										(stop-server-fn :timeout 1000))
								(dissoc app :http))


