(ns app.styles.forms
  (:refer-clojure :exclude [rem])
  (:require [app.styles.palette :refer :all]
            [app.styles.core :refer :all]
))

(def screen
(list

[:.card {
  :background-color "white"
  :border [[(px 1) "solid" light-grey]]
  :border-radius (px 3)
  :box-shadow "none"
  :width "100%"
  :height "auto"
  }

  [:.container {
    :margin [[(rem 1.3125) (rem 1)]]
    :padding 0
    }

    [:&.small {
      :margin [[(rem 0.625) (rem 0.5)]]
    }]
  ]
]

["input[type=text]" "input[type=password]" "input[type=email]" "input[type=number]" :textarea {
  :background-color white
  :border [[(px 1) "solid" light-grey]]
  :border-radius (px 3)
  :display "inline-block"
  :padding [[(rem 0.35) (rem 0.5) (rem 0.45)]]
  :width "100%"
  }

  [:&.inline {
    :width (px 70)
  }]

  [:&:focus {
    :box-shadow [[0 (px 0.5) (px 3) (px 3) primary]]
    :outline "none"
    :z-index 12
  }]

  ["&::-webkit-input-placeholder" {
    :color light-grey
    :font-family (font-family "groldroundedslim" "lightitalic")
  }]

  ["&::-moz-placeholder" {
    :color light-grey
    :font-family (font-family "groldroundedslim" "lightitalic")
  }]

  [:&:-moz-placeholder {
    :color light-grey
    :font-family (font-family "groldroundedslim" "lightitalic")
  }]

  [:&:-ms-input-placeholder {
    :color light-grey
    :font-family (font-family "groldroundedslim" "lightitalic")
  }]

  [:&:disabled :&.disabled {
    :border "none"
    :cursor "not-allowed"
  }]
]

["input[type=password]" {
  :font-family "Helvetica, Arial, sans-serif"
}]

[:button :.button {
  :background-color off-white
  :border 0
  :border-radius (px 3)
  :color black
  :display "inline-block"
  :font-family (font-family "groldroundedslim" "regular")
  :padding [[(em 0.25) (em 1) (em 0.375)]]
  :text-align "center"
  :text-decoration "none"
  :white-space "nowrap"
  }

  [:&:hover {
    :background-color light-grey
    :color black
    :cursor "pointer"
    :text-decoration "none"
  }]

  [:&.naked {
    :background-color "transparent"
    :padding 0
    :vertical-align "text-top"
    }

    [:&:hover {
      :background-color "transparent"
    }]
  ]

  [:&.primary {
    :background-color primary
    :color white
    }

    [:&:hover {
      :background-color primary-hover
    }]

    [:&.outline {
      :background-color white
      :border [[(px 1) "solid" primary]]
      :color primary
      }

      [:&:hover {
        :background-color white
        :border-color primary-hover
        :color primary-hover
      }]
    ]
  ]

  [:&.outline {
    :background-color white
    :border [[(px 1) "solid" medium-grey]]
    }

    [:&:hover {
      :background-color light-grey
    }]
  ]

  [:&.light {
    :font-family (font-family "groldroundedslim" "extralight")
  }]

  [:&.error {
    :background-color error
    :color white
    }

    [:&:hover {
      :background-color error-hover
      :cursor "pointer"
    }]
  ]

  [:&.disabled :&:disabled :&.disabled:hover :&:disabled:hover {
    :background-color off-white
    :color white
    :cursor "not-allowed"
    }

    [:.spinner {
      :background-image  "url('/images/gifs/spinner-medium-grey.gif')"
    }]
  ]

  [:&.center {
    :display "block"
    :margin-left "auto"
    :margin-right "auto"
    :max-width (px 320)
    :text-align "center"
  }]

  [:&.full-width {
    :width "100%"
  }]

  [:&.small {
    :font-size (px 15)
    :margin 0
    :padding [[(rem 0.1) (rem 0.375) ]]
    }

    [:.fas :.far {
      :font-size (px 12)
    }]
  ]

  [:&.sign-in {
    :align-items "center"
    :display "flex"
    :padding-top (rem 0.375)
    :width (px 270)
    }

    [:.text {
      :flex 1
    }]

    [:&.google
      [:.icon {
        :background      "url('/images/brands/google-g.png') transparent no-repeat"
        :background-size [[(px 24) (px 24)]]
        :display         "block"
        :margin-right    (rem 0.625)
        :width           (px 24)
        :height          (px 24)
      }]

      [:.text {
        :flex 1
        :font-family "'Roboto', sans-serif"
        :font-size (px 15)
        :font-weight "bold"
      }]
    ]

    [:&.clean-coders
      [:.icon {
        :background      "url('/images/logos/cc-emblem.png') transparent no-repeat"
        :background-size [[(px 24) (px 24)]]
        :display         "block"
        :margin-right    (rem 0.625)
        :width           (px 24)
        :height          (px 24)
      }]
    ]
  ]

  [:&.tall {
    :padding [[(rem 0.625) 0]]
  }]

  [:&.image {
    :background-color white
    :border [[(px 1) "solid" light-grey]]
    :color light-grey
    :padding [[0 0 (rem 0.375)]]
    :text-align "center"
    }

    [:img {
      :margin-bottom (rem 0.375)
    }]

    [:span {
      :display "block"
      :margin [[0 "auto"]]
    }]

    [:.small {
      :font-size (rem 0.6)
      :line-height (rem 0.85)
    }]

    [:&:hover {
      :color black
      :cursor "pointer"
    }]
  ]

  [:.fas :.far {
    :font-size (px 15)
    :margin-right (rem 0.25)
  }]

  [:&.unselected {
    :background-color "transparent"
    :border [[(px 1) "solid" light-grey]]
    :color black
  }]

  [:&.selected {
    :background-color primary
    :color white
    }

    [:&:hover {
      :background-color primary
      :color white
    }]
  ]

  [:&.backlog-active {
    :position "relative"
    }

    [:&:before {
      :border-color [["transparent" light-grey "transparent" "transparent"]]
      :border-style "solid"
      :border-width [[(px 13) (px 13) (px 13) 0]]
      :content "''"
      :position "absolute"
      :top (px 0)
      :left (px -34)
      :width 0
      :height 0
    }]

    [:&:after {
      :border-color [["transparent" white "transparent" "transparent"]]
      :border-style "solid"
      :border-width [[(px 13) (px 13) (px 13) 0]]
      :content "''"
      :position "absolute"
      :top (px 0)
      :left (px -33)
      :width 0
      :height 0
    }]
  ]

  [:&.disconnected {
    :width (px 35)
  }]

  [:&.fixed-size-130 {
    :flex [[0 0 (px 130)]]
    :height (px 37)
  }]

  [:.spinner {
    :background-image  "url('/images/gifs/spinner-white.gif')"
    :background-repeat "no-repeat"
    :background-size   [[(px 18) (px 18)]]
    :width             (px 18)
    :height            (px 18)
    :display           "inline-block"
    :vertical-align    "middle"
    :margin-bottom     (px 3)
  }]
]

[:.pocky-input {
  :align-items "center"
  :display "flex"
  :flex-direction "row"
  :width "100%"
  }

  [:input {
    :border-bottom-right-radius 0
    :border-top-right-radius 0
    :margin 0
  }]

  [:a.button :button {
    :border-bottom-left-radius 0
    :border-top-left-radius 0
    :margin 0
  }]
]

[:.validation-message-container
  [:input {
    :border-bottom-left-radius 0
    :border-bottom-right-radius 0
  }]

  [:.button :button {
    :border-bottom-left-radius 0
    :border-bottom-right-radius 0
  }]
]

[:.validation-message {
  :background-color light-grey
  :border-bottom-left-radius (px 3)
  :border-bottom-right-radius (px 3)
  :color white
  :padding (rem 0.375)
  }

  [:&.error {
    :background-color error
  }]

  [:&.success {
    :background-color primary
  }]

  [:&.note {
    :background-color "transparent"
    :color light-grey
  }]
]

[:.flash-root {
  :max-width (px 300)
  :position "absolute"
  ; :width    "100%"
  :right 0
  :z-index 2
}]

[:.flash-message {
  :background-color light-grey
  :margin-top (rem 3.25)
  :position "relative"
  }

  [:&.error {
    :background-color error
    :color            white
  }]

  [:&.success {
    :background-color primary
    :color            white
  }]

  [:&.warn {
    :background-color warn
    :color            white
  }]

  [:.container {
    :padding [[(rem 0.5) (rem 2) (rem 0.5) (rem 1.3125)]]
  }]

  [:span {
    :margin-right (rem 1)
    }

    [:&:hover {
      :cursor "pointer"
    }]

    [:a {
      :display "inline"
      :padding 0
    }]
  ]

  [:.flash-close {
    :position "absolute"
    :top (rem 0.5)
    :right 0
  }]
]

))
