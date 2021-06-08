(ns cc-web-app.router-spec
  (:require-macros [speclj.core :refer [describe context it should-not-be-nil should-be-nil should= should-not
                                        should-not= should-have-invoked after before with-stubs with around
                                        stub should-contain should-not-contain should before-all]]
                   [cc-web-app.spec-helperc :refer [it-routes]])
  (:require
   [c3kit.apron.log :as log]
   [cc-web-app.page :as page]
   [cc-web-app.router :as router]
   [secretary.core :as secretary]
   [speclj.core]
   ))

(describe "Router"

  (with-stubs)
  (around [it] (with-redefs [router/load-page! (stub :load-page!)] (it)))
  (before (page/clear!)
    (secretary/reset-routes!)
    (router/app-routes))

  (it-routes "/" :home)
  (it-routes "/pages/test-page" :pages/test-page)
  )