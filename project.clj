(defproject money "0.1.0-SNAPSHOT"
  :description "FIXME: write this!"
  :url "http://example.com/FIXME"
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [org.clojure/clojurescript "0.0-2173"]
                 [ring "1.2.1"]
                 [xnlogic/clobber "0.1.0-SNAPSHOT"]
                 [secretary "1.0.2"]
                 [enfocus "2.0.2"]
                 [org.clojure/core.async "0.1.267.0-0d7780-alpha"]
                 [om "0.5.0"]]
  :plugins [[lein-cljsbuild "1.0.2"]
            [lein-ring "0.8.8"]]
  :hooks [leiningen.cljsbuild]
  :source-paths ["src/clj"]
  :cljsbuild {
    :builds [{
              :id "main"
              :source-paths ["src/cljs"]
              :compiler {;:output-to "resources/public/js/cljs.js"
                         :optimizations :simple
                         :pretty-print true
                         :output-dir "out"
                         :output-to "main.js"
                         :source-map "main.js.map"
;;                          :source-map true
                         }
              :jar true}]}



;;   :cljsbuild {
;;     :builds [{:id "om-tut"
;;               :source-paths ["src"]
;;               :compiler {
;;                 :output-to "om_tut.js"
;;                 :output-dir "out"
;;                 :optimizations :none
;;                 :source-map true}}]}


  :main money.server
  :ring {:handler money.server/app})

