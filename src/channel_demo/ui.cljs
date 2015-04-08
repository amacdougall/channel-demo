(ns channel-demo.input
  (:require [cljs.core.async :refer [>! <! chan put!] :as async]
            [goog.events :as events])
  (:require-macros [cljs.core.async.macros :refer [go go-loop]])
  (:import [goog.events EventType]))

;; Given an input channel, returns a channel which transforms each value using
;; the supplied function.
(defn- map-channel [f in]
  (let [out (chan)]
    (go (loop []
          (>! out (f (<! in)))
          (recur)))
    out))

;; Given a DOM node, returns a channel of click events emitted by that node.
(defn- raw-clicks [node]
  (let [out (chan)
        handle-click (fn [e] (put! out e))]
    (events/listen node goog.events.EventType.KEYDOWN handle-click)
    out))

;; Given a click event, produce an artisanal click object with only the
;; information we care about.
(defn- event->click [e]
  {:node (.-target e)
   :timestamp (.getTime (new js/Date))
   :x (.-pageX e)
   :y (.-pageY e)})

;; Given a DOM node, returns a channel of clicks emitted by that node.
(defn clicks [node]
  (map event->click (raw-clicks node)))
