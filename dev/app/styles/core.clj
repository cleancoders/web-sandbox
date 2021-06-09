(ns app.styles.core
  (:refer-clojure :exclude [rem])
  (:require
    [app.styles.palette :refer :all]
    [garden.units :as units]
))

(defn px [n] (units/px n))
(defn em [n] (units/em n))
(defn rem [n] (units/rem n))
(defn percent [n] (units/percent n))

(defn font-load [face weight]
  ["@font-face" {
    :font-family (str "'" face "-" weight "'")}
    {:src        (str "url('/fonts/" face "-" weight ".woff2') format('woff2'),
                       url('/fonts/" face "-" weight ".woff') format('woff')")
    :font-weight "normal"
    :font-style  "normal"
  }]
)

(defn font-family [face weight]
  (str "'" face "-" weight "', Helvetica, sans-serif"))

;(def small-caps {
;  :font-size      (rem 0.6725)
;  :letter-spacing (px 1)
;  :text-transform "uppercase"
;})

(def overflow-ellipsis {
  :overflow      "hidden"
  :text-overflow "ellipsis"
  :white-space   "nowrap"
  :width         "100%"
})

;(defn icon [url width height] {
;  :background-image  (str "url('/images/icons/" url ".png')")
;  :background-repeat "no-repeat"
;  :background-size   [[(px width) (px height)]]
;  :width             (px width)
;  :height            (px height)
;  :display           "inline-block"
;})

(defn hover [& [url]]
  [:&:hover
   (cond->
    {:cursor "pointer"}
    url
    (assoc :background-image (str "url('/img/icons/" url ".png')")))]
)

;(defn video-carousel-width [number-of-videos]
;  (px (+ (* 240 number-of-videos) (* 16 (- number-of-videos 1))))
;)

;(defn truncate-multiple-lines [font-size line-height number-of-lines] {
;  :display "block"
;  :height (rem (* line-height number-of-lines))
;  :font-size (rem font-size)
;  :line-height  (rem line-height) ;(rem 1)
;  :-webkit-line-clamp number-of-lines
;  :-webkit-box-orient "vertical"
;  :overflow "hidden"
;  :text-overflow "ellipsis"
;  :white-space "nowrap"
;})

;   @-moz-document url-prefix() {
;     overflow: hidden;
;     position: relative;

;     &:before {
;       background: transparent;
;       position: absolute;
;       right: 0;
;       bottom: 0;
;       float: right;
;       content: '...';
;       margin-left: -3rem;
;       width: 3rem;
;     }

;     &:after {
;       content: '';
;       background: transparent;
;       position: absolute;
;       height: 50px;
;       width: 100%;
;       z-index: 1;
;     }
;   }
; }
