(ns cc-web-app.page
		(:require
			[reagent.core :as reagent]
			[c3kit.apron.log :as log]))

(def state (reagent/atom {}))

(defn clear! [] (reset! state {}))

(defn install-page! [page] (swap! state assoc :page page))
(def current (reagent/track #(:page @state)))

(defn cursor
		([path] (cursor path nil))
		([path value]
			(let [c (reagent/cursor state path)]
					(reset! c value)
					c)))

(defmulti render identity)
(defmulti entering! identity)
(defmulti leaving! identity)
(defmulti reconnected! identity)

(defmethod render :default [page]
		(log/error "DEFAULT render-page!")
		[:h1 "DEFAULT render-page"])

(defmethod entering! :default [page])
(defmethod leaving! :default [page])
(defmethod reconnected! :default [page])
