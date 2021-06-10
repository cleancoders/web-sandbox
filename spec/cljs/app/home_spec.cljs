(ns app.home-spec
		(:require-macros [speclj.core :refer [describe context it should-not-be-nil should-be-nil should= should-not
																																								should-not= should-have-invoked after before before-all with-stubs with around
																																								stub should-contain should-not-contain should]]
																			[c3kit.wire.spec-helperc :refer [should-select should-not-select]])
		(:require
			[c3kit.wire.spec-helper :as wire-helper]
			[c3kit.wire.websocket :as ws]
			[app.home :as sut]
			[app.layout :as layout]
			[app.page :as page]
			[app.spec-helper :as helper]))

(describe "Home"

		(with-stubs)
		(wire-helper/stub-ajax)
		(wire-helper/stub-ws)
		(wire-helper/with-root-dom)
		(before
				(page/clear!)
				(page/install-page! :home)
				(wire-helper/render [layout/default]))

		(it "create new page button"
				(should-select "#-create-page-button")
				(wire-helper/click! "#-create-page-button")
				(should-have-invoked :ws/call!)
				(should= :page/create (wire-helper/last-ws-call-id)))

		)

