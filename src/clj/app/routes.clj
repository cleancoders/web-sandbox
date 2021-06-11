(ns app.routes
		(:require
			[c3kit.apron.util :as util]
			[c3kit.wire.ajax :as ajax]
			[clojure.string :as str]
			[compojure.core :as compojure :refer [ANY defroutes routes context]]
			[app.config :as config]
			[ring.util.response :as response]
			))

(defn wrap-prefix [handler prefix not-found-handler]
		(fn [request]
				(let [path (or (:path-info request) (:uri request))]
						(when (str/starts-with? path prefix)
								(let [request (assoc request :path-info (subs path (count prefix)))]
										(if-let [response (handler request)]
												response
												(not-found-handler request)))))))

(def resolve-handler
		(if config/development?
				(fn [handler-sym] (util/resolve-var handler-sym))
				(memoize (fn [handler-sym] (util/resolve-var handler-sym)))))


(defn lazy-handle
		"Reduces load burden of this ns, which is useful in development.
		Runtime errors will occur for missing handlers, but all the routes should be tested in routes_spec.
		Assumes all handlers take one parameter, request."
		[handler-sym request]
		(let [handler (resolve-handler handler-sym)]
				(handler request)))

(defmacro lazy-routes
		"Creates compojure route for each entry where the handler is lazily loaded.
		Why are params a hash-map instead of & args? -> Intellij nicely formats hash-maps as tables :-)"
		[table]
		`(routes
					~@(for [[[path method] handler-sym] table]
									(let [method (if (= :any method) nil method)]
											(compojure/compile-route method path 'req `((lazy-handle '~handler-sym ~'req)))))))

(defn redirect-handler [path]
		(let [segments (str/split path #"/")
								segments (map #(if (str/starts-with? % ":") (keyword (subs % 1)) %) segments)]
				(fn [request]
						(let [params   (:params request)
												segments (map #(if (keyword? %) (get params %) %) segments)
												dest     (str/join "/" segments)]
								(response/redirect dest)))))

(defmacro redirect-routes [table]
		`(routes
					~@(for [[[path method] dest] table]
									(let [method (if (= :any method) nil method)]
											(compojure/compile-route method path 'req `((redirect-handler ~dest)))))))

(def ws-handlers
		{
			:page/create 'app.page-maker/ws-create
			:user/auth 'app.user-handlers/ws-auth-user
			})

(def ajax-routes-handler
		(->
				(lazy-routes
						{
							["/user/csrf-token" :get]          app.user-handlers/ajax-csrf-token
							})
				(wrap-prefix "/api" ajax/api-not-found-handler)
				ajax/wrap-ajax))

(def web-routes-handlers
		(lazy-routes
				{
					["/" :get]               app.layouts/web-rich-client
					["/user/websocket" :get] app.user-handlers/websocket-open-get
					["/user/websocket" :post] app.user-handlers/websocket-open-post
					}))

(def dev-handler
		(lazy-routes
				{
					["/pages/:page" :get] app.layouts/web-rich-client
					}))

(defroutes handler
		ajax-routes-handler
		web-routes-handlers
		(if config/development? dev-handler (fn [_] nil))
		)