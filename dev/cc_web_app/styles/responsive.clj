(ns cc-web-app.styles.responsive
  (:refer-clojure :exclude [rem])
  (:require [cc-web-app-styles.palette :refer :all]
            [cc-web-app-styles.core :refer :all]
            [garden.stylesheet :refer [at-media]]
))


(def screen
(list

(at-media {:screen true :max-width (px 1440)}
  [:.story-sidebar {
    :flex [[0 0 (px 500)]]
  }]

  [:.story [:.name:hover [:span {
    :max-width (px 450)
  }]]]

  [:.room
    [:.card-table {
      :flex 1
    }]

    [:.card-table-scroll-container {
      :flex [[0 0 "calc(100vh - 60px)"]]
    }]
  ]

  [:.card-table [:.hand {
    :bottom (px -150)
   }]]
)

(at-media {:screen true :max-width (px 800)}
  [:footer {
    :flex-direction "column"
    :justify-content "none"
  }]

)


))
