(ns bob.project
  (:use [clojure.contrib.duck-streams :only [pwd]])
  (:require [leiningen.new :as lein-new]
            [leiningen.core :as lein-core]
            [leiningen.add :as lein-add]))

(defn new [name]
  (lein-new/new name))

(defn load-project [project-name]
  (lein-core/read-project (str (pwd) "/" project-name "/project.clj")))

(defn add-dev-deps [project deps]
  (doall (map (fn [[a v]] (lein-add/add project "--dev" a v)) deps)))

(defn add-deps [project deps]
  (doall (map (fn [[a v]] (lein-add/add project a v)) deps)))