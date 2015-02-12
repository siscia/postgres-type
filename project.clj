(defproject postgre-types "0.0.2"
  :description "Integration with PostgresSQL multiple types"
  :url "https://github.com/siscia/postgres-type"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [org.clojure/java.jdbc "0.3.3"]
                 [org.postgresql/postgresql "9.3-1102-jdbc41"]]
  :profiles {:dev {:dependencies [[cheshire "5.3.1"]]}})
