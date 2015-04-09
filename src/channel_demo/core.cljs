(ns channel-demo.core
  (:require [channel-demo.data :as data]
            [channel-demo.views :as views]
            [om.core :as om :include-macros true]))

(enable-console-print!)

(om/root views/main data/app-state
  {:target (. js/document (getElementById "app"))})
