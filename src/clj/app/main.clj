(ns app.main
  (:import [java.lang Runtime Thread])
  (:require
   [c3kit.apron.app :as app]
   [c3kit.apron.log :as log]
   [c3kit.apron.time :refer [minutes hours]]
   [c3kit.apron.util :as util]
   [c3kit.wire.websocket :as websocket]
   [app.config :as config]
   [app.init :as init]
   ))

(defn start-env [app] (app/start-env "cc.env" "CC_ENV" app))

(def env (app/service 'app.main/start-env 'c3kit.apron.app/stop-env))
(def http (app/service 'app.http/start 'app.http/stop))

(def all-services [env http websocket/service])

(defn start-all [] (app/start! all-services))
(defn stop-all [] (app/stop! all-services))

(defn -main []
  (log/report "----- STARTING Clean Coders Web-App SERVER -----")
  (init/configure-api!)
  (.addShutdownHook (Runtime/getRuntime) (Thread. stop-all))
  (.addShutdownHook (Runtime/getRuntime) (Thread. shutdown-agents))
  (start-all))