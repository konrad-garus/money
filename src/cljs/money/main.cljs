(ns money.main
  (:require [secretary.core :as secretary :include-macros true :refer [defroute]]
            [goog.events :as events])
  (:import goog.History
           goog.history.EventType))

(defroute "/" []
  (.setToken (History.) "/add"))

(defroute "/add" []
  (js/console.log "In ADD page")
  )

(doto (History.)
  (goog.events/listen EventType/NAVIGATE #(secretary/dispatch! (.-token %)))
  (.setEnabled true))
