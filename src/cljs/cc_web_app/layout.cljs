(ns cc-web-app.layout
		(:require
			[c3kit.wire.flash :as flash]
			[cc-web-app.page :as page]))

(defn layout []
		[:div#content {:class "studio-background"}
			[:header
				[flash/flash-root]]]
			(page/render @page/current))