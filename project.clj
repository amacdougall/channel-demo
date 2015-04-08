(defproject channel-demo "0.1.0-SNAPSHOT"
  :description "Skeletal app to demonstrate ClojureScript channels."

  :dependencies [[org.clojure/clojure "1.7.0-alpha5"]
                 [org.clojure/clojurescript "0.0-3058" :scope "provided"]
                 [figwheel "0.2.5"]
                 [org.omcljs/om "0.8.8"]
                 [sablono "0.3.4"]
                 [org.clojure/core.async "0.1.346.0-17112a-alpha"]]

  :plugins [[lein-cljsbuild "1.0.4"]
            [lein-figwheel "0.2.5"]]

  :source-paths ["src"]

  :clean-targets ^{:protect false} ["resources/public/js/compiled"]
  
  :cljsbuild {
    :builds [{:id "dev"
              :source-paths ["src" "dev_src"]
              :compiler {:output-to "resources/public/js/compiled/channel_demo.js"
                         :output-dir "resources/public/js/compiled/out"
                         :optimizations :none
                         :main channel-demo.dev
                         :asset-path "js/compiled/out"
                         :source-map true
                         :source-map-timestamp true
                         :cache-analysis true }}
             {:id "min"
              :source-paths ["src"]
              :compiler {:output-to "resources/public/js/compiled/channel_demo.js"
                         :main channel-demo.core                         
                         :optimizations :advanced
                         :pretty-print false}}]}

  :figwheel {:http-server-root "public"
             :server-port 3449
             :css-dirs ["resources/public/css"]})
