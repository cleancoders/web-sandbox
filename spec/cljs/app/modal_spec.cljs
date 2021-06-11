(ns app.modal-spec
  (:require-macros [speclj.core :refer [describe context it should-not-be-nil should-be-nil should= should-not
                                        should-not= should-have-invoked after before with-stubs with around
                                        should-contain should-not-contain should]]
                   [c3kit.wire.spec-helperc :refer [should-select should-not-select]])
  (:require
   [c3kit.apron.log :as log]
   [c3kit.wire.spec-helper :as wire-helper]
   [app.modal :as modal]
   [app.page :as page]
   [app.spec-helper :as helper]
   [speclj.core]
   ))


(describe "Modal"

  (wire-helper/with-root-dom)
  (before (page/clear!)
    (wire-helper/render [modal/modal]))

  (it "not open by default"
    (should-not-select "#-modal"))

  (it "default"
    (log/capture-logs
      (modal/install! :blah)
      (wire-helper/flush)
      (should-select "#-modal")
      (should-select "#-default-modal")))

  (it "hello"
    (modal/install! :modal/hello)
    (wire-helper/flush)
    (should-select "#-modal")
    (should-select "#-hello-modal"))

  (it "closing"
    (modal/install! :modal/hello)
    (wire-helper/flush)

    (modal/close!)
    (wire-helper/flush)

    (should-not-select "#-modal")
    (should-not-select "#-hello-modal")
    (should= nil @modal/state))

  (it "clicking backdrop closes"
    (modal/install! :modal/hello)
    (wire-helper/flush)
    (wire-helper/click! "#-modal-overlay")
    (should-not-select "#-modal"))

  (it "on-close action"
    (let [calls (atom 0)]
      (modal/install! :modal/hello :on-close #(swap! calls inc))
      (wire-helper/flush)
      (modal/close!)
      (should= 1 @calls)))
  )


