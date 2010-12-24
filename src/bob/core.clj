(ns bob.core
  (:gen-class))

(def a 1)

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

(defn resolve-task
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
  ([task] (resolve-task task #'task-not-found)))


(defn apply-task [task-name args not-found]
  (let [task (resolve-task task-name not-found)]
    (apply task args)))

(defn -main
  ([& [task-name & args]]
     (let [task-name (or task-name "help")]
       (let [value (apply-task task-name args task-not-found)]
         (when (integer? value)
           (System/exit value)))))
  ([]
     (apply -main (or *command-line-args* ["help"]))))