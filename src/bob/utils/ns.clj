(ns bob.utils.ns
  (:require [clojure.contrib.find-namespaces :as find-ns]))

(defn tasks-in [the-ns]
  (filter #(let [m (meta %)] (contains? m :task))
       (vals (ns-interns the-ns))))

(defn namespaces-matching [prefix]
  (filter #(.startsWith (name %) prefix) 
          (find-ns/find-namespaces-on-classpath)))
