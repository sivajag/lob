(ns bob.project
  (:require [leiningen.new :as lein-new]))

(defn new [name]
  (lein-new/new name))


(defn add-dev-dep [project-path deps])


(defn add-deps [deps])