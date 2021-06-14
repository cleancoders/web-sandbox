(ns app.home-spec
		(:require-macros [speclj.core :refer [describe context it should-not-be-nil should-be-nil should= should-not
																																								should-not= should-have-invoked after before before-all with-stubs with around
																																								stub should-contain should-not-contain should]]
																			[c3kit.wire.spec-helperc :refer [should-select should-not-select]])
		(:require
			[c3kit.wire.spec-helper :as wire-helper]
			[app.home :as sut]
			[app.layout :as layout]
			[app.page :as page]
			[speclj.stub :as stub]
			[app.modal :as modal]))

(describe "Home"

		(with-stubs)
		(wire-helper/stub-ajax)
		(wire-helper/with-root-dom)
		(before
				(page/clear!)
				(page/install-page! :home)
				(wire-helper/render [layout/default]))

		(it "post new story"
				(should-select "#-new-toy-title")
				(should-select "#-build-toy-button")
				(wire-helper/click! "#-build-toy-button")
				;(should= :home/build-toy (modal/subject))
				(should-have-invoked :ajax/post!)
				(should= "/api/toy-builder/request-toy" (wire-helper/last-ajax-post-url)))

		(it "request-new-story"
				(reset! sut/name "test-toy")
				(with-redefs [sut/request-new-toy (stub :request-toy)]
						(should-select "#-new-toy-title")
						(should-select "#-build-toy-button")
						(wire-helper/click! "#-build-toy-button")
						(should-have-invoked :request-toy {:with ["test-toy"]})))

		(it "request-new-story with no name"
				(with-redefs [sut/request-new-toy (stub :request-toy)]
						(should-select "#-new-toy-title")
						(should-select "#-build-toy-button")
						(wire-helper/click! "#-build-toy-button")
						(should-have-invoked :request-toy {:with ["your-new-sandbox-toy"]})))


		;(context "new sandbox toy modal"
		;
		;		(before (modal/install! :home/build-toy :name "New Toy")
		;				(wire-helper/flush))
		;
		;		(it "populated"
		;				(should-select "#-new-toy-modal")
		;				(should-select "#-new-toy-title")
		;(should= (:title @flop) (wire-helper/value "#-story-title textarea"))
		;(should-contain "selected" (wire-helper/class-name "#-include-button"))
		;(should-contain "unselected" (wire-helper/class-name "#-exclude-button"))
		;)

		;(it "submit"
		;		(wire-helper/change! "#-story-title textarea" "New Toy")
		;		(wire-helper/click! "#-save-story-button")
		;		(should-have-invoked :ajax/post!)
		;		)
		;)

		)