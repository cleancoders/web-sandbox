(ns app.toy-builder
		(:require [c3kit.wire.ajax :as ajax]
												[clojure.java.io :as io]
												[clojure.string :as str]))

(def path (str (.getCanonicalPath (io/file ".")) "/src/clj/app/sandbox/"))

(defn build-toy [name]
		(let [name (str/replace name #" " "-")
								file-path (str path (str/replace name #"-" "_") ".cljs")
								data (str "(ns app.sandbox." name "\r\n"
															"\t(:require [app.page :as page]))\r\n\r\n"
															"(defmethod page/render :sandbox/" name " [_]\r\n"
															"\t;Write Components Here\r\n"
															")")]
				(spit file-path data)
				))

(defn request-toy [request]
		(let [name    (-> request :params :name)]
				(build-toy name)
				(ajax/ok {:new-toy (str "app.sandbox." name)} (str name " has been added to the sandbox!"))))

