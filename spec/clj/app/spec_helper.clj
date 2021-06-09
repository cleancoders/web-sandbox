(ns app.spec-helper
  (:require
   [c3kit.apron.log :as log]
   [app.init :as init]
   [speclj.core :refer :all]
   ))

(log/warn!)
(init/configure-api!)
