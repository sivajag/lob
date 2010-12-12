(ns bob.project
  (:use [clojure.contrib.duck-streams :only [pwd]])
  (:require [leiningen.new :as lein-new]
            [leiningen.core :as lein-core]
            [leiningen.add :as lein-add]))

(defn new [name]
  (lein-new/new name))

(defn project-clj [project-name]
  (lein-core/read-project (str (pwd) "/" project-name "/project.clj")))

(defn current-project-clj []
  (lein-core/read-project (str (pwd) "/project.clj")))

(defn add-dev-deps [project deps]
  (map (fn [[a v]] (lein-add/add project "--dev" a v)) deps))

(defn add-deps [project deps]
  (map (fn [[a v]] (lein-add/add project a v)) deps))