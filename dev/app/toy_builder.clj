(ns app.toy-builder
		(:require [c3kit.apron.log :as log]
												[clojure.java.io :as io]
												[clojure.string :as str]))

(defn build-toy [name]
		(let [path (str (.getCanonicalPath (io/file ".")) "/src/clj/app/sandbox/")
								file-path (str path (str/replace name #"-" "_") ".clj")
								data (str "(ns app.sandbox." name "\r\n"
															"\t(:require [app.page :as page]))\r\n\r\n"
															"(defmethod page/render :sandbox/" name " [_]\r\n"
															"\t;Write Components Here\r\n"
															")")]
				(spit file-path data)))

(defn -main [& args]
		(log/info "creating a new toy for your sandbox")
		(if-let [name (first args)]
				(build-toy name)
				(build-toy "new-toy")))
