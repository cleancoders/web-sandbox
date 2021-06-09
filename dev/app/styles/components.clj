(ns app.styles.components
  (:refer-clojure :exclude [rem])
  (:require [app.styles.palette :refer :all]
            [app.styles.core :refer :all]
            [garden.def :refer [defrule defkeyframes]]
))
 
(defn card-front [value]
  [(keyword (str ".card.front-" value)) {
    :background-image (str "url('/images/card/" value ".png')")
  }]
)

(defkeyframes disconnected
  [:from {
    :color error
    :font-size (px 15)
  }]

  [:to {
    :color error-hover
    :font-size (px 20)
  }]
)

(def screen
(list

; User Menu
; ---------------------------------------------------

[:.user-menu {
  :color white
  :position "fixed"
  :top 0
  :right 0
  :z-index 3
  }

  [:div {
    :align-items "center"
    :display "flex"
    :padding [[(rem 0.625)]]
    }

    [:&:hover {
      :cursor "auto"
    }]
  ]

  [:span {
    :display "none"
    :margin-right (rem 0.625)
  }]

  [:img {
    :margin-left "auto"
    :margin-right 0
    :max-width (rem 2)
    :width "100%"
    }

    [:&:hover {
      :cursor "pointer"
    }]
  ]

  [:ul {
    :background-color black
    :color white
    :display "none"
    }

    [:&:hover {
      :cursor "pointer"
    }]
  ]

  [:li {
    :border-top [[(px 1) "solid" light-grey]]
    :display "flex"
    :padding [[(rem 0.375) (rem 0.625)]]
    }

    [:&:hover {
      :background-color primary
    }]
  ]

  [:&:hover {
    :box-shadow [[(px -2) (px 2) (px 6) medium-shadow]]
    :cursor "pointer"
    }

    [:div {
      :background-color black
    }]

    [:span {
      :display "inline-block"
    }]

    [:img {
      :box-shadow "none"
    }]

    [:ul {
      :display "block"
    }]
  ]

  [:&.box-shadow [:img {
    :box-shadow [[0 0 (px 40) (px 20) white]]
  }]]
]


; Home
; ---------------------------------------------------

[:.home {
  :height "auto"
  :min-height "100%"
  }

  [:img {
    :max-width "100%"
  }]

  [:h2 {
    :letter-spacing (px 2)
    :text-transform "uppercase"
  }]
]

[:.rooms-remaining {
  :border [[(px 1) "solid" light-grey]]
  :border-radius (px 3)
  :padding [[(rem 0.25) (rem 0.5)]]
  }

  [:&.error {
    :background-color error
    :color white
  }]
]

[:&.endorsement
  [:.column:first-child {
    :color white
    :text-align "center"
  }]

  [:.column:last-child {
    :flex 2
  }]

  [:.card {
    :border-radius (px 15)
    }

    [:&:before {
      :border-color [["transparent" light-grey "transparent" "transparent"]]
      :border-style "solid"
      :border-width [[(px 8) (px 10) (px 8) 0]]
      :content "''"
      :position "absolute"
      :top (px 25)
      :left (px -10)
      :width 0
      :height 0
    }]

    [:&:after {
      :border-color [["transparent" white "transparent" "transparent"]]
      :border-style "solid"
      :border-width [[(px 8) (px 10) (px 8) 0]]
      :content "''"
      :position "absolute"
      :top (px 25)
      :left (px -8)
      :width 0
      :height 0
    }]

    [:p {
      :color black
      :font-size (px 17)
      :line-height (px 22)
    }]
  ]

  [:.container {
    :margin [[(rem 0.5) (rem 0.625)]]
  }]

  [:small {
    :color white
    :display "block"
    :text-align "center"
  }]
]

[:.youtube-container {
  :height 0
  :overflow "hidden"
  :padding-bottom "56.25%"
  :position "relative"
  }

  [:iframe {
    :position "absolute"
    :left 0
    :top 0
    :height "100%"
    :width "100%"
  }]
]

; Room
; ---------------------------------------------------

[:.room {
  :display "flex"
  :flex-direction "row"
  :overflow "hidden"
  :width "100%"
  :z-index 0
  :background-image "url('/images/backgrounds/room-background.jpg')"
  :background-size "cover"
  :height "100vh"
  }

  [:.stories-scroll-container {
    :height "calc(100vh - 220px)"
    :overflow-y "auto"
  }]

  [:.card-table {
    :display "flex"
    :flex [[0 0 "65%"]]
    :flex-direction "column"
    :overflow "hidden"
    :position "relative"
  }]

  [:.card-table-scroll-container {
    :flex [[0 0 "calc(100vh - 210px)"]]
    :overflow-y "auto"
    :padding [[0 (rem 1.3125)]]
  }]
]

[:.story-sidebar {
  :background-color "rgba(255,255,255,0.15)"
  :box-shadow [[0 0 (px 8) medium-shadow]]
  :display "flex"
  :flex [[0 0 "35%"]]
  :flex-direction "column"
  }

  [:.background {
    :opacity 0.1
    :position "fixed"
    :bottom 0
    :width "30vw"
    :height "auto"
    :z-index 0
  }]

  [:.status-bar-container {
    :background-color white
    :height (rem 4)
    :padding [[(rem 0.625) (rem 1) (rem 1.3125) 0]]
    :position "relative"
  }]

  [:.status-bar {
    :background-color white
    :border [[(px 1) "solid" light-grey]]
    :border-left "none"
    :border-bottom-right-radius (px 3)
    :border-top-right-radius (px 3)
    :box-shadow [[0 0 (px 6) light-shadow]]
    :min-width "103%"
    :padding [[(rem 0.375) (rem 1)]]
    :position "absolute"
    :white-space "nowrap"
    :width "auto"
    :z-index 1
    }

    [:span :button {
      :margin-right (rem 0.5)
      }

      [:&:last-child {
        :margin 0
      }]
    ]
  ]

  [:.column-header {
    :background-color white
    :padding [[(rem 1.25) (rem 1) (rem 1)]]
  }]

  [:.room-name {
    :background-color white
    :padding-left (rem 1)
    :padding-right (rem 1)
    }

    [:.fa-cog {
      :font-size (rem 0.75)
      }

      [:&:hover {
        :color primary
        :cursor "pointer"
      }]
    ]

    [:h3 {
      :overflow "hidden"
      :text-overflow "ellipsis"
      :white-space "nowrap"
      :width (px 10)
    }]
  ]

  [:.fa-caret-right {
    :margin-left (rem 0.25)
    :margin-right 0
  }]

  [:.add-story {
    :padding [[(rem 1) (rem 1) (rem 0.375) (rem 1)]]
    :z-index 1
  }]

  [:.totals {
    :padding-left (rem 1)
  }]

  [:.story:hover {
    :color primary
    :cursor "grab"
  }]

  [:.story:last-child {
    :box-shadow [[0 (px 3) (px 6) light-shadow]]
  }]
]

[:.backlog {
  :box-shadow "none"
  :left (px 100)
  :top (px -14)
  :width (px 500)
  }

  [:&:before {
    :width 0
    :border-width [[(px 12) (px 15) (px 12) 0]]
    :top (px 16)
    :border-color [["transparent" light-grey "transparent" "transparent"]]
    :height 0
    :border-style "solid"
    :position "absolute"
    :content "''"
    :left (px -15)
  }]

  [:&:after {
    :width 0
    :border-width [[(px 12) (px 15) (px 12) 0]]
    :top (px 16)
    :border-color [["transparent" white "transparent" "transparent"]]
    :height 0
    :border-style "solid"
    :position "absolute"
    :content "''"
    :left (px -13)
  }]

  [:.hgroup {
    :background-color white
    :border-left [[(px 1) "solid" light-grey]]
    :border-right [[(px 1) "solid" light-grey]]
    :border-top [[(px 1) "solid" light-grey]]
    :border-top-left-radius (px 3)
    :border-top-right-radius (px 3)
    :padding [[(rem 0.625) (rem 1)]]
  }]

  [:.story {
    :border-left [[(px 1) "solid" light-grey]]
    :border-right [[(px 1) "solid" light-grey]]
    }

    [:.name:hover [:span {
      :max-width (px 450)
    }]]
  ]

  [:li:last-child [:.story :&.dropzone {
    :border-bottom-left-radius (px 3)
    :border-bottom-right-radius (px 3)
  }]]

]

disconnected

[:.fa-exclamation-triangle {
  :animation [[disconnected "1s" :infinite :alternate]]
}]

[:.story :.totals {
  :align-items "center"
  :background-color white
  :border-bottom [[(px 1) "solid" light-grey]]
  :box-shadow "none"
  :display "flex"
  :height (px 44)
  :position "relative"
  :transition "box-shadow 0.5s ease-in-out"
  }

  [:&:hover {
    :color black
    :cursor "auto"
  }]

  [:&.active {
    :background-color primary
    :color white
    }

    [:&:hover {
      :color white
    }]

    [:.fas:hover {
      :color white
      :cursor "pointer"
    }]

    [:.name:hover [:span {
      :background-color primary
      :box-shadow [[(px 20) 0 (px 10) (px -9) primary]]
    }]]
  ]

  [:&.moved {
    :box-shadow [[0 0 (px 3) (px 3) primary]]
    :z-index 1
  }]

  [:&.disabled {
    :color light-grey
    }

    [:.fas:hover {
      :color light-grey
      :cursor "not-allowed"
    }]
  ]

  [:&.excluded
    [:.estimate :.estimate-total {
      :color light-grey
    }]
  ]

  [:.fas {
    :padding [[(rem 0.43) (rem 0.5) (rem 0.43) (rem 1)]]
    :font-size (px 14)
    :line-height (px 27)
    }

    [:&:hover {
      :color primary
      :cursor "pointer"
    }]
  ]

  [:.story-content {
    :align-items "center"
    :display "flex"
    :flex 1
  }]

  [:.title {
    :flex 1
    :font-family (font-family "calps" "bold")
    :font-size (rem 1.3125)
    :text-transform "uppercase"
  }]

  [:.name
    overflow-ellipsis
    {:flex 1
    :position "relative"
    :width (px 10)}

    [:span {
      :padding [[(px 9) (rem 1) (px 9) 0]]}
      overflow-ellipsis
    ]
  ]

  [:.name:hover {
    :height (px 44)
    :overflow "initial"
    }

    [:span {
      :background-color white
      :box-shadow [[(px 20) 0 (px 10) (px -9) white]]
      :height (px 42)
      :max-width "calc(35vw - 50px)"
      :position "absolute"
      :top (px 1)
      :width "auto"
      :z-index 1
    }]
  ]

  [:.estimate {
    :flex [[0 0 (px 65)]]
    :font-family (font-family "calps" "light")
    :font-size (rem 1.3125)
    :line-height (rem 1.5)
    :position "relative"
    :text-align "right"
  }]

  [:.estimate-total {
    :flex [[0 0 (px 95)]]
    :font-family (font-family "calps" "light")
    :font-size (rem 1.8125)
    :line-height (rem 1.8125)
    :padding-right (rem 1)
    :text-align "right"
  }]
]

[:&.dropzone {
  :background-color white
  :box-shadow [["inset" (px 2) (px 2) (px 5) light-grey]]
  :height (px 45)
}]

[:#dragged-item {
  :box-shadow [[(px 5) (px 5) (px 10) light-grey]]
  :color white
  :cursor "grabbing"
  :transform "rotate(2deg)"
  :width "30vw"
  :z-index 2
  }

  [:&:hover {
    :cursor "grabbing"
  }]

  [:&.sandbox-positioner {
    :position "fixed"
    :top "30%"
    :left "15%"
  }]

  [:.estimate :.estimate-total {
    :display "block"
  }]
]

[:.totals {
  :align-items "baseline"
  :background-color white
  :padding [[(rem 0.375) 0 (rem 0.5) 0]]
  :position "sticky"
  :top 0
  :z-index 1
  }

  [:&:hover {
    :color black
    :cursor "auto"
  }]

  [:.estimate {
    :font-family (font-family "calps" "medium")
    :height (px 28)
  }]

  [:.estimate:after {
    :border-radius "50%"
    :content "''"
    :display "block"
    :font-size (em 0.55)
    :line-height (em 1.2)
    :text-align "center"
    :position "absolute"
    :top (px -10)
    :right (px -3)
    :width (px 16)
    :height (px 16)
  }]

  [:.estimate.optimistic:after {
    :content "'O'"
  }]

  [:.estimate.realistic:after {
    :content "'R'"
  }]

  [:.estimate.pessimistic:after {
    :content "'P'"
  }]

  [:.estimate-total {
    :font-family (font-family "calps" "bold")
  }]
]

[:.card-table
  [:.background {
    :opacity 0.1
    :position "fixed"
    :width "100%"
    :height "auto"
    ; :z-index -1
  }]

  [:.spectators {
    :display "flex"
    :flex-direction "row"
    :justify-content "center"
    :overflow-x "auto"
    }

    [:li {
      :flex [[0 0 (px 100)]]
      :margin [[(rem 1) (rem 1) 0 0]]
      :text-align "center"
    }]

    [:.player-info [:img {
      :width (px 65)
      :height (px 65)
    }]]
  ]

  [:.player-info {
    :position "relative"
    :z-index 1
    }

    [:img {
      :border [[(px 1) "solid" light-grey]]
      :border-radius "50%"
      :display "block"
      :margin [[0 "auto" (rem 0.5)]]
      :width (px 130)
      :height (px 130)
    }]

    [:h4 {
      :color white
    }]
  ]

  [:.estimator {
    :align-items "center"
    :display "flex"
    :flex-direction "row"
    :margin-bottom (rem 2.625)
    :max-width (px 770)
    }

    ["input[type=checkbox]" {
      :margin-right (rem 1)
    }]

    [:.cards-container {
      :margin-right (rem 0.5)
      :z-index 1
    }]

    [:.status {
      :color white
      ; :display "flex"
      :flex [[0 0 (px 140)]]
      :flex-direction "column"
      :font-family (font-family "calps" "bold")
      :font-size (rem 1.3125)
      :height (px 197)
      :justify-content "center"
      :padding (rem 0.625)
      :text-align "center"
      :text-transform "uppercase"
      }

      [:button {
        ; :display "block"
        :font-size (rem 1)
        :margin-bottom (rem 0.375)
        :width "100%"
      }]
    ]

    [:&.me
      [:.player-info:hover {
        :cursor "pointer"
        :opacity 0.8
      }]

      [:.card:before {
        :color white
        :content "''"
        :dislay "block"
        :font-family (font-family "calps" "light")
        :text-align "center"
        :text-transform "uppercase"
        :position "absolute"
        :top (px -28)
        :left (px 14)
        :width (px 110)
        :height (px 45)
        :z-index -1
      }]

      [".card-container:nth-child(1) .card:before" {
        :content "'Optimistic'"
      }]

      [".card-container:nth-child(2) .card:before" {
        :content "'Realistic'"
      }]

      [".card-container:nth-child(3) .card:before" {
        :content "'Pessimistic'"
      }]
    ]
  ]

  [:.hand {
    :padding (rem 1)
    :position "absolute"
    :bottom 0
    :width "100%"
    :z-index 1
    }

    [:.card-container:hover {
      :cursor "pointer"
      }

      [:.card {
        :bottom (px 66)
        :position "absolute"
      }]
    ]
  ]

  [:.cards-container {
    :position "relative"
    :width "100%"
    :height (px 197)
  }]

  [:.card-container {
    :position "absolute"
    :width (px 143)
    :height (px 197)
  }]

  [:.card {
    :background-color white
    :background-repeat "no-repeat"
    :background-size [[(px 135) (px 189)]]
    :border-color white
    :border-radius (px 10)
    :border-style "dashed"
    :border-width (px 3)
    :box-shadow [[0 0 (px 6) light-shadow]]
    :display "block"
    :position "absolute"
    :bottom 0
    :transition [["bottom" "0.3s"]]
    :transition-timing-function "ease"
    :width (px 143)
    :height (px 197)
    }

    [:&.unset {
      :border-color light-grey
      :border-style "dashed"
      :border-width (px 3)
    }]

    [:&.back {
      :background-image "url('/images/card/back.png')"
    }]

    [:&.selectable:hover {
      :border-color primary
      :cursor "pointer"
    }]

    [:&.focus :&:focus {
      :border-color primary
      :box-shadow [[0 (px 0.5) (px 3) (px 3) primary]]
      :outline "none"
    }]
  ]

  (for [value ["00" "25" "50" "01" "02" "03" "04" "05" "06" "07" "08" "09" "10" "11" "12" "13" "14" "15" "16" "21" "25" "32" "34" "50" "55" "64" "99"]]
    (card-front value))
]

[:.room.crowded
  [:.card-table
    [:.card-table-scroll-container {
      :flex [[0 0 "calc(100vh - 80px)"]]
    }]

    [:.estimators {
      :display "flex"
      :flex-direction "row"
      :flex-wrap "wrap"
      :justify-content "center"
    }]

    [:.estimator {
      :flex [[0 0 (px 570)]]
      :margin [[0 (rem 1) (rem 1.8125) 0]]
      :max-width (px 570)
      }

      [:.status {
        :flex [[0 0 (px 150)]]
        :height (px 120)
        :padding 0
        }

        [:h1 {
          :font-size (rem 2.45)
          :margin-bottom 0
        }]

        [:h4 {
          :font-size (rem 1)
          :line-height 1
        }]

        [:button {
          :font-size (rem 0.8)
          :margin-bottom 0
          :margin-right (rem 0.5)
          :padding [[(rem 0.1) (rem 0.625)]]
          :width "auto"
          }

          [:&:last-child {
            :margin 0
          }]
        ]
      ]

      [:&.me [:.card:before {
        :font-size (px 16)
        :left 0
        :width (px 79)
      }]]
    ]

    [:.player-info [:img {
       :width (px 100)
       :height (px 100)
    }]]

    [:.spectators [:.player-info [:img {
      :width (px 50)
      :height (px 50)
    }]]]

    [:.hand {
      :bottom (px -58)
      }

      [:.card-container:hover [:.card {
        :bottom (px 40)
      }]]
    ]

    [:.cards-container {
      :height (px 120)
    }]

    [:.card-container {
      :width (px 85)
      :height (px 120)
    }]

    [:.card {
      :background-size [[(px 78) (px 114)]]
      :width (px 85)
      :height (px 120)
      :border-radius (px 6)
    }]
  ]
]

; Tooltips
; ---------------------------------------------------

[:.tooltip-anchor {
  :position "relative"
}]

[:.tooltip {
  :background-color white
  :border           [[(px 1) "solid" light-grey]]
  :border-radius    (px 3)
  :box-shadow       [[0 0 (px 6) medium-shadow]]
  :color            black
  :font-family      (font-family "groldroundedslim" "extralight")
  :font-size        (rem 1)
  :font-variant     "normal"
  :font-weight      400
  :letter-spacing   0
  :line-height      (rem 1.3125)
  :padding          (rem 1)
  :position         "absolute"
  :left             (px -10)
  :top              (px 24)
  :text-transform   "none"
  :width            (px 250)
  :z-index          2
  }

  [:&:before {
    :border-color [["transparent" "transparent" light-grey "transparent"]]
    :border-style "solid"
    :border-width [[0 (px 8) (px 10) (px 8)]]
    :content "''"
    :position "absolute"
    :top (px -11)
    :left (px 10)
    :width 0
    :height 0
  }]

  [:&:after {
    :border-color [["transparent" "transparent" white "transparent"]]
    :border-style "solid"
    :border-width [[0 (px 8) (px 10) (px 8)]]
    :content "''"
    :position "absolute"
    :top (px -9)
    :left (px 10)
    :width 0
    :height 0
  }]

  [:&.wide  {
    :top (px 30)
    :width (px 430)
  }]
]

; Export Room
; ---------------------------------------------------

[:.export-room
  [:.totals {
    :padding-left 0
  }]

  [:.story
    [:&:hover [:.estimate :.estimate-total {
      :display "block"
    }]]
  ]


  [:ol.stories [:li:last-child {
    :box-shadow "none"
  }]]
]


; Admin Dashboard
; ---------------------------------------------------

[:.admin-dashboard
  [:.list {
    ; :flex [[0 0 (px 350)]]
  }]

  [:.details {
    :flex 2
    ; :margin-top (px 142)
    ; :max-width (px 960)
    }

    ; [:.column:last-child {
    ;   :flex 2
    ; }]

    [:.portrait {
      :max-width (px 65)
    }]
  ]

  [:.scrollable-column {
    :max-height "calc(100vh - 140px)"
  }]
]

[:.navigation-tabs
  [:.column {
    :background-color light-grey
    :color medium-grey
    :margin 0
    }

    [:&:hover {
      :color black
      :cursor "pointer"
    }]

    [:&.active {
      :background-color white
      :border-radius (px 3)
      :color black
      }

      [:&:hover {
        :cursor "auto"
      }]
    ]

    [:&.disabled {
      :background-color off-white
      :color white
      }

      [:&:hover {
        :cursor "not-allowed"
      }]
    ]
  ]

  [:.container {
    :margin (rem 1)
  }]

  [:h3 {
    :line-height (rem 1)
  }]
]

[:.user-grid {
  :display "grid"
  :grid-template-columns "repeat(auto-fill, minmax(80px, 1fr))"
  :grid-gap [[(rem 1) (rem 0.25)]]
  }

  [:li {
    :opacity 0.3
    :text-align "center"
    }

    [:&.active {
      :opacity 1
    }]
  ]

  [:img {
    :margin [[0 "auto" (rem 0.375)]]
  }]

  [:h4
    overflow-ellipsis
  ]
]

[:ul.interactive
  [:li {
    :align-items "center"
    :border-bottom [[(px 1) "solid" light-grey]]
    :display "flex"
    :padding [[(rem 0.375) 0]]
  }]

  [:&.full-width [:li {
    :padding [[(rem 0.375) (rem 0.625)]]
  }]]

  [:li:last-child {
    :border-bottom "none"
  }]

  [:li.active {
    :background-color primary
    :color white
  }]

  [:li.active:after {
    :border-color [["transparent" "transparent" "transparent" white]]
    :border-style "solid"
    :border-width [[(px 4.5) 0 (px 4.5) (px 7.8)]]
    :content "''"
    :display "inline-block"
    :height 0
    :position "absolute"
    :top (px 14)
    :right (px 12)
    :width 0
  }]

  [:.fas {
    :color light-grey
    :margin-right (rem 0.5)
    }

    [:&:hover {
      :color primary
      :cursor "pointer"
    }]
  ]

  [:.name {
    :flex 1
    :margin-right (rem 0.5)}
    overflow-ellipsis
  ]

  [:button :.button {
    :font-size (rem 0.8)
    :padding [[0 (rem 0.625)]]
  }]
]

[:ul.traversable
  [:li:hover {
    :background-color primary-hover
    :color white
    :cursor "pointer"
  }]
]



))
