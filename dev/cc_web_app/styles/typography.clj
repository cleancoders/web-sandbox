 (ns cc-web-app.styles.typography
  (:refer-clojure :exclude [rem])
  (:require [cc-web-app.styles.core :refer :all]
            [cc-web-app.styles.palette :refer :all]
))

(def screen
(list

[:h1 {
  :font-family (font-family "calpsslim" "bold")
  :font-size (rem 3.975)
  :letter-spacing (px -0.5)
  :line-height (rem 3)
}]

[:h2 {
  :font-family (font-family "calps" "bold")
  :font-size (rem 1.8125)
  :line-height (rem 1.8125)
  }

  [:span {
    :font-size (rem 1)
  }]

  [:.fa-question-circle {
    :cursor "pointer"
    :vertical-align "top"
  }]

  [:.trademark {
    :font-family (font-family "calps" "light")
    :vertical-align "super"
  }]
]

[:h3 {
  :font-family (font-family "groldroundedslim" "medium")
  :font-size (rem 1.3125)
  :line-height (rem 1.5125)
}]

[:h4 {
  :font-family (font-family "calps" "light")
  :font-size (rem 1)
  :line-height (rem 1)
  :text-transform "uppercase"
}]

[:h5 {
  :font-family (font-family "groldroundedslim" "medium")
  :font-size (rem 1)
  }

  [:.fas :.far {
    :font-size (px 14)
    :margin-right (rem 0.375)
  }]
]

[:h6 :label :.small-caps :th {
  :font-family (font-family "calps" "regular")
  :font-size (rem 0.8)
  :letter-spacing (px 1)
  :text-transform "uppercase"
}]

[:p
  [:.fas :.far {
    :font-size (px 14)
    :margin-right (rem 0.375)
  }]
]

[:a {
  :color primary
  :text-decoration "none"
  }

  [:&:hover {
    :color primary-hover
    :cursor "pointer"
    :text-decoration "underline"
  }]
]

[:.error {
  :color error
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

[:small :.small {
  :display "inline-block"
  :font-size (em 0.75)
  :line-height (em 1.15)
  }

  [:&.promo {
    :color white
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
]

[:.note {
  :color light-grey
}]

[:em :i {
  :font-family (font-family "groldroundedslim" "extralightitalic")
}]

[:strong :b {
  :font-family (font-family "groldroundedslim" "medium")
}]

))
