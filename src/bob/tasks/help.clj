(ns bob.tasks.help
  (:use [clojure.contrib.duck-streams :only [reader writer with-out-writer]]
        [bob.utils.ns :only [namespaces-matching]]))

(def tasks (->> (namespaces-matching "bob.tasks")
                (distinct)
                (sort)))

(defn help []
  (println "I am Bob - I am always eager to impress and help team on projects")
  (println "I can do these following tasks to help you")
  (println "")
  (println "Main Tasks")
  (println "----------")
  (doseq [task-ns tasks]
    (println " " (last (.split (name task-ns) "\\."))))
  
  (println "")
  (println "Tasks from installed Packages")
  (println "-----------------------------")
  (println "")
  (println "Fire me up !!!"))