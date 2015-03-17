(defproject postgre-types "0.0.3"
  :description "Integration with PostgresSQL multiple types"
  :url "https://github.com/siscia/postgres-type"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/java.jdbc "0.3.6"]
                 [org.postgresql/postgresql "9.4-1201-jdbc41"]]
  :profiles {:dev {:dependencies [[cheshire "5.3.1"]]}})
