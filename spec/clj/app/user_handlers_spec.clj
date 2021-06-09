(ns app.user-handlers-spec
  (:require
   [c3kit.wire.ajax :as ajax]
   [c3kit.wire.websocket :as websocket]
   [app.user-handlers :as sut]
   [speclj.core :refer :all]
   ))

(def request :undefined)

(describe "User Handlers"

  (with-stubs)

  (context "ajax-csrf-token"

    (it "returns the csrf-token"
      (let [response (sut/ajax-csrf-token {:session/key "abc123"
                                           :session     {:ring.middleware.anti-forgery/anti-forgery-token "xyz789"}})]
        (should= 200 (:status response))
        (should= :ok (ajax/status response))
        (should= "abc123" (:csrf-token (ajax/payload response)))
        (should= "xyz789" (:anti-forgery-token (ajax/payload response)))))

    (it "puts something in the session to make sure one exists"
      (let [response (sut/ajax-csrf-token {:session/key "abc123" :session {:foo :bar}})]
        (should= :bar (-> response :session :foo))
        (should-contain :csrf? (:session response))))
    )

  (context "websocket-open"

    (around [it]
      (with-redefs [websocket/get-handler (delay (stub :websocket/get-handler {:return :connected}))
                    ;websocket/post-handler (delay (stub :websocket/post-handler {:return :connected}))
                     ]
        (it)))

    (it "get - no user"
      (should= nil (sut/websocket-open-get {}))
      (should-not-have-invoked :websocket/get-handler))

    ;(it "post - no user"
    ;  (should= nil (sut/websocket-open-post {}))
    ;  (should-not-have-invoked :websocket/post-handler))

    (it "get - with user"
      (let [response (sut/websocket-open-get {:session {:user {:id "id123"}}})]
        (should= :connected response)
        (should-have-invoked :websocket/get-handler)))

    ;(it "post - with user"
    ;  (let [response (sut/websocket-open-post {:session {:user @holdem/earl}})]
    ;    (should= :connected response)
    ;    (should-have-invoked :websocket/post-handler)))

    )
  )