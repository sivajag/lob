(ns bob.project
  (:require [leiningen.new :as lein-new]
            [leiningen.core :as lein-core]
            [leiningen.add :as lein-add]))

(defn new [name]
  (lein-new/new name))

(defn add-dev-deps [project deps]
  (map (fn [[a v]] (lein-add/add project "--dev" a v)) deps))

(defn add-deps [deps])