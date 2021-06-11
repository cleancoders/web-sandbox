(ns app.router-spec
  (:require-macros [speclj.core :refer [describe context it should-not-be-nil should-be-nil should= should-not
                                        should-not= should-have-invoked after before with-stubs with around
                                        stub should-contain should-not-contain should before-all]])
  (:require
   [c3kit.apron.log :as log]
   [app.page :as page]
   [app.router :as router]
   [secretary.core :as secretary]
   [speclj.core]
   ))

(defmacro it-routes
  "Tests a client side route"
  [path page-key & body]
  `(it ~path
     (with-redefs [app.router/load-page! (stub :load-page!)]
       (app.router/dispatch! ~path)
       (should-have-invoked :load-page! {:with [~page-key]})
       ~@body)))

(describe "Router"

  (with-stubs)
  (around [it] (with-redefs [router/load-page! (stub :load-page!)] (it)))
  (before (page/clear!)
    (secretary/reset-routes!)
    (router/app-routes))

  (it-routes "/" :home)
  (it-routes "/sandbox/example-page" :sandbox/example-page)

  )