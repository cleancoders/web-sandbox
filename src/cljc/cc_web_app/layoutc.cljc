(ns cc-web-app.layoutc
		#?(:cljs (:require [c3kit.wire.util :as util])))

(defn with-keys [col]
		#?(:clj col
					:cljs (util/with-react-keys col)))

(defn base [& content]
		[:div#content.studio-background
			[:header]
			(with-keys content)])
