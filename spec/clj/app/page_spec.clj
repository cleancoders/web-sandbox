(ns app.page-spec
		(:require
			[app.page-maker :as sut]
			[clojure.java.io :as io]
			[speclj.core :refer :all]
			[clojure.string :as string]))

(def path (str (.getCanonicalPath (io/file ".")) "/src/cljs/app/pages"))


(describe "page creator"

		(context "creates a new page"
				(it "with no name"
				(sut/ws-create)
						(should-contain "new_page.cljs" (map str (.list (io/file path))))
				(let [file (str path "/new_page.cljs")]
						(should= (str "(ns app.pages.new-page\r\n"
						"\t(:require [app.page :as page]))\r\n\r\n"
						"(defmethod page/render :page/new-page [_]\r\n"
						"\t;Write Components Here\r\n"
						")") (slurp file))
						(io/delete-file file)))

				(it "with name"
						(sut/ws-create "test-page")
								(should-contain "test_page.cljs" (map str (.list (io/file path))))
						(let [file (str path "/test_page.cljs")]
								(should= (str "(ns app.pages.test-page\r\n"
																			"\t(:require [app.page :as page]))\r\n\r\n"
																			"(defmethod page/render :page/test-page [_]\r\n"
																			"\t;Write Components Here\r\n"
																			")") (slurp file))
								(io/delete-file file))))
		)