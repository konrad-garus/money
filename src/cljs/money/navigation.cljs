(ns money.navigation
  (:require [goog.events :as events]
            [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true])
  (:import goog.History
           goog.History.EventType))

(def history (History.))

(def navigation-state (atom []))

(defn configure-navigation! [routes]
  (reset! navigation-state routes))

(defn refresh-navigation []
  (let [token (.getToken history)
        set-active (fn [nav]
                     (assoc nav :active (= (:path nav) token)))]
    (swap! navigation-state #(map set-active %))))

(defn on-navigate [event]
  (refresh-navigation)
  (secretary/dispatch! (.-token event)))

(doto history
  (goog.events/listen EventType/NAVIGATE on-navigate)
  (.setEnabled true))

(defn navigation-item-view [{:keys [active path name]} owner]
  (reify
    om/IRender
    (render [this]
            (dom/li #js {:className (if active "active" "")}
                    (dom/a #js {:href (str "#" path)} name)))))

(defn navigation-view [app owner]
  (reify
    om/IRender
    (render [this]
            (apply dom/ul #js {:className "nav nav-tabs"}
                   (om/build-all navigation-item-view app)))))

(om/root navigation-view navigation-state
         {:target (. js/document (getElementById "navigation"))})
