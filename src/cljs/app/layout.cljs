(ns app.layout
		(:require
			[c3kit.wire.flash :as flash]
			[app.page :as page]))

(defn default []
		[:div#content {:class "studio-background"}
			[:header
				[flash/flash-root]]
			(page/render @page/current)

		[:footer
			[:p "Visit " [:a {:href "https://cleancoders.com" :target "_blank"} "cleancoders.com"]
				" for training videos & software consulting"]
			[:p "© 2021 Clean Coders • "
				[:a {:href "https://cleancoders.com/legal"} "User Agreement & Privacy Policy"]
				" • "
				[:a {:href "mailto:contact@cleancoders.com?subject=Planning%20Poker"} [:span.fa.fa-envelope]]]]

;[modal/modal]
])