(ns cc-web-app.spec-helper
  (:refer-clojure :exclude [flush])
  (:require-macros [speclj.core :refer [around before stub with-stubs]])
  (:require
   [c3kit.apron.corec :as ccc]
   [c3kit.apron.log :as log]
   [cljsjs.react.dom.test-utils]                           ;; Brings in js/ReactTestUtils
   [cc-web-app.init :as init]
   [cc-web-app.page :as page]
   [speclj.core]
   ))

(log/warn!)
(init/configure-api!)

(defmethod page/render :helper/blank [_] [:p "Spec helper page"])
