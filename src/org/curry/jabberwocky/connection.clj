(ns org.curry.jabberwocky.connection
  (:import [org.jivesoftware.smack XMPPConnection ConnectionConfiguration]))

(def CONN-DEFAULTS {:host "localhost" :port 5222 :sasl false :compression true})

(defn- new-configuration [{:keys [host port sasl compression]}]
  (doto (ConnectionConfiguration. host port)
    (.setSASLAuthenticationEnabled sasl)
    (.setCompressionEnabled compression)))

(defn new-connection [params-overides]
  (doto (->> params-overides
             (merge CONN-DEFAULTS)             
             (new-configuration)
             (XMPPConnection.))
    (.connect)))

(defn login [username password resource connection]
  (.login connection username password resource))

(defn disconnect [connection]
  (.disconnect connection))