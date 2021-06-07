(ns routes-spec
  (:require
   [c3kit.apron.log :as log]
   [c3kit.apron.utilc :as utilc]
   [c3kit.wire.flashc :as flashc]
   [c3kit.wire.spec-helper :as wire-helper]
   [c3kit.wire.websocket :as ws]
   [cc-web-app.routes :as routes]
   [cc-web-app.spec-helper]
   [speclj.core :refer :all]
   [speclj.stub :as stub]
   ))

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
  (test-route "/pages/test-page" :get cc-web-app.layouts/web-rich-client)

  ;; websocket handlers
  ;(test-webs :admin/access poker.admin/ws-access-admin)

  ;(it "not-found global - nil - handled by http"
  ;  (let [response (routes/handler {:uri "/blah" :request-method :get})]
  ;    (should= nil response)))
  ;
  ;(it "not-found for api-calls"
  ;  (let [response (routes/handler {:uri "/api/v1/blah" :request-method :get})
  ;        body (utilc/<-transit (:body response))]
  ;    (should= :fail (:status body))
  ;    (should= "API not found: /api/v1/blah" (-> body :flash first flashc/text))))
  )
