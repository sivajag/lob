(ns bob.file
  (:use [org.bituf.clj-stringtemplate]
        [clojure.contrib.duck-streams :only (reader writer with-out-writer)]))

(defn file-location [project destination]
  (str (:root project) "/" destination))

(defn write [project template destination attrs]
  (let [view (get-view-from-dir "compojure/templates/core" "/Users/admin/work/clojure/clj-bob/packages")
        filled (fill-view! view attrs)]
    (with-out-writer (file-location project destination)
      (println (render-view filled)))))