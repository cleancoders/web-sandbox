(ns cc-web-app.styles.layout
  (:refer-clojure :exclude [rem])
  (:require [cc-web-app-styles.palette :refer :all]
            [cc-web-app-styles.core :refer :all]
))

(def screen
(list

[:#app-root :#content :section { ;:main
  :height "100%"
}]

[:#content {
  :display "flex"
  :flex-direction "column"
  :min-height "100vh"
  :height "auto"
  :width "100%"
  }

  [:&.studio-background {
    :background-image "url('/images/backgrounds/studio-background.jpg')"
    :background-size "cover"
  }]
]

[:main {
  :flex 1
}]

[:footer {
  :color white
  :display "flex"
  :font-size (px 16)
  :justify-content "space-between"
  :padding [[(rem 0.25) (rem 0.625)]]
  }

  [:a {
    :color white
    :text-decoration "underline"
    }

    [:&:hover {
      :color white
    }]
  ]
]

[:.container {
  :margin [[0 "auto"]]
  :position "relative"
  }

  [:&.small-width {
    :max-width (px 380)
    :width "90%"
  }]

  [:&.medium-width {
    :max-width (px 752)
    :width "90%"
    :padding [[(rem 2) 0]]
  }]

  [:&.large-width {
    :max-width (px 960)
    :width "90%"
  }]

  [:&.full-width {
    :padding [[0 (rem 3.25)]]
  }]
]

[:.row {
  :display "flex"
  :position "relative"
  :width "100%"
}]

[:.column {
  :flex 1
  :position "relative"
  :width "100%"
  }

  [:&.standard-margin {
    :margin [[0 (rem 2.625) (rem 1.8125) 0]]
  }]

  [:&:last-child {
    :margin-right 0
  }]

  [:&.overflow-ellipsis {
    :width (px 10)
  }]

  [:&.small-width {
    :flex [[0 0 (px 350)]]
  }]
]

[:div.scrollable-column {
  :max-height "calc(100vh - 200px)"
  :overflow-x "hidden"
  :overflow-y "auto"
}]

[:.button-group [:button :.button {
  :margin [[0 (rem 0.375) (rem 0.375) 0]]
  }

  [:&:last-child {
    :margin-bottom 0
  }]
]]

[:.button-grid {
  :display "grid"
  :grid-template-columns "repeat(auto-fill, minmax(200px, 1fr))"
  :grid-gap (rem 1)
}]

[:.hgroup {
  :align-items "Center"
  :display "flex"
  }

  [:h1 :h2 :h3 {
    :flex 1
  }]

  [:.button :button [:&:last-child {
    :margin-right 0
  }]]

  [:&.inline-button {
    :align-items "baseline"
    }

    [:h1 :h2 :h3 {
      :flex "initial"
      :margin-right (rem 0.375)
    }]
  ]
]

[:.button-group [:a :button :button.small {
  :margin-right (rem 0.5)
}]]

[:img :video {
  :display "block"
}]

[:video {
  :max-width "100%"
}]

[:.portrait {
  :border [[(px 1) "solid" light-grey]]
  :border-radius "50%"
  }

  [:&.large {
    :margin [[0 "auto" (rem 1.3125)]]
    :width (px 175)
    :height (px 175)
  }]
]

[:ol :ul :li {
  :position "relative"
}]

[:.inline-block {
  :display "inline-block"
}]

[:.text-align-center {
  :text-align "center"
}]

[:.mini-margin-top {
  :margin-top (rem 0.375)
}]

[:.margin-top {
  :margin-top (rem 1)
}]

[:.medium-margin-top {
  :margin-top (rem 1.3125)
}]

[:.large-margin-top {
  :margin-top (rem 2.625)
}]

[:.huge-margin-top {
  :margin-top (rem 4)
}]

[:.medium-margin-right {
  :margin-right (rem 1.3125)
}]

[:.mini-margin-bottom {
  :margin-bottom (rem 0.375)
}]

[:.small-margin-bottom {
  :margin-bottom (rem 0.625)
}]

[:.margin-bottom {
  :margin-bottom (rem 1)
}]

[:.medium-margin-bottom {
  :margin-bottom (rem 1.3125)
}]

[:.large-margin-bottom {
  :margin-bottom (rem 2.625)
}]

[:.mini-margin-right {
  :margin-right (rem 0.375)
}]

[:.small-margin-right {
  :margin-right (rem 0.5)
}]

[:.margin-right {
  :margin-right (rem 1)
}]

[:.small-margin-left {
  :margin-left (rem 0.325)
}]

[:.huge-padding-top {
  :padding-top (rem 4)
}]

[:.small-padding-bottom {
  :padding-bottom (rem 0.625)
}]

[:.medium-padding-bottom {
  :padding-bottom (rem 1.3125)
}]

[:.large-padding-bottom {
  :padding-bottom (rem 2.625)
}]

[:hr {
  :background-color light-grey
  :border 0
  :height (px 1)
  :margin [[(rem 1.3125) 0 (rem 1.3125)]]
}]


))
