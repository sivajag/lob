(ns bob.tasks.help
  (:use [clojure.contrib.duck-streams :only [reader writer with-out-writer]]
        [bob.utils.ns :only [namespaces-matching tasks-in]]))

(def tasks (->> (namespaces-matching "bob.tasks")
                (distinct)
                (sort)))

(defn help-string [task-ns]
  (map #(let [m (meta %)] (str (:name m) " -- " (:doc m)))
       (tasks-in task-ns)))

(defn help 
  "Prints help"
  {:task true}
  []
  (println "I am Bob - I am always eager to impress and help team on projects")
  (println "I can do these following tasks to help you")
  (println "")
  (println "Main Tasks")
  (println "----------")
  (doseq [task-ns tasks]
    (doall (map println (help-string task-ns))))
  
  (println "")
  (println "Tasks from installed Packages")
  (println "-----------------------------")
  (println "")
  (println "Fire me up !!!"))