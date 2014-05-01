(ns money.main
  (:require [secretary.core :as secretary :include-macros true :refer [defroute]]
            [goog.events :as events]
            [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]
            [money.navigation :refer [configure-navigation!]])
  (:require-macros [enfocus.macros :as em])
  (:import goog.History
           goog.History.EventType))

(enable-console-print!)

(defroute "/add" []
  (js/console.log "Adding"))

(defroute "/browse" []
  (js/console.log "Browsing"))

(configure-navigation!
 [{:name "Dodawanie" :path "/add"}
  {:name "Przegląd" :path "/browse"}])
