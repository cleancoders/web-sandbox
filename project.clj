(defproject app "0.1.0-SNAPSHOT"
		:description "A Clean Coders Web-App Template"
		:url "https://web-app.cleancoders.com"

		:dependencies [[clj-http "3.12.1" :exclusions [commons-codec]]
																	[com.cleancoders.c3kit/wire "1.0.5"]
																	;[com.google.api-client/google-api-client "1.30.4" :exclusions [com.google.guava/guava]]
																	[compojure "1.6.1" :exclusions [ring/ring-core ring/ring-codec]]
																	[hiccup "1.0.5"]
																	[org.clojure/clojure "1.10.3"]
																	[ring/ring-anti-forgery "1.3.0" :exclusions [commons-codec]]
																	]

		:global-vars {*warn-on-reflection* false}
		:target-path "tmp/target/%s/"
		:auto-clean false

		:profiles {;:server {:jvm-opts ["-Xmx1g" "-server"]}
             :dev {:repl-options   {:init-ns app.repl
                                    :timeout 120000}
                   :resource-paths ["resources" "dev"]
                   :dependencies   [[clj-commons/secretary "1.2.4"]
                                    [com.cleancoders.c3kit/bucket "1.0.2"]
                                    [com.cleancoders.c3kit/scaffold "1.0.1"]
                                    [com.google.guava/guava "30.1-jre"] ; need to override 18.0 version required by datomic
                                    [speclj "3.3.2"]
                                    [venantius/accountant "0.2.5"]]}}

		:plugins [[speclj "3.3.2"]
												[lein-auto "0.1.3"]
												[lein-pdo "0.1.1"]]

		:source-paths ["src/clj" "src/cljc" "src/cljs"]
		:test-paths ["spec/clj" "spec/cljc" "spec/cljs"]
		:resource-paths ["resources"]

		:clean-targets ^{:protect false} [:target-path "resources/public/cljs"]

		:main app.main

		:aliases {
												"cljs" ["run" "-m" "c3kit.scaffold.cljs"]
												"css"  ["run" "-m" "c3kit.scaffold.css"]
												"prep" ["do" "clean," "css" "once," "cljs" "once"]
												"dev"  ["run" "-m" "app.dev"]
												"toy"  ["run" "-m" "app.dev-toy-builder"]
												}
		)