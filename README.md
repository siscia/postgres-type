# postgre-types

A Clojure library designed to let jdbc talk the PostgresSQL json type.

## Install

[![Clojars Project](http://clojars.org/postgre-types/latest-version.svg)](http://clojars.org/postgre-types)

## Usage

```clojure
(ns your-namespace.foo
    (:require [postgre-types.json :refer [add-json-type]]))

(add-json-type f-write-json f-read-json)

;; from now on jdbc knows how to deal with the PostgreSQL json type.

```

You can also add `jsonb` with the same interface.

```clojure
(ns your-namespace.foo
    (:require [postgre-types.json :refer [add-jsonb-type]]))

(add-jsonb-type f-write-json f-read-json)

;; from now on jdbc knows how to deal with the PostgreSQL jsonb type.

```

Please note that you need to pass two function to `add-json-type`.

The first (f-write-json) is the function that providden the data structure return a json rappresentation of such structure, the second (f-read-json) is the function that providen the json return the correct data structure.

Practical examples of such structure could be `cheshire.core/generate-string` and `cheshire.core/parse-string` or also `clj-json.core/generate-string` and `clj-json.core/parse-string` or also `clojure.data.json/write-str` and `clojure.data.json/read-str`.

Very basic example is providen in the test.

## Test

To run the test you need to have a Postgres instance running.

Then you should create a database, call it `test`.

Finally create an user, call also the user `test` and give a password to the user, give the password `test`.

You can run those command in order:

``` bash
createdb test # create the database
sudo -u postgres createuser test -d -P # create the user
# type `test` two times to create the password
```

Please note that the comand `sudo -u postgres createuser test -d -P ` is pretty Ubuntu specific, if it doesn't work you should just try to create a new user for your Postgres.

## License

Copyright © 2015 by Simone Mosciatti

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
