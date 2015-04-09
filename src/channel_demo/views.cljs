(ns channel-demo.views
  (:require [channel-demo.data :as data]
            [om.core :as om :include-macros true]
            [clojure.string :as string]
            [sablono.core :as html :refer-macros [html]]))

;; Returns the supplied string class names, without nils, as a space-separated
;; string suitable for use as a #js className. Class names may already include
;; spaces, naturally; (classes "inner button" "selected") will return "inner
;; button selected".
(defn classes [& cs]
  (string/join " " (remove nil? cs)))


;; A helper which produces a Sablono-like vector. Provide a container in
;; Sablono format, and a vector of children produced by om/build-all.
;;
;; Example:
;; (html-container
;;   [:div {:class 'parent'}] (om/build-all component data))
(defn html-container [container children]
  (html (vec (concat container children))))

(defn stripe [color owner]
  (reify
    om/IDisplayName
    (display-name [_] "Stripe")
    om/IRender
    (render [_]
      (html
        [:div {:class "stripe"} (name color)]))))

(defn stripes [colors owner]
  (reify
    om/IDisplayName
    (display-name [_] "Stripes")
    om/IRender
    (render [_]
      (if (empty? colors)
        (html
          [:div {:class "stripes"} "No content yet!"])
        (html-container
          [:div {:class "stripes"}]
          (om/build-all stripe colors))))))

(defn main [{:keys [colors] :as app} owner]
  (reify
    om/IDisplayName
    (display-name [_] "App")
    om/IRender
    (render [_]
      (html
        [:div {:class "main"}
         [:h1 "Channel Demo"]
         (om/build stripes colors)]))))
