(ns app.errors
		(:require
			[c3kit.apron.log :as log]
			[app.layouts :as layouts]
			[hiccup.core :refer [html]]
			))

(defn wrap-errors [handler]
		(fn [request]
				(try
						(handler request)
						(catch Throwable e
								(log/error e)
								(layouts/error e)))))
