(ns cc-web-app.dev
  (:require
    [cc-web-app.cljs :as cljs]
    [cc-web-app.css :as css]
    [cc-web-app.main :as main]
    [c3kit.apron.log :as log]))

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
