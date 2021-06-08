(ns cc-web-app.layoutc
		#?(:cljs (:require [c3kit.wire.util :as util])))

(defn with-keys [col]
		#?(:clj col
					:cljs (util/with-react-keys col)))

(def home-logo [:div.container.text-align-center
																[:img.margin-bottom {:src "/images/logos/studio-color-transparent.png"}]
																[:h2.large-margin-bottom "A Web-App Template" [:span.trademark "®"]]])

(defn home-card [& content]
		(println "home-card content: " content)
		[:div.container.small-width.huge-padding-top
			[:div.card.medium-margin-bottom
				(with-keys content)]])

(defn home-main [& content]
		(println "home-main content: " content)
		[:main
			[:section.home
				(with-keys content)]])

(defn base [& content]
		(println "base content: " content)
		[:div#content.studio-background
			[:header]
			(with-keys content)])
