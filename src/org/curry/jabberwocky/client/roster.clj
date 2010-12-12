(ns org.curry.jabberwocky.client.roster
  (:import [org.jivesoftware.smack Roster]))

(defn roster-entries [connection]
  (let [roster (.getRoster connection)]
    (seq (.getEntries roster))))

