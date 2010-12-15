(ns compojure.core
  (:require [bob.project :as project]
            [bob.file :as file]))

(def version "1.0")

(def run-deps {"ring/ring-jetty-adapter" "0.2.5"
               "compojure" "0.4.1"
               "hiccup" "0.2.6"})

(def dev-deps {"swank-clojure" "1.3.0-SNAPSHOT"})

(defn new 
  "Creates a new compojure project to get started with"
  [name]
  (project/new name)
  (project/add-dev-deps name dev-deps)
  (project/add-deps name run-deps)
  (project/run-deps name)
  (file/write name 
              "compojure/templates/core" 
              (str "src/" name "/core.clj") 
              {"name" name}))

(defn add 
  "Generate new route or model to compojure project"
  [])

(defn list-libraries 
  "List libraries that could be added to your application"[]
  ["jquery"])


(defn install-library 
  "Installing libraries"
  [lib])