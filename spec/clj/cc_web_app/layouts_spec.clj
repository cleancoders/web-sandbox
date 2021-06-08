(ns cc-web-app.layouts-spec
  (:require
   [c3kit.wire.api :as api]
   [c3kit.wire.flash :as flash]
   [cc-web-app.config :as config]
   [cc-web-app.layouts :as sut]
   [speclj.core :refer :all]
   [speclj.stub :as stub]))

(def config :undefined)

(describe "layouts"

  (context "rich client handler"

    (with-stubs)
    (around [it]
      (with-redefs [sut/rich-client (stub :layout/rich-client)]
        (it)))

    (it "includes flash messages"
      (sut/web-rich-client (-> {:flash {:foo :bar}}
                             (flash/warn "Hello")))
      (should-have-invoked :layout/rich-client)
      (let [args (stub/last-invocation-of :layout/rich-client)
            payload (first args)]
        (should= "Hello" (-> payload :flash first :text))))
    )

  (context "rich client payload"

    (context "config"

      (with config (:config (sut/build-rich-client-payload {:session/key "session-key"
                                                            :anti-forgery-token "aft"})))

      (it "environment" (should= "development" (:environment @config)))
      (it "host" (should= config/host (:host @config)))
      (it "api/version" (should= (api/version) (:api-version @config)))
      )
    ))