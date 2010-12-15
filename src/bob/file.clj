(ns bob.file
  (:use [org.bituf.clj-stringtemplate]
        [clojure.contrib.duck-streams :only (reader writer with-out-writer)]
        [bob.project :as project]))

(defn file-location [project destination]
  (str (:root project) "/" destination))

(defn write [project-name template destination attrs]
  (let [project (project/load-project project-name)
        view (get-view-from-dir template "/Users/admin/work/clojure/clj-bob/packages")
        filled (fill-view! view attrs)]
    (with-out-writer (file-location project destination)
      (println (render-view filled)))))