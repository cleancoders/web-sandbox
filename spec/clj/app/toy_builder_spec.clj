(ns app.toy-builder-spec
		(:require
			[app.toy-builder :as sut]
			[app.dev-toy-builder :as dev]
			[clojure.java.io :as io]
			[speclj.core :refer :all]))

(def path (str (.getCanonicalPath (io/file ".")) "/dev/app/sandbox"))


(describe "toy builder"

		(it "builds from main with no arg"
				(dev/-main)
				(should-contain "new_toy.cljs" (map str (.list (io/file path))))
				(let [file (str path "/new_toy.cljs")]
						(should= (str "(ns app.sandbox.new-toy\r\n"
																	"\t(:require [app.page :as page]))\r\n\r\n"
																	"(defmethod page/render :sandbox/new-toy [_]\r\n"
																	"\t;Write Components Here\r\n"
																	")") (slurp file))
						(io/delete-file file)))

		(it "builds a new toy for the sandbox"
				(sut/build-toy "test-toy")
				(should-contain "test_toy.cljs" (map str (.list (io/file path))))
				(let [file (str path "/test_toy.cljs")]
						(should= (str "(ns app.sandbox.test-toy\r\n"
																	"\t(:require [app.page :as page]))\r\n\r\n"
																	"(defmethod page/render :sandbox/test-toy [_]\r\n"
																	"\t;Write Components Here\r\n"
																	")") (slurp file))
						(io/delete-file file)))

		(it "builds a new toy with spaces"
				(sut/build-toy "test toy")
				(should-contain "test_toy.cljs" (map str (.list (io/file path))))
				(let [file (str path "/test_toy.cljs")]
						(should= (str "(ns app.sandbox.test-toy\r\n"
																	"\t(:require [app.page :as page]))\r\n\r\n"
																	"(defmethod page/render :sandbox/test-toy [_]\r\n"
																	"\t;Write Components Here\r\n"
																	")") (slurp file))
						(io/delete-file file)))

		(it "a new toy from an ajax request"
				(let [response (sut/request-toy {:params {:name "ajax-toy"}})]
				(should-contain "ajax_toy.cljs" (map str (.list (io/file path))))
				(let [file (str path "/ajax_toy.cljs")]
						(should= (str "(ns app.sandbox.ajax-toy\r\n"
																	"\t(:require [app.page :as page]))\r\n\r\n"
																	"(defmethod page/render :sandbox/ajax-toy [_]\r\n"
																	"\t;Write Components Here\r\n"
																	")") (slurp file))
						(should= 200 (:status response))
						(should= "ajax-toy has been added to the sandbox!" (-> response :body :flash first :text))
						(should= "app.sandbox.ajax-toy" (-> response :body :payload :new-toy))
						(io/delete-file file))))


		)