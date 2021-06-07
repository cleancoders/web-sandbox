(ns cc-web-app.styles.tables
  (:refer-clojure :exclude [rem])
  (:require [cc-web-app-styles.palette :refer :all]
            [cc-web-app-styles.core :refer :all]
            [garden.color :as color]
            [garden.stylesheet :refer [at-media]]
            [garden.def :refer [defstyles]]
))

(def screen
(list

[:table {
  :text-align "left"
  :width "100%"
  }

  [:&.striped
    [:td :th {
      :padding [[(rem 0.25) (rem 0.5)]]
    }]
    ["tr:nth-child(even)" {
      :background-color off-white
    }]
  ]

  [:button :a.button {
    :font-size (rem 0.8)
    :padding [[0 (rem 0.5)]]
  }]

  [:&.outline {
    :border [[(px 1) "solid" light-grey]]
    }

    [:td :th {
      :padding [[(rem 0.1) (rem 0.625)]]
    }]
  ]
]

[:td.focus :th.focus {
  :background-color light-grey
}]

[:td.minus-two-std-dev :th.minus-two-std-dev {
  :background-color minus-two-std-dev
}]

[:td.minus-one-std-dev :th.minus-one-std-dev {
  :background-color minus-one-std-dev
}]

[:td.zero-std-dev :th.zero-std-dev {
  :background-color zero-std-dev
}]

[:td.plus-one-std-dev :th.plus-one-std-dev {
  :background-color plus-one-std-dev
}]

[:td.plus-two-std-dev :th.plus-two-std-dev {
  :background-color plus-two-std-dev
  :color white
}]

[:div.gaussian {
  :height (px 400);
}]

#_[:.card
  [:td
    overflow-ellipsis
   {:width "auto"
    :max-width (px 230)}
  ]

  [:td:first-child {
    :padding-right (rem 1)
  }]
]

#_[:.container.small-width [:.card [:td {
  :max-width (px 230)
  :width "1000px"
}]]]

))
