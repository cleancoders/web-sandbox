(ns app.styles.modals
  (:refer-clojure :exclude [rem])
  (:require [app.styles.palette :refer :all]
            [app.styles.core :refer :all]
))

(def screen
(list

; Modal
; ---------------------------------------------------

[:.no-scroll {
  :overflow "hidden"
}]

[:.modal-background {
  :background-color transparent-black
  :overflow "auto"
  :position "fixed"
  :top 0
  :left 0
  :width "100%"
  :height "100%"
  :z-index 3
}]

[:.modal-overlay {
  :width "100%"
  :height "100%"
  :position "absolute"
  }

  [:span {
    :color white
    :font-size (rem 1.8125)
    :line-height (rem 1.3125)
    :padding (rem 1)
    }

    [:&:hover {
      :color light-grey
      :cursor "pointer"
    }]
  ]
]

[:.modal {
  :max-width (px 380)
  :margin [[(px 100) "auto"]]
  :position "relative"
  }

  [:&.larger-width {
    :max-width (px 500)
    :width "90%"
  }]

  [:&.medium-width {
    :max-width (px 752)
    :width "90%"
  }]

  [:&.full-width {
    :max-width (px 1080)
    :width "90%"
    }

    [:.container {
      :margin (rem 1)
    }]
  ]

  [:img {
    :max-width "100%"
  }]
]

; Contextual Menu
; ---------------------------------------------------

[:.contextual-menu-anchor {
  :position "relative"
  }

  [:.button.active {
    :box-shadow "inset 3px 3px 6px #47a0c8, inset -3px -3px 6px #5fd8ff"
  }]
]

[:.contextual-menu {
  :box-shadow       [[0 0 (px 6) medium-shadow]]
  :color            black
  :cursor           "auto"
  :font-family      (font-family "groldroundedslim" "extralight")
  :font-size        (rem 1)
  :font-variant     "normal"
  :font-weight      400
  :letter-spacing   0
  :line-height      (rem 1.3125)
  :position         "absolute"
  :left             0
  :top              (px 30)
  :text-align       "left"
  :text-transform   "none"
  :width            (px 450)
  :z-index          4
  }

  [:&:before {
    :background-color "transparent"
    :content "''"
    :cursor "default"
    :display "block"
    :position "fixed"
    :top 0
    :right 0
    :bottom 0
    :left 0
    :z-index -1
  }]

  [:.card {
    :padding (rem 1)
  }]

  [:.card:before {
    :border-color [["transparent" "transparent" light-grey "transparent"]]
    :border-style "solid"
    :border-width [[0 (px 8) (px 10) (px 8)]]
    :content "''"
    :position "absolute"
    :top (px -9)
    :left (px 24)
    :width 0
    :height 0
  }]

  [:.card:after {
    :border-color [["transparent" "transparent" white "transparent"]]
    :border-style "solid"
    :border-width [[0 (px 8) (px 10) (px 8)]]
    :content "''"
    :position "absolute"
    :top (px -7)
    :left (px 24)
    :width 0
    :height 0
  }]
]

))
