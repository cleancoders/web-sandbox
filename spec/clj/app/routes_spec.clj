(ns app.routes-spec
  (:require
   [c3kit.apron.log :as log]
   [c3kit.apron.utilc :as utilc]
   [c3kit.wire.flashc :as flashc]
   [c3kit.wire.spec-helper :as wire-helper]
   [c3kit.wire.websocket :as ws]
   [app.routes :as routes]
   [app.spec-helper]
   [app.dev-toy-builder]
   [speclj.core :refer :all]
   [speclj.stub :as stub]
   [app.layouts :as layouts]
   [clojure.java.io :as io]))

(def args (atom :none))

;; MDM - Macros are used here to preserve line number when specs fail

(defmacro check-route [path method handler]
  `(let [stub-key# ~(keyword handler)]
     (require '~(symbol (namespace handler)))
     (with-redefs [~handler (stub stub-key#)]
       (routes/handler {:uri ~path :request-method ~method})
       (should-have-invoked stub-key#)
       (reset! args (stub/first-invocation-of stub-key#)))))

(defmacro test-route [path method handler & body]
  `(it ~path
     (check-route ~path ~method ~handler)
     ~@body))

(defmacro test-redirect [path method dest]
  `(it (str ~path " -> " ~dest)
     (let [response# (routes/handler {:uri ~path :request-method ~method})]
       (wire-helper/should-redirect-to response# ~dest))))

(defmacro test-webs [id sym]
  `(it (str "remote " ~id " -> " '~sym)
     (let [action# (ws/resolve-handler ~id)]
       (should-not= nil action#)
       (should= '~sym (.toSymbol action#)))))

(describe "Routes"

  (with-stubs)
  (before (reset! args :none))
  (around [it] (with-redefs [c3kit.wire.api/version (constantly "fake-api-version")] (it)))

  ; Please keep these specs sorted alphabetically

  ;; web routes
  (test-route "/" :get app.layouts/web-rich-client)
  (test-route "/sandbox/example-toy" :get app.layouts/web-rich-client)

  ;; ajax routes
  ;(test-route "/api/user/csrf-token" :get app.user-handlers/ajax-csrf-token)
  (test-route "/api/toy-builder/request-toy" :post app.toy-builder/request-toy)

  ;; dev routes
  (test-route "/sandbox/example-toy" :get app.layouts/web-rich-client)
  (test-route "/sandbox/blah" :get app.layouts/web-rich-client)


  (it "not-found global - nil - handled by http"
    (let [response (routes/handler {:uri "/blah" :request-method :get})]
      (should= nil response)))

  )
