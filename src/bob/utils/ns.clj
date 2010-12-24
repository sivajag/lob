(ns bob.utils.ns
  (:require [clojure.contrib.find-namespaces :as find-ns]))

(defn namespaces-matching [prefix]
  (map name 
       (filter #(.startsWith (name %) prefix) 
               (find-ns/find-namespaces-on-classpath))))
