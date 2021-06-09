(ns app.user-spec
  (:require-macros [speclj.core :refer [describe context it should-not-be-nil should-be-nil should= should-not
                                        should-not= should-have-invoked after before with-stubs with around
                                        stub should-contain should-not-contain should]]
                   [c3kit.wire.spec-helperc :refer [should-select should-not-select should-have-invoked-ws]])
  (:require
    [c3kit.wire.spec-helper :as wire-helper]
    [c3kit.wire.websocket :as ws]
    [app.layout :as layout]
    [app.page :as page]
    [app.spec-helper :as helper]
    [app.user :as sut]
    ))

(def earl {:id (rand-int 10000)})

(describe "User"

  (with-stubs)
  (wire-helper/stub-ajax)
  (wire-helper/stub-ws)
  (wire-helper/with-root-dom)
  (before (page/clear!))


  (it "install-and-connect!"
    (with-redefs [ws/start! (stub :ws/start!)]
      (sut/install-and-connect! earl)
      (should= earl (sut/current))
      (should-have-invoked :ws/start!)))

  )
