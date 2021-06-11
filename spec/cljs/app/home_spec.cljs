(ns app.home-spec
		(:require-macros [speclj.core :refer [describe context it should-not-be-nil should-be-nil should= should-not
																																								should-not= should-have-invoked after before before-all with-stubs with around
																																								stub should-contain should-not-contain should]]
																			[c3kit.wire.spec-helperc :refer [should-select should-not-select]])
		(:require
			[c3kit.wire.spec-helper :as wire-helper]
			[app.home]
			[app.layout :as layout]
			[app.page :as page]
			[speclj.stub :as stub]))

(describe "Home"

		(with-stubs)
		(wire-helper/stub-ajax)
		(wire-helper/with-root-dom)
		(before
				(page/clear!)
				(page/install-page! :home)
				(wire-helper/render [layout/default]))

		(it "create new page button"
				(should-select "#-build-toy-button")
				(wire-helper/click! "#-build-toy-button")
				(should-have-invoked :ajax/post!)
				(should= "/api/toy-builder/request-toy" (wire-helper/last-ajax-post-url))
				)

		)

