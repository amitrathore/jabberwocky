(ns org.curry.jabberwocky.client.chat
  (:import [org.jivesoftware.smack MessageListener]))

(def CHATS (atom {}))

(defn register-chat [jid connection message-listener]
  (let [c (.createChat (.getChatManager connection) jid message-listener)]
    (swap! CHATS assoc jid c)
    c))

(defn chat-for [jid message-listener connection]
  (or (@CHATS jid)
      (register-chat jid connection message-listener)))

(defn message-listener-from [function]
  (proxy [MessageListener] []
    (processMessage [chat message]
      (function chat message))))

(def do-nothing-listener (message-listener-from (fn [c m])))

(defn send-message
  ([jid message connection]
     (send-message jid message connection do-nothing-listener))
  ([jid message connection listener-fn]
     (let [chat (chat-for jid (message-listener-from listener-fn) connection)]
       (.sendMessage chat message))))

