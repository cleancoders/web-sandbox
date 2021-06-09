(ns app.styles.palette
  (:require [garden.color :as color]))

(def black (color/rgb 44 40 37))
(def transparent-black (color/rgba 44 40 37 0.93))
(def half-transparent-black (color/rgba 44 40 37 0.5))
(def light-shadow (color/rgba 0 0 0 0.1))
(def medium-shadow (color/rgba 0 0 0 0.2))
(def medium-grey (color/rgb 199 194 194))
(def light-grey (color/rgb 230 225 225))
(def off-white (color/rgb 247 245 245))
(def transparent-white (color/rgba 255 255 255 0.83))
(def less-transparent-white (color/rgba 255 255 255 0.6))
(def white (color/rgb 255 255 255))

(def primary (color/rgb 83 188 235))
(def primary-hover (color/rgb 107 201 245))
(def primary-dark (color/rgb 28 109 145))
(def transparent-primary (color/rgba 83 188 235 0.7))

(def minus-two-std-dev (color/rgb 220 242 251))
(def minus-one-std-dev (color/rgb 185 229 248))
(def zero-std-dev (color/rgb 150 215 244))
(def plus-one-std-dev (color/rgb 116 202 240))
(def plus-two-std-dev (color/rgb 80 189 236))

(def studio (color/rgb 28 108 145))

(def error (color/rgb 232 95 81))
(def error-hover (color/rgb 248 113 99))
(def success (color/rgb 116 213 89))
(def warn (color/rgb 255 192 59))
