(ns cc-web-app.page
		(:require [cc-web-app.layouts :as layouts]))

(defn web-page [request]
		(layouts/web-rich-client request))
