(ns app.config
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
				{:host      "http://localhost:8082"
					:log-level :trace}))

(def staging
		(merge base
				{:host             "https://web-app-staging.cleancoders.com"
					:log-level        :trace}))

(def production
		(merge base
				{:host             "https://web-app.cleancoders.com"
					:analytics-code   "console.log('Replace me with Real Google Analytics Code.');"}))


(def environment (app/find-env "cc.env" "CC_ENV"))
(def development? (= "development" environment))
(def production? (= "production" environment))

(def env
		(case environment
				"staging" staging
				"production" production
				development))

(def host (:host env))

(defn link [& parts] (apply str host parts))