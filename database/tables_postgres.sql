CREATE TABLE "type" (
    "id" SERIAL PRIMARY KEY,
    identifier VARCHAR(5) UNIQUE NOT NULL,
    description VARCHAR NOT NULL
);

CREATE TABLE "user" (
    "id" SERIAL PRIMARY KEY,
    personal_identifier VARCHAR,
    "name" VARCHAR NOT NULL,
    lastname VARCHAR NOT NULL,
    "type" VARCHAR(5) NOT NULL REFERENCES "type"(identifier) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE crud_log (
    "id" SERIAL PRIMARY KEY,
    "table" VARCHAR NOT NULL,
    "date" TIMESTAMP NOT NULL,
    field VARCHAR NOT NULL,
    record VARCHAR NOT NULL,
    old_value VARCHAR,
    new_value VARCHAR,
    "type" VARCHAR(5) NOT NULL REFERENCES "type"(identifier) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE credential (
    "id" SERIAL PRIMARY KEY,
    user_id INTEGER NOT NULL REFERENCES "user"("id") ON UPDATE CASCADE ON DELETE CASCADE,
    username VARCHAR(6) NOT NULL,
    "password" VARCHAR NOT NULL
);

CREATE TABLE product_collection (
    "id" SERIAL PRIMARY KEY,
    responsible_id INTEGER NOT NULL REFERENCES "user"("id") ON UPDATE CASCADE ON DELETE CASCADE,
    provider VARCHAR,
    "cost" NUMERIC NOT NULL,
    description VARCHAR,
    product_quantity INTEGER NOT NULL
);

CREATE TABLE product (
    "id" SERIAL PRIMARY KEY,
    collection_id INTEGER NOT NULL REFERENCES product_collection("id") ON UPDATE CASCADE ON DELETE CASCADE,
    price NUMERIC NOT NULL,
    benefit NUMERIC NOT NULL,
    isAvailable BOOLEAN NOT NULL
);

CREATE TABLE "transaction" (
    "id" SERIAL PRIMARY KEY,
    "type" VARCHAR(5) NOT NULL REFERENCES "type"(identifier) ON UPDATE CASCADE ON DELETE CASCADE,
    "value" NUMERIC,
    "date" TIMESTAMP NOT NULL
);

CREATE TABLE product_operation (
    "id" SERIAL PRIMARY KEY,
    "type" VARCHAR(5) NOT NULL REFERENCES "type"(identifier) ON UPDATE CASCADE ON DELETE CASCADE,
    collection_id INTEGER NOT NULL REFERENCES product_collection("id") ON UPDATE CASCADE ON DELETE CASCADE,
    product_amount INTEGER NOT NULL,
    "date" TIMESTAMP NOT NULL,
    responsible_id INTEGER NOT NULL REFERENCES "user"("id") ON UPDATE CASCADE ON DELETE CASCADE,
    transaction_id INTEGER NOT NULL REFERENCES "transaction"("id") ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE sell (
    "id" SERIAL PRIMARY KEY,
    "date" TIMESTAMP NOT NULL,
    collection_id INTEGER NOT NULL REFERENCES product_collection("id") ON UPDATE CASCADE ON DELETE CASCADE,
    responsible_id INTEGER NOT NULL REFERENCES "user"("id") ON UPDATE CASCADE ON DELETE CASCADE,
    product_operation_id INTEGER NOT NULL REFERENCES product_operation("id") ON UPDATE CASCADE ON DELETE CASCADE
);