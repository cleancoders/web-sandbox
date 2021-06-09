(ns app.core
		(:import [goog History])
		(:require
			[accountant.core :as accountant]
			[c3kit.wire.js :as wjs]
			[secretary.core :as secretary]
			))

(defn navigate!
		"Use for navigation between rich-client pages.  Passing a full URL will cause error."
		[path]
		(accountant/navigate! path))

(defn goto! [path]
		(when path
				(if (secretary/locate-route path)
						(navigate! path)
						(wjs/redirect! path))))

