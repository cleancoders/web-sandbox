(ns app.user
		(:require
			[c3kit.apron.log :as log]
			[c3kit.wire.ajax :as ajax]
			[c3kit.wire.js :as wjs]
			[c3kit.wire.websocket :as ws]
			[app.core :as cc]
			;[app.modal :as modal]
			[app.page :as page]
			[reagent.core :as reagent]
			))


(def user-atom (reagent/atom nil))
(def authed? (reagent/atom false))

(defn install! [user] (reset! user-atom user))
(defn current [] @user-atom)
(defn clear! [] (reset! user-atom nil))

(defn- authed! [data]
		(reset! authed? true))

(defn install-and-connect! [user]
		(if-let [user user]
				(install! user)
				(install! {:id (apply * (repeat 4 (rand-int 1000)))}))
		(ws/start!)
		(ws/call! :user/auth nil authed!))

