(ns app.toy-builder-spec
		(:require
			[app.toy-builder :as sut]
			[app.dev-toy-builder :as dev]
			[clojure.java.io :as io]
			[speclj.core :refer :all]
			[speclj.stub :as stub]))

(def path (str (.getCanonicalPath (io/file ".")) "/src/clj/app/sandbox"))

(describe "toy builder"
		(with-stubs)

		(it "builds from main with no arg"
				(with-redefs [spit (stub :spit)]
						(dev/-main)
						(let [file (str path "/new_toy.cljs")
												[file-path file-data] (stub/first-invocation-of :spit)]
								(should-have-invoked :spit)
								(should= file file-path)
								(should= (str "(ns app.sandbox.new-toy\r\n"
																			"\t(:require [app.page :as page]))\r\n\r\n"
																			"(defmethod page/render :sandbox/new-toy [_]\r\n"
																			"\t;Write Components Here\r\n"
																			")") file-data))))

		(it "builds a new toy for the sandbox"
				(with-redefs [spit (stub :spit)]
						(sut/build-toy "test-toy")
						(let [file (str path "/test_toy.cljs")
												[file-path file-data] (stub/first-invocation-of :spit)]
								(should-have-invoked :spit)
								(should= file file-path)
								(should= (str "(ns app.sandbox.test-toy\r\n"
																			"\t(:require [app.page :as page]))\r\n\r\n"
																			"(defmethod page/render :sandbox/test-toy [_]\r\n"
																			"\t;Write Components Here\r\n"
																			")") file-data))))

		(it "builds a new toy with spaces"
				(with-redefs [spit (stub :spit)]
						(sut/build-toy "test toy")
						(let [file (str path "/test_toy.cljs")
												[file-path file-data] (stub/first-invocation-of :spit)]
								(should-have-invoked :spit)
								(should= file file-path)
								(should= (str "(ns app.sandbox.test-toy\r\n"
																			"\t(:require [app.page :as page]))\r\n\r\n"
																			"(defmethod page/render :sandbox/test-toy [_]\r\n"
																			"\t;Write Components Here\r\n"
																			")") file-data))))

				(it "a new toy from an ajax request"
						(with-redefs [spit (stub :spit)]
								(let [response (sut/request-toy {:params {:name "ajax-toy"}})
														file     (str path "/ajax_toy.cljs")
														[file-path file-data] (stub/first-invocation-of :spit)]
										(should-have-invoked :spit)
										(should= file file-path)
										(should= (str "(ns app.sandbox.ajax-toy\r\n"
																					"\t(:require [app.page :as page]))\r\n\r\n"
																					"(defmethod page/render :sandbox/ajax-toy [_]\r\n"
																					"\t;Write Components Here\r\n"
																					")") file-data)
										(should= 200 (:status response))
										(should= "ajax-toy has been added to the sandbox!" (-> response :body :flash first :text))
										(should= "app.sandbox.ajax-toy" (-> response :body :payload :new-toy)))))
				)