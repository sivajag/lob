(ns bob.project
  (:use [clojure.contrib.duck-streams :only [pwd]])
  (:require [leiningen.new :as lein-new]
            [leiningen.core :as lein-core]
            [leiningen.add :as lein-add]
            [leiningen.deps :as lein-deps]))

(defn new [name]
  (lein-new/new name))

(defn load-project [name]
  (lein-core/read-project (str (pwd) "/" name "/project.clj")))

(defn add-dev-deps [name deps]
  (let [project (load-project name)]
    (doall (map (fn [[a v]] (lein-add/add project "--dev" a v)) deps))))

(defn add-deps [name deps]
  (let [project (load-project name)]
    (doall (map (fn [[a v]] (lein-add/add project a v)) deps))))

(defn run-deps [name]
  (let [project (load-project name)]
    (lein-deps/deps project)))