(ns cc-web-app.main
  (:import [java.lang Runtime Thread])
  (:require
   [c3kit.apron.app :as app]
   [c3kit.apron.log :as log]
   [c3kit.apron.time :refer [minutes hours]]
   [c3kit.apron.util :as util]
   ;[c3kit.bucket.bg :as bg]
   [c3kit.wire.websocket :as websocket]
   [cc-web-app.config :as config]
   [cc-web-app.init :as init]

   ))

(defn -main []
  (log/report "----- STARTING Clean Coders Web-App SERVER -----")
  (init/configure-api!)
  (.addShutdownHook (Runtime/getRuntime) (Thread. stop-all))
  (.addShutdownHook (Runtime/getRuntime) (Thread. shutdown-agents))
  (start-all))