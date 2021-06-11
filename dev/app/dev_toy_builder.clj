(ns app.dev-toy-builder
		(:require [c3kit.apron.log :as log]
												[app.toy-builder :as builder]
												))

(defn -main [& args]
		(log/info "creating a new toy for your sandbox")
		(if-let [name (first args)]
				(builder/build-toy name)
				(builder/build-toy "new-toy")))
