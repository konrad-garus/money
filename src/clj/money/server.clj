(ns money.server
  (:require [ring.adapter.jetty :as jetty]
            [ring.middleware.resource :as resources]
            [ring.middleware.content-type]
            [ring.util.response :as response])
  (:gen-class))

(defn handler [request]
  (->
    (response/resource-response "/public/app.html")
    (response/content-type "text/html")))

(def app 
  (-> handler
    (resources/wrap-resource "public")
    (ring.middleware.content-type/wrap-content-type)))

(defn -main [& args]
  (jetty/run-jetty app {:port 3000}))

