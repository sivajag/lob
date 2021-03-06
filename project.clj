(defproject bob "0.0.1-SNAPSHOT"
  :description "Bob - Always eager to impress and help team on projects"
  :dependencies [[org.clojure/clojure "1.2.0"]
                 [org.clojure/clojure-contrib "1.2.0"]
                 [org.apache.ant/ant "1.7.1"]
                 [jline "0.9.94"]
                 [robert/hooke "1.1.0"]
                 [org.apache.maven/maven-ant-tasks "2.0.10" :exclusions [ant]]
                 [org.bituf/clj-stringtemplate "0.2"]
                 [org.clojars.trptcolin/lein-search "0.3.3"]]
  :dev-dependencies [[swank-clojure "1.2.1"]]
  :main bob.core)
