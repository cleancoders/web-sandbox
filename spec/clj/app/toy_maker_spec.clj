(ns app.toy-maker-spec
		(:require
			[app.toy-builder :as sut]
			[clojure.java.io :as io]
			[speclj.core :refer :all]
			[clojure.string :as string]))

(def path (str (.getCanonicalPath (io/file ".")) "/src/clj/app/sandbox"))


(describe "page creator"

		(it "calls main with no arg"
				(sut/-main)
				(should-contain "new_toy.clj" (map str (.list (io/file path))))
				(let [file (str path "/new_toy.clj")]
						(should= (str "(ns app.sandbox.new-toy\r\n"
																	"\t(:require [app.page :as page]))\r\n\r\n"
																	"(defmethod page/render :sandbox/new-toy [_]\r\n"
																	"\t;Write Components Here\r\n"
																	")") (slurp file))
						(io/delete-file file)))

		(it "builds a new toy for the sandbox"
				(sut/build-toy "test-toy")
				(should-contain "test_toy.clj" (map str (.list (io/file path))))
				(let [file (str path "/test_toy.clj")]
						(should= (str "(ns app.sandbox.test-toy\r\n"
																	"\t(:require [app.page :as page]))\r\n\r\n"
																	"(defmethod page/render :sandbox/test-toy [_]\r\n"
																	"\t;Write Components Here\r\n"
																	")") (slurp file))
						(io/delete-file file)))
		)