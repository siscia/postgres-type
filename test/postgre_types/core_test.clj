(ns postgre-types.core-test
  (:require [clojure.test :refer :all]
            [clojure.java.jdbc :as j]
            [postgre-types.json :refer [add-json-type]]
            [cheshire.core :refer [generate-string parse-string]]))

(def db-spec {:classname "org.postgresql.Driver"
              :subprotocol "postgresql"
              :subname "//localhost:5432/test"
              :username ""
              :password ""})

(add-json-type generate-string parse-string)


(deftest test-values-in-db
  (j/db-do-commands db-spec
                    (j/create-table-ddl :test
                                        [:data "json"]))
  (j/insert! db-spec :test {:data {:foo :bar}})

  (testing "presence of data"
      (let [data (j/query db-spec "SELECT data FROM test")]
        (is (= data
               '({:data {"foo" "bar"}})))))
  
  (j/db-do-commands db-spec
                    (j/drop-table-ddl :test)))
