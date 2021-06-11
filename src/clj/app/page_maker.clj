(ns app.page-maker
		(:require [c3kit.apron.log :as log]
												[clojure.java.io :as io]
												[clojure.string :as string]))

(defn ws-create [& args]
		(log/info "ws-create: creating new page")
		(let [name (if args (first args) "new-page")
								path (str (.getCanonicalPath (io/file ".")) "/src/cljs/app/pages/")
								file-path (str path (string/replace name #"-" "_") ".cljs")]
		(with-open [page (io/writer file-path)]
				(.write page (str "(ns app.pages." name "\r\n"))
				(.write page (str "\t(:require [app.page :as page]))\r\n\r\n"))
				(.write page (str "(defmethod page/render :page/" name " [_]\r\n"))
				(.write page (str "\t;Write Components Here\r\n"))
				(.write page ")")))
		)
