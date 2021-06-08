(ns cc-web-app.spec-helperc
  #?(:cljs (:require-macros [speclj.core :refer [-fail -to-s around]]))
  (:require
   #?(:clj  [speclj.core :refer :all]
      :cljs [speclj.core])
   ))


#?(:clj (defmacro it-routes
          "Tests a client side route"
          [path page-key & body]
          `(it ~path
             (with-redefs [cc-web-app.router/load-page! (stub :load-page!)]
               (cc-web-app.router/dispatch! ~path)
               (should-have-invoked :load-page! {:with [~page-key]})
               ~@body))))
