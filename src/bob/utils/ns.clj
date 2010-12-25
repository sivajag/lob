(ns bob.utils.ns
  (:require [clojure.contrib.find-namespaces :as find-ns]
            [clojure.contrib.string :as contrib-str]))

(defn tasks-in [nz]
  (filter #(let [m (meta %)] (contains? m :task))
       (vals (ns-interns nz))))

(defn ns-str [f]
  (str (:ns (meta f))))

(defn ns-last-str [f]
  (last (contrib-str/split #"\." (ns-str f))))

(defn namespaces-matching [prefix]
  (filter #(.startsWith (name %) prefix) 
          (find-ns/find-namespaces-on-classpath)))
