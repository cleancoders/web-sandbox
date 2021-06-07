(ns cc-web-app.styles.icons
  (:refer-clojure :exclude [rem])
  (:require [cc-web-app-styles.palette :refer :all]
            [cc-web-app-styles.core :refer :all]
))

(def screen
(list

[:.spinner-medium-grey {
  :background-image  "url('/images/gifs/spinner-medium-grey.gif')"
  :background-repeat "no-repeat"
  :background-size   [[(px 40) (px 40)]]
  :width             (px 40)
  :height            (px 40)
  :display           "inline-block"
}]

))
