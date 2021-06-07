(ns cc-web-app.layouts
		(:require
			[c3kit.apron.legend :as legend]
			[c3kit.apron.utilc :as utilc]
			[c3kit.wire.api :as api]
			[c3kit.wire.assets :refer [add-fingerprint]]
			[c3kit.wire.flash :as flash]
			[clj-stacktrace.core :as cst]
			[clj-stacktrace.repl :as cstr]
			[clojure.string :as str]
			[hiccup.core :as hiccup]
			[hiccup.element :as elem]
			[hiccup.page :as page]
			[cc-web-app.layoutc :as layoutc]
			[ring.util.response :as response]
			))

(def default-title "A Web-App Template by Clean Coders")
(def default-description "Build a new page with the Clean Coders Web-App Template")
(defn title [options] (or (:title options) default-title))

(defn default
		([body] (default body {}))
		([body options]
			(-> (response/response
									(page/html5
											[:head
												[:title (title options)]
												[:meta {:charset "utf-8"}]
												[:meta {:name "viewport" :content "width=device-width, initial-scale=1.0, minimum-scale=1.0"}]
												[:link {:rel         "stylesheet"
																				:href        "https://use.fontawesome.com/releases/v5.3.1/css/all.css"
																				:integrity   "sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU"
																				:crossorigin "anonymous"}]
												(page/include-js (add-fingerprint "/cljs/cc-web-app.js"))
												(:head options)                                 ;; MDM - must go after js so we can include js-fns, and before css, so we can override styles as needed
												(page/include-css (add-fingerprint (or (:css options) "/css/cc-web-app.css")))]
											[:body body]))

					(response/content-type "text/html")
					(response/charset "UTF-8"))))

(defn static [body]
		(default
				(layoutc/base
						body)))

(defn not-found []
		(response/status
				(static
						(layoutc/base
										[:hr]
										[:div.container.text-align-center
											[:h2.small-margin-bottom "Page Not Found"]
											[:p "Sorry, we don't know where this page is."]])))
				404)

(defn client-init
		([] (client-init {}))
		([data]
			(let [payload (pr-str (utilc/->transit data))]
					(str "<script type=\"text/javascript\">\n//<![CDATA[\n"
							"cc-web-app.main.main(" (str/replace payload "</script>" "<\\/script>") ");"
							"\n//]]>\n</script>"))))

(def rich-client-placeholder
		(layoutc/base))

(defn rich-client
		([options] (rich-client {} options))
		([payload options]
					(assoc options
							:head (elem/javascript-tag (str "goog.require('cc-web-app.main');")))))

(defn build-rich-client-payload [request]
		{:flash  (flash/messages request)
			:config {
												;:anti-forgery-token (:anti-forgery-token request)
												:api-version        (api/version)
												;:csrf-token         (:session/key request)
												}})

(defn web-rich-client
		"Load the default web page and let the client side take over."
		([request] (web-rich-client request {}))
		([request options] (rich-client (build-rich-client-payload request) options)))

(defn- elem-partial [elem]
		(if (:clojure elem)
				[:tr.clojure
					[:td.source (hiccup/h (cstr/source-str elem))]
					[:td.method (hiccup/h (cstr/clojure-method-str elem))]]
				[:tr.java
					[:td.source (hiccup/h (cstr/source-str elem))]
					[:td.method (hiccup/h (cstr/java-method-str elem))]]))

(defn error [ex]
		(let [[ex & causes] (iterate :cause (cst/parse-exception ex))]
				(static
						[:div.content
							[:section.bad-gateway.hero]
							[:section.message
								[:h1 "Oh no!"]
								[:h4 "Something went wrong. Please try your request again."]]
							[:div.exception
								[:h4 (hiccup/h (.getName ^Class (:class ex)))]
								[:div.message (hiccup/h (:message ex))]
								[:div.trace
									[:table
										[:tbody (map elem-partial (:trace-elems ex))]]]
								(for [cause causes :while cause]
										[:div.causes
											[:h2 "Caused by " [:span.class (hiccup/h (.getName ^Class (:class cause)))]]
											[:div.message (hiccup/h (:message cause))]
											[:div.trace
												[:table
													[:tbody (map elem-partial (:trace-elems cause))]]]])]])))