(ns bob.core
  (:gen-class)
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

(defn resolve-task1
  ([task not-found]
     (let [task-ns (symbol (str "bob.tasks." task))
           task (symbol task)]
       (try
         (when-not (find-ns task-ns)
           (require task-ns))
         (or (ns-resolve task-ns task)
             not-found)
         (catch java.io.FileNotFoundException e
           not-found))))
  ([task] (resolve-task1 task #'task-not-found)))

(defn resolve-task
  ([task-name not-found]
     (let [[nz-name task] (contrib-str/split #"\:" task-name)]
       (let [task-ns (symbol (str "bob.tasks." nz-name))
             task (symbol task)]
         (try
          (when-not (find-ns task-ns)
            (require task-ns))
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
     (let [task-name (or task-name "bob:help")]
       (let [value (apply-task task-name args task-not-found)]
         (when (integer? value)
           (System/exit value)))))
  ([]
     (apply -main (or *command-line-args* ["help"]))))