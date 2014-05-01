(ns money.main
  (:require [secretary.core :as secretary :include-macros true :refer [defroute]]
            [goog.events :as events]
            [enfocus.core :as ef]
            [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true])
  (:require-macros [enfocus.macros :as em])
  (:import goog.History
           goog.History.EventType))

;; (em/deftemplate view-add "templates/add.html" [])

;; (em/deftemplate view-browse "templates/browse.html" [category]
;;   ["h1"] (ef/content category))

;; (defroute "/" []
;;   (.setToken (History.) "/add"))

;; (defroute "/add" []
;;   (js/console.log "Addin")
;;     (ef/at
;;       ["#view"] (ef/content (view-add))))

;; (defroute "/browse/:category" [category]
;;     (ef/at
;;       ["#view"] (ef/content (view-browse category))))

;; (doto (History.)
;;   (goog.events/listen EventType/NAVIGATE #(em/wait-for-load (secretary/dispatch! (.-token %))))
;;   (.setEnabled true))

(enable-console-print!)

(def history (History.))

(defn navigation-item-view [{:keys [active path name]} owner]
  (reify
    om/IRender
    (render [this]
            (dom/li #js {:className (if active "active" "")}
                    (dom/a #js {:href path} name)))))

(defn navigation-view [app owner]
  (reify
    om/IRender
    (render [this]
            (apply dom/ul #js {:className "nav nav-tabs"}
                   (om/build-all navigation-item-view app)))))

(def navigation-state
  (atom
   [{:name "Dodawanie" :path "#/add"}
    {:name "Przegląd" :path "#/browse"}]))

(defn refresh-navigation []
  (let [token (.getToken history)
        browser-path (str "#" token)
        set-active (fn [nav]
                     (assoc nav :active (= (:path nav) browser-path)))]
    (swap! navigation-state #(map set-active %))))

(om/root navigation-view navigation-state
         {:target (. js/document (getElementById "navigation"))})

(defroute "/add" []
  (js/console.log "Adding"))

(defroute "/browse" []
  (js/console.log "Browsing"))

(defn on-navigate [event]
  (refresh-navigation)
  (secretary/dispatch! (.-token event)))

(doto history
  (goog.events/listen EventType/NAVIGATE on-navigate)
  (.setEnabled true))
