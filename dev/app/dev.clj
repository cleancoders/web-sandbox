(ns app.dev
  (:require
   [c3kit.apron.log :as log]
   [c3kit.scaffold.cljs :as cljs]
   [c3kit.scaffold.css :as css]
   [app.main :as main]))

(defn start-cljs [] (cljs/-main "auto" "development"))
(defn start-css [] (css/-main "auto" "development"))

(def cljs-thread (Thread. start-cljs))
(def css-thread (Thread. start-css))
(def server-thread (Thread. main/-main))
(def threads
  [
   server-thread
   cljs-thread
   css-thread
   ])

(defn shutdown []
  (log/report "---- DEV Task - Shutdown ----")
  )

(defn -main
  "Where three separate lein tasks (and Java processes) were required before, this single task will host them all
   in a single Java process.  Easier to use and consuming less computer resources."
  []
  (log/report "---- DEV Task - One process to rule them all.----")

  (.addShutdownHook (Runtime/getRuntime) (Thread. shutdown))

  (doseq [t threads]
    (.start t))
  )
