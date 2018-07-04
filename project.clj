(defproject postgres-json "0.0.4"
  :description "Integration with PostgresSQL multiple types"
  :url "https://github.com/ecbrown/postgres-json"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/java.jdbc "0.3.6"]
                 [org.postgresql/postgresql "9.4-1201-jdbc41"]]
  :repositories [#_["release" "https://clojars.org/siscia"]]
  :profiles {:dev {:dependencies [[cheshire "5.3.1"]]}})
