(defproject money "0.1.0-SNAPSHOT"
  :description "FIXME: write this!"
  :url "http://example.com/FIXME"
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [org.clojure/clojurescript "0.0-2030"]
                 [ring "1.2.1"]
                 [xnlogic/clobber "0.1.0-SNAPSHOT"]]
  :plugins [[lein-cljsbuild "1.0.0-alpha2"]
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
                         :source-map "main.js.map"}
              :jar true}]}
  :main money.server
  :ring {:handler money.server/app})

