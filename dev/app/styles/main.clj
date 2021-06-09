(ns app.styles.main
  (:refer-clojure :exclude [rem])
  (:require
    [garden.def :as garden]
    [app.styles.components :as components]
    [app.styles.core :refer :all]
    [app.styles.forms :as forms]
    [app.styles.icons :as icons]
    [app.styles.layout :as layout]
    [app.styles.modals :as modals]
    [app.styles.palette :refer :all]
    [app.styles.print :as print]
    [app.styles.reset :as reset]
    [app.styles.responsive :as responsive]
    [app.styles.tables :as tables]
    [app.styles.typography :as typography]
    ))

(garden/defstyles screen

reset/screen

[:* :*:before :*:after {
  :box-sizing "border-box"
}]

["::selection" {
  :background-color primary
  :color white
}]

[:body :html {
  :width "100%"
  :height "100%"
}]

[:html {
  :font-size (px 19)
}]

[:body {
  :background-color white
  :color black
  :font-family (font-family "groldroundedslim" "extralight")
  :letter-spacing (px 0.25)
  :line-height (rem 1.35)
}]

typography/screen
layout/screen
icons/screen
forms/screen
modals/screen
tables/screen
components/screen
responsive/screen
print/page

(font-load "calps" "black")
(font-load "calps" "blackitalic")
(font-load "calps" "bold")
(font-load "calps" "bolditalic")
(font-load "calps" "extrablack")
(font-load "calps" "extrablackitalic")
(font-load "calps" "extrabold")
(font-load "calps" "extrabolditalic")
(font-load "calps" "extralight")
(font-load "calps" "extralightitalic")
(font-load "calps" "italic")
(font-load "calps" "light")
(font-load "calps" "lightitalic")
(font-load "calps" "medium")
(font-load "calps" "mediumitalic")
(font-load "calps" "regular")
(font-load "calps" "semilight")
(font-load "calps" "semilightitalic")
(font-load "calps" "thin")
(font-load "calps" "thinitalic")

(font-load "calpsslim" "black")
(font-load "calpsslim" "blackitalic")
(font-load "calpsslim" "bold")
(font-load "calpsslim" "bolditalic")
(font-load "calpsslim" "extrablack")
(font-load "calpsslim" "extrablackitalic")
(font-load "calpsslim" "extrabold")
(font-load "calpsslim" "extrabolditalic")
(font-load "calpsslim" "extralight")
(font-load "calpsslim" "extralightitalic")
(font-load "calpsslim" "italic")
(font-load "calpsslim" "light")
(font-load "calpsslim" "lightitalic")
(font-load "calpsslim" "medium")
(font-load "calpsslim" "mediumitalic")
(font-load "calpsslim" "regular")
(font-load "calpsslim" "semilight")
(font-load "calpsslim" "semilightitalic")
(font-load "calpsslim" "thin")
(font-load "calpsslim" "thinitalic")

(font-load "groldroundedslim" "black")
(font-load "groldroundedslim" "blackitalic")
(font-load "groldroundedslim" "bold")
(font-load "groldroundedslim" "bolditalic")
(font-load "groldroundedslim" "extrablack")
(font-load "groldroundedslim" "extrablackitalic")
(font-load "groldroundedslim" "extrabold")
(font-load "groldroundedslim" "extrabolditalic")
(font-load "groldroundedslim" "extralight")
(font-load "groldroundedslim" "extralightitalic")
(font-load "groldroundedslim" "italic")
(font-load "groldroundedslim" "light")
(font-load "groldroundedslim" "lightitalic")
(font-load "groldroundedslim" "medium")
(font-load "groldroundedslim" "mediumitalic")
(font-load "groldroundedslim" "regular")
(font-load "groldroundedslim" "semilight")
(font-load "groldroundedslim" "semilightitalic")
(font-load "groldroundedslim" "thin")
(font-load "groldroundedslim" "thinitalic")

)
