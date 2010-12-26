(ns bob.core
  (:gen-class)
  (:use [bob.utils.ns :only [namespaces-matching]])
  (:require [clojure.contrib.string :as contrib-str]))

(defn exit
  ([code]
     (System/exit code))
  ([] (exit 0)))

(defn abort [& msg]
  (binding [*out* *err*]
    (apply println msg)
    ;(exit 1)
    ))

(defn task-not-found [& _]
  (abort "That's not a task. Use \"bob help\" to list all tasks."))

(defn require-task [task-ns]
  (when-not (find-ns task-ns)
    (require task-ns)))

(defn require-tasks []
  (doall (map require-task (namespaces-matching "bob.tasks"))))

(defn resolve-task
  ([task-name not-found]
     (let [[nz-name task] (contrib-str/split #"\:" task-name)]
       (let [task-ns (symbol (str "bob.tasks." nz-name))
             task (symbol task)]
         (try
          (require-task task-ns)
          (or (ns-resolve task-ns task)
              not-found)
          (catch java.io.FileNotFoundException e
            not-found)))))
  ([task-name] (resolve-task task-name #'task-not-found)))


(defn apply-task [task-name args not-found]
  (let [task (resolve-task task-name not-found)]
    (apply task args)))

(defn -main
  ([& [task-name & args]]
     (require-tasks)
     (let [task-name (or task-name "bob:help")]
       (let [value (apply-task task-name args task-not-found)]
         (when (integer? value)
           (System/exit value)))))
  ([]
     (apply -main (or *command-line-args* ["help"]))))