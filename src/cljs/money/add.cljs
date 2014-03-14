(ns money.add
  (:require-macros [clang.angular :refer [def.controller def.config defn.scope def.filter fnj]])
  (:use [clang.util :only [? module]]))

(def m (module "money.add" ["clang"]))

;(def.controller m TodoCtrl [$scope]
;  (assoc! $scope :todos [{:text "learn angular" :done "yes"}
;                         {:text "learn cljs" :done "yes"}
;                         {:text "build an app" :done "no"}])
;  (defn.scope remaining []
;    (->>
;      (:todos $scope)
;      (map :done)
;      (remove #{"yes"})
;      count)))

(def.controller m add-controller [$scope]
  ;(.log js/console "Bababa")
  nil)

