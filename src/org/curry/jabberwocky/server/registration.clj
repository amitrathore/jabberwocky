(ns org.curry.jabberwocky.server.registration
  (:use org.curry.jabberwocky.connection)
  (:import [org.jivesoftware.smack AccountManager XMPPException]))

(defn register-new-user [username password connection]
  (try
    (doto (AccountManager. connection)
      (.createAccount username password))
    true
    (catch XMPPException e
      false)))
