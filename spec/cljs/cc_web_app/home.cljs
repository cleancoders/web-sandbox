(ns cc-web-app.home
  (:require-macros [speclj.core :refer [describe context it should-not-be-nil should-be-nil should= should-not
                                        should-not= should-have-invoked after before before-all with-stubs with around
                                        stub should-contain should-not-contain should]]
                   [c3kit.wire.spec-helperc :refer [should-select should-not-select]])
  (:require
   [c3kit.bucket.db :as db]
   [c3kit.wire.spec-helper :as wire-helper]
   [c3kit.wire.websocket :as ws]
   [cc-web-app.home :as sut]
   [cc-web-app.layout :as layout]
   [cc-web-app.page :as page]
   [cc-web-app.spec-helper :as helper]
   ))

(describe "Home"

  (with-stubs)
  (wire-helper/stub-ajax)
  (wire-helper/stub-ws)
  (wire-helper/with-root-dom)
  (before (db/clear!)
    (page/clear!)
    (page/install-page! :home)
    (wire-helper/render [layout/default]))

  (context "no user"

    (it "sign in buttons"
      (should-select "#-cleancoders-signin-button")
      (should-select "button.sign-in.google"))

    (it "no create room button"
      (should-not-select "#-create-room-button"))

    )

  (context "with user"

    (before
      (wire-helper/flush))

    (it "no signin buttons"
      (should-not-select "#-cleancoders-signin-button")
      (should-not-select "button.sign-in.google"))

    (it "create room button"
      (should-select "#-create-room-button")
      (wire-helper/click! "#-create-room-button")
      (should-have-invoked :ws/call!)
      (should= :room/create (wire-helper/last-ws-call-id)))

    (it "shows spinner instead of room list if user data hasn't been fetched"
      (wire-helper/flush)
      (should-not-select "#-room-list")
      (should-select "#-room-list-spinner"))

    (it "lists rooms (based on occupants with user)"
      (should-select "#-room-list")
      (should-not-select "#-room-list-spinner"))

    (it "disconnected button - off"
      (reset! ws/open? true)
      (wire-helper/flush)
      (should-not-select "#-disconnected-button"))

    (it "disconnected button - on"
      (reset! ws/open? false)
      (wire-helper/flush)
      (should-select "#-disconnected-button"))

    (it "disconnected button - context menu"
      (reset! ws/open? false)
      (wire-helper/flush)
      (should-not-select "#-disconnected-menu")
      (wire-helper/click! "#-disconnected-button")
      (should-select "#-disconnected-menu"))

    )

  (context "room limit"

    (before
      (wire-helper/flush))

    (it "no message with 0 rooms"
      (should-not-select "#-room-limit"))

    (it "friendly message when under limit"
      (wire-helper/flush)
      (should-select "#-room-limit")
      (should-not-contain "error" (wire-helper/class-name "#-room-limit"))
      (should-contain "You have 4 rooms remaining" (wire-helper/html "#-room-limit")))

    (it "1 room remaining"
      (wire-helper/flush)
      (should-select "#-room-limit")
      (should-not-contain "error" (wire-helper/class-name "#-room-limit"))
      (should-contain "You have 1 room remaining" (wire-helper/html "#-room-limit")))

    (it "error message when limit reached"
      (wire-helper/flush)
      (should-select "#-room-limit")
      (should-contain "error" (wire-helper/class-name "#-room-limit"))
      (should-contain "You have reached your room limit." (wire-helper/html "#-room-limit"))
      (should= true (wire-helper/disabled? "#-create-room-button"))
      )

    )
  )

