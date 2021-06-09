(ns app.styles.icons
  (:refer-clojure :exclude [rem])
  (:require [app.styles.palette :refer :all]
            [app.styles.core :refer :all]
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
