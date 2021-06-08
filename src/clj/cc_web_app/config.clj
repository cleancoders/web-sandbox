(ns cc-web-app.config
		(:require
			[c3kit.apron.app :as app]
			))

(def ^:private base
		{
			:analytics-code   "console.log('google analytics would have loaded for this page');"
			:host             :define-me                             ;"http://localhost:8080"
			:log-level        :info
			})

(def development
		(merge base
				{:host      "http://localhost:8081"
					:log-level :trace}))

(def environment (app/find-env "cc.env" "CC_ENV"))
(def development? (= "development" environment))
(def production? (= "production" environment))

(def env
		(case environment
				development))

(def host (:host env))

(defn link [& parts] (apply str host parts))