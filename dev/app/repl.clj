(ns app.repl
  (:require
    [c3kit.apron.log :as log]
    [app.main :as main]
    [c3kit.apron.time :as time :refer [milliseconds seconds minutes hours days ago from-now]]
    [clojure.string :as str]
    [clojure.pprint :refer [pprint]]
    ))

(println "Welcome to the Clean Coders Web-App REPL!")
