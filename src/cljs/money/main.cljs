(ns money.main
  (:require-macros [clang.angular :refer [def.controller def.config defn.scope def.filter fnj]])
  (:require [clang.core]
            [money.add])
  (:use [clang.util :only [? module]]))

(def m (module "money" ["clang", "ngRoute", "money.add"]))

(def.config m [$routeProvider]
  (doto $routeProvider
    (.when "/add" (js-obj 
                    "templateUrl" "templates/add.html"
                    "controller" "add-controller"))
    (.otherwise (js-obj "redirectTo" "/add"))
    ))


