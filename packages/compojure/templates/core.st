(ns $name$.core
  (:use [ring.adapter.jetty :only [run-jetty]]
        [compojure.core :only [defroutes GET POST]]
        [hiccup.core :only [html]]
        [hiccup.page-helpers :only [doctype include-css link-to]])
  (:require [compojure.route :as route]))

(defroutes routes
  ;;Common
  (GET "/" [] ("Welcome"))
  
  ;;General
  (route/files "/")
  (route/not-found "<h1>Not Found</h1>"))

(def application-routes (-> routes))

(defn start []
  (run-jetty (var application-routes) {:port 8080
                                       :join? false}))
