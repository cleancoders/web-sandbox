(defproject cc-web-app "0.1.0-SNAPSHOT"
  :description "A Clean Coders Web-App Template"
  :url "https://web-app.cleancoders.com"

  :main cc-web-app.main
  :dependencies [[clj-http "3.12.1" :exclusions [commons-codec]]
                 [com.cleancoders.c3kit/wire "1.0.5"]
                 [compojure "1.6.1" :exclusions [ring/ring-core ring/ring-codec]]
                 [hiccup "1.0.5"]
                 [org.clojure/clojure "1.10.3"]
                 ]

  :target-path "tmp/target/%s/"
  :auto-clean false

  :profiles {:dev {:repl-options   {:init-ns cc-web-app.repl
                                    :timeout 120000}
                   :resource-paths ["resources" "dev"]
                   :dependencies   [[com.cleancoders.c3kit/scaffold "1.0.1"]
                                    [clj-commons/secretary "1.2.4"]
                                    [speclj "3.3.2"]
                                    [venantius/accountant "0.2.5"]]}}

  :plugins [[speclj "3.3.2"]
            [lein-auto "0.1.3"]
            [lein-pdo "0.1.1"]]

  :source-paths ["src/clj" "src/cljc" "src/cljs"]
  :test-paths ["spec/clj" "spec/cljc" "spec/cljs"]
  :resource-paths ["resources"]

  :clean-targets ^{:protect false} [:target-path "resources/public/cljs"]

  :aliases {
            "cljs"    ["run" "-m" "c3kit.scaffold.cljs"]
            "css"     ["run" "-m" "c3kit.scaffold.css"]
            "prep"    ["do" "clean," "css" "once," "cljs" "once"]
            }
  )