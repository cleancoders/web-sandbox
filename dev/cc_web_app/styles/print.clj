(ns cc-web-app.styles.print
  (:refer-clojure :exclude [rem print])
  (:require [cc-web-app-styles.core :refer :all]
            [cc-web-app-styles.palette :refer :all]
            [garden.stylesheet :refer [at-media]]
))

(def page
(list

(at-media {:print true}
  [:header :footer :button :.fa-file-download {
    :display "none"
  }]

  [:td.plus-two-std-dev :th.plus-two-std-dev {
    :color black
  }]
)

))
