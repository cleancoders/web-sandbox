(ns cc-web-app.main_spec
  (:require-macros [speclj.core :refer [describe context it should-not-be-nil should-be-nil should= should-not
                                        should-not= should-have-invoked after before with-stubs with around
                                        stub should-contain should-not-contain should should-not-have-invoked]]
                   [c3kit.apron.log :refer [capture-logs]])
  (:require
   [c3kit.apron.log :as log]
   [c3kit.apron.utilc :as util]
   [c3kit.wire.flash :as flash]
   [c3kit.wire.flashc :as flashc]
   [c3kit.wire.spec-helper :as wire-helper]
   ))

(describe "a test"
  (it "FIXME, I fail."
    (should= 0 0)))
