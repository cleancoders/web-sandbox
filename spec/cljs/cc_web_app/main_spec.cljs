(ns cc-web-app.main_spec
  (:require-macros [speclj.core :refer [describe context it should-not-be-nil should-be-nil should= should-not
                                        should-not= should-have-invoked after before with-stubs with around
                                        stub should-contain should-not-contain should should-not-have-invoked]]
                   [c3kit.apron.log :refer [capture-logs]])
  (:require
   [accountant.core :as accountant]
   [c3kit.apron.log :as log]
   [c3kit.apron.utilc :as util]
   [c3kit.wire.flash :as flash]
   [c3kit.wire.flashc :as flashc]
   [c3kit.wire.spec-helper :as wire-helper]
   [cc-web-app.config :as config]
   [cc-web-app.main :as sut]
   [cc-web-app.page :as page]
   [cc-web-app.router :as router]
   [reagent.dom :as dom]
   ))

(describe "Main"

  (with-stubs)
  (wire-helper/stub-ajax)
  (wire-helper/stub-ws)
  (before (page/clear!))

  (context "main"

    (around [it]
      (with-redefs [sut/dispatch-and-render (stub :dispatch-and-render)
                    log/all! (stub :all!)]
        (log/capture-logs
          (it))))

    (it "installs flash"
      (let [flash (flashc/warn "Hello")]
        (sut/main (util/->transit {:flash [flash]}))
        (should= "Yes!" (flash/first-msg))))

    (it "installs config"
      (sut/main (util/->transit {:config {:environment "blah"}}))
      (should= "blah" (config/environment)))
    )

  )
