(ns app.session-spec
		(:require
			[c3kit.apron.time :as time :refer [days ago]]
			[c3kit.wire.spec-helperc :as wire-helperc]
			[app.session :as sut]
			[ring.middleware.session.store :as store]
			[speclj.core :refer :all]
			))

(def store :undefined)
(def now :undefined)
(def user :undefined)

(describe "Session"

		(with-stubs)
		(with now (time/now))
		(wire-helperc/stub-now @now)

		(it "update session"
				(let [session (sut/save-session nil {:foo "bar"})
										hash    (sut/session-key session)
										updated (sut/update-session hash #(assoc % :fizz "bang"))]
						(should= "bang" (-> updated :data read-string :fizz))
						(should= "bang" (:fizz (sut/load-session hash)))))

		(context "db store"

				(with store (sut/db-session-store))

				(it "creating new session"
						(let [key     (store/write-session @store nil {:foo "bar"})
												id      (sut/hash->id key)
												session (get-in @sut/sessions [id])]
								(should-not= nil session)
								(should= id (:id session))
								(should= @now (:updated-at session))
								(should= (pr-str {:foo "bar"}) (:data session))))

				(it "updating a session"
						(let [key     (store/write-session @store nil {:foo "bar"})
												id      (sut/hash->id key)
												session (get-in @sut/sessions [id])
												_       (swap! sut/sessions assoc-in [id :updated-at] (-> 1 days ago))
												key2    (store/write-session @store key {:foo "bang"})
												session (sut/reload session)]
								(should= key key2)
								(should= id (:id session))
								(should= @now (:updated-at session))
								(should= (pr-str {:foo "bang"}) (:data session))))

				(it "reading a session"
						(let [key  (store/write-session @store nil {:foo "bar"})
												data (store/read-session @store key)]
								(should= "bar" (:foo data))))

				(it "reading session with bad key"
						(should= nil (store/read-session @store "blah")))

				(it "reading session with nil key"
						(should= nil (store/read-session @store nil)))

				(it "deleting session"
						(let [key  (store/write-session @store nil {:foo "bar"})
												key2 (store/delete-session @store key)]
								(should= nil key2)
								(should= nil (sut/load-session key))))

				)
		;
		;(it "prunes old sessions"
		;		(let [old        (db/tx :kind :session :updated-at (-> 6 days ago))
		;								not-so-old (db/tx :kind :session :updated-at (-> 4 days ago))
		;								pretty-new (db/tx :kind :session :updated-at (-> 1 days ago))]
		;				(sut/prune-sessions! nil nil)
		;				(let [sessions (db/find-all :session :updated-at)]
		;						(should= 2 (count sessions))
		;						(should= nil (db/entity (:id old))))))
		)

