(ns bob.tasks.help
  (:use [bob.utils.ns :only [namespaces-matching]]))

(def tasks (->> (namespaces-matching "bob.tasks")
                (distinct)
                (sort)))

(defn get-arglists [task]
  (for [args (:arglists (meta task))]
    (vec (remove #(= 'project %) args))))

;; affected by clojure ticket #130: bug of AOT'd namespaces losing metadata
(defn help-summary-for [task-ns]
  (require task-ns)
  (let [task-name (last (.split (name task-ns) "\\."))]
    (str task-name (apply str (repeat (- 8 (count task-name)) " "))
         " - " (:doc (meta (find-ns task-ns))))))

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