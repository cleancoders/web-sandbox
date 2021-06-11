(ns app.toy-builder
		(:require [c3kit.apron.log :as log]
												[clojure.java.io :as io]
												[clojure.string :as str]
												[c3kit.wire.ajax :as ajax]))

(defn build-toy [name]
		(let [path (str (.getCanonicalPath (io/file ".")) "/src/clj/app/sandbox/")
								file-path (str path (str/replace name #"-" "_") ".cljs")
								data (str "(ns app.sandbox." name "\r\n"
															"\t(:require [app.page :as page]))\r\n\r\n"
															"(defmethod page/render :sandbox/" name " [_]\r\n"
															"\t;Write Components Here\r\n"
															")")]
				(spit file-path data)))

(defn request-toy [request]
		(log/info "&&&&&&&&&&&&&&&&&&&&NAME: " request)
		(let [name (-> request :params :name)]
			(build-toy name)
		(ajax/ok nil (str name " has been added to the sandbox"))))

