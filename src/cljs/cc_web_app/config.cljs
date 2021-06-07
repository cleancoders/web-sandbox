(ns cc-web-app.config
		(:require [reagent.core :as reagent]))

(def state (reagent/atom {}))

(defn install! [new-config]
		(when new-config
				(assert (map? new-config) "Config must come as a map")
				(swap! state merge new-config)))

(defn host [] (:host @state))
(defn link [& parts] (apply str (host) parts))
(defn cleancoders-root [] (:cleancoders-root @state))
