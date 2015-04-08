(ns channel-demo.data
  (:require [om.core :as om :include-macros true]
            [cljs.core.async :refer [chan timeout <! >! put! close!]])
  (:require-macros [cljs.core.async.macros :refer [go go-loop]]))

(defonce app-state (atom {:text "Hello world!"}))
