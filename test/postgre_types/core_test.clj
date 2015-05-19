(ns postgre-types.core-test
  (:require [clojure.test :refer :all]
            [clojure.java.jdbc :as j]
            [postgre-types.json :refer [add-json-type add-jsonb-type]]
            [cheshire.core :refer [generate-string parse-string]]))

(def db-spec {:classname "org.postgresql.Driver"
              :subprotocol "postgresql"
              :subname "//localhost:5432/test"
              :user "test"
              :password "test"})

(deftest test-values-in-db
  
  (add-json-type generate-string parse-string)

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


(deftest test-jsonb

  (add-jsonb-type generate-string parse-string)
  
  (j/db-do-commands db-spec
                    (j/create-table-ddl :test
                                        [:data "jsonb"]))
  (j/insert! db-spec :test {:data {:foo :bar}})

  (testing "presence of data"
    (let [data (j/query db-spec "SELECT data FROM test")]
      (is (= data
             '({:data {"foo" "bar"}})))))
  
  (j/db-do-commands db-spec
                    (j/drop-table-ddl :test)))

(deftest test-assertion-safeness
  (testing "json"
    (is (thrown? AssertionError (add-json-type "foo" (fn [_] _))))
    (is (thrown? AssertionError (add-json-type (fn [_] _) "bar"))))
  (testing "jsonb"
    (is (thrown? AssertionError (add-jsonb-type "foo" (fn [_] _))))
    (is (thrown? AssertionError (add-jsonb-type (fn [_] _) "bar")))))
