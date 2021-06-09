(ns app.home
		(:require
			[c3kit.apron.corec :as ccc]
			[c3kit.wire.js :as wjs]
			[c3kit.wire.websocket :as ws]
			[app.layoutc :as layoutc]
			[app.page :as page]
			[reagent.core :as reagent]
			))

(defmethod page/render :home [_]
		[:main#-home
			[:section.home
			[:h1 "Hello, World!"]]])

