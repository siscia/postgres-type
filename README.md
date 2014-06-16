# postre-types

A Clojure library designed to let jdbc talk the PostgresSQL json type.

## Usage

```clojure
(ns your-namespace.foo
    (:require postgre-types.json :refer [add-json-type]))

(add-json-type f-write-json f-read-json)

;; from now on jdbc know how to deal with the PostgreSQL json type.

```

Please note that you need to pass two function to `add-json-type`.

The first (f-write-json) is the function that providden the data structure return a json rappresentation of such structure, the second (f-read-json) is the function that providen the json return the correct data structure.

Practical examples of such structure could be `cheshire.core/generate-string` and `cheshire.core/parse-string` or also `clj-json.core/generate-string` and `clj-json.core/parse-string` or also `clojure.data.json/write-str` and `clojure.data.json/read-str`.

Very basic example is providen in the test.

## License

Copyright © 2014 by Simone Mosciatti

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
