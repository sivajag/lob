(ns bob.utils.file
  (:require [clojure.contrib.find-namespaces :as ns-utils :only [find-clojure-sources-in-dir]]))

(defn find-tasks [dir]
  (map #(load-file (.getAbsolutePath %)) (ns-utils/find-clojure-sources-in-dir dir)))