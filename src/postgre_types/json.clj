;; Copyright (c) Simone Mosciatti, 2014. All rights reserved.  The use
;; and distribution terms for this software are covered by the Eclipse
;; Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
;; which can be found in the file epl-v10.html at the root of this
;; distribution.  By using this software in any fashion, you are
;; agreeing to be bound by the terms of this license.  You must not
;; remove this notice, or any other, from this software.


(ns ^{:author "Simone Mosciatti"}
  postgre-types.json
  (:require [clojure.java.jdbc :as jdbc])
  (:import org.postgresql.util.PGobject))

(defn add-json-type [write-json read-json]
  (let [to-pgjson (fn [value] (doto  (PGobject.)
                                (.setType "json")
                                (.setValue (write-json value))))]

    (extend-protocol jdbc/ISQLValue
      clojure.lang.IPersistentMap
      (sql-value [value] (to-pgjson value))
      clojure.lang.IPersistentVector
      (sql-value [value] (to-pgjson value)))

    (extend-protocol jdbc/IResultSetReadColumn
      PGobject
      (result-set-read-column [pgobj _metadata _index]
        (let [type  (.getType pgobj)
              value (.getValue pgobj)]
          (case type
            "json" (read-json value)
            :else value))))))

(defn add-jsonb-type [write-json read-json]
  (let [to-pgjson (fn [value] (doto  (PGobject.)
                                (.setType "jsonb")
                                (.setValue (write-json value))))]

    (extend-protocol jdbc/ISQLValue
      clojure.lang.IPersistentMap
      (sql-value [value] (to-pgjson value))
      clojure.lang.IPersistentVector
      (sql-value [value] (to-pgjson value)))

    (extend-protocol jdbc/IResultSetReadColumn
      PGobject
      (result-set-read-column [pgobj _metadata _index]
        (let [type  (.getType pgobj)
              value (.getValue pgobj)]
          (case type
            "jsonb" (read-json value)
            :else value))))))
