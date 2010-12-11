(ns compojure)

(def version "1.0")

(def run-deps {"org.clojure/clojure"  "1.2.0"
               "org.clojure/clojure-contrib" "1.2.0"
               "ring/ring-jetty-adapter" "0.2.5"
               "compojure" "0.4.1"
               "hiccup" "0.2.6"
               "sandbar" "0.3.0-SNAPSHOT"})

(def dev-deps {"swank-clojure" "1.3.0-SNAPSHOT"})


(defn new 
  "Creates a new compojure project to get started with"
  [])

(defn add 
  "Generate new route or model to compojure project"
  [])

(defn list-libraries 
  "List libraries that could be added to your application"[]
  ["jquery"])


(defn install-library 
  "Installing libraries"
  [lib])