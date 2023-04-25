/*Tablas principales*/

CREATE TABLE "type" (
    "id" SERIAL PRIMARY KEY,
    identifier VARCHAR(5) UNIQUE NOT NULL,
    description VARCHAR NOT NULL
);

CREATE TABLE "user" (
    "id" SERIAL PRIMARY KEY,
    personal_identifier VARCHAR UNIQUE NOT NULL,
    "name" VARCHAR NOT NULL,
    lastname VARCHAR NOT NULL,
    "type" VARCHAR(5) NOT NULL REFERENCES "type"(identifier) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE credential (
    "id" SERIAL PRIMARY KEY,
    user_id INTEGER NOT NULL REFERENCES "user"("id") ON UPDATE CASCADE ON DELETE CASCADE,
    username VARCHAR(6) UNIQUE NOT NULL,
    "password" VARCHAR NOT NULL
);

CREATE TABLE product_collection (
    "id" SERIAL PRIMARY KEY,
    user_id INTEGER NOT NULL REFERENCES "user"("id") ON UPDATE CASCADE ON DELETE CASCADE,
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
    is_available BOOLEAN NOT NULL
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
    user_id INTEGER NOT NULL REFERENCES "user"("id") ON UPDATE CASCADE ON DELETE CASCADE,
    transaction_id INTEGER NOT NULL REFERENCES "transaction"("id") ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE sell (
    "id" SERIAL PRIMARY KEY,
    "date" TIMESTAMP NOT NULL,
    collection_id INTEGER NOT NULL REFERENCES product_collection("id") ON UPDATE CASCADE ON DELETE CASCADE,
    user_id INTEGER NOT NULL REFERENCES "user"("id") ON UPDATE CASCADE ON DELETE CASCADE,
    product_operation_id INTEGER NOT NULL REFERENCES product_operation("id") ON UPDATE CASCADE ON DELETE CASCADE
);

/*Tablas Log*/
CREATE TABLE "type_log" (
    "id" SERIAL,
    identifier VARCHAR(5),
    description VARCHAR,
    action text,
    log_time timestamp default now()
);

CREATE TABLE "user_log" (
    "id" SERIAL,
    personal_identifier VARCHAR,
    "name" VARCHAR,
    lastname VARCHAR,
    action text,
    log_time timestamp default now()
);

CREATE TABLE product_collection_log (
    "id" SERIAL,
    user_id INTEGER,
    provider VARCHAR,
    "cost" NUMERIC,
    description VARCHAR,
    product_quantity INTEGER,
    action text,
    log_time timestamp default now()
);

CREATE TABLE product_log (
    "id" SERIAL,
    collection_id INTEGER,
    price NUMERIC,
    benefit NUMERIC,
    is_available BOOLEAN,
    action text,
    log_time timestamp default now()
);

CREATE TABLE "transaction_log" (
    "id" SERIAL,
    "type" VARCHAR(5),
    "value" NUMERIC,
    "date" TIMESTAMP,
    action text,
    log_time timestamp default now()
);

CREATE TABLE product_operation_log (
    "id" SERIAL,
    "type" VARCHAR(5),
    collection_id INTEGER,
    product_amount INTEGER,
    "date" TIMESTAMP,
    user_id INTEGER,
    transaction_id INTEGER,
    action text,
    log_time timestamp default now()
);

CREATE TABLE sell_log (
    "id" SERIAL,
    "date" TIMESTAMP,
    collection_id INTEGER,
    user_id INTEGER,
    product_operation_id INTEGER,
    action text,
    log_time timestamp default now()
);

/*Funciones para logs*/
CREATE OR REPLACE FUNCTION type_log_function()
RETURNS TRIGGER AS $$
BEGIN
  IF (TG_OP = 'INSERT') THEN
    INSERT INTO table_log (id, identifier, description, action) VALUES (NEW.id, NEW.identifier, NEW.description, 'insert');
  ELSIF (TG_OP = 'UPDATE') THEN
    INSERT INTO table_log (id, identifier, description, action) VALUES (NEW.id, NEW.col1, NEW.col2, 'update');
  ELSIF (TG_OP = 'DELETE') THEN
    INSERT INTO table_log (id, identifier, description, action) VALUES (OLD.id, OLD.col1, OLD.col2, 'delete');
  END IF;
  RETURN NULL;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION user_log_function()
RETURNS TRIGGER AS $$
BEGIN
  IF (TG_OP = 'INSERT') THEN
    INSERT INTO table_log (id, personal_identifier,"name",lastname, action) VALUES (NEW.id, NEW.personal_identifier,NEW.name,NEW.lastname, 'insert');
  ELSIF (TG_OP = 'UPDATE') THEN
    INSERT INTO table_log (id, personal_identifier,"name",lastname, action) VALUES (NEW.id, NEW.personal_identifier,NEW.name,NEW.lastname, 'update');
  ELSIF (TG_OP = 'DELETE') THEN
    INSERT INTO table_log (id, personal_identifier,"name",lastname, action) VALUES (OLD.id, OLD.personal_identifier,OLD.name,OLD.lastname, 'delete');
  END IF;
  RETURN NULL;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION product_collection_log_function()
RETURNS TRIGGER AS $$
BEGIN
  IF (TG_OP = 'INSERT') THEN
    INSERT INTO table_log (id, user_id,provider,"cost",description,product_quantity, action) 
    VALUES (NEW.id, NEW.user_id,NEW.provider,NEW.cost,NEW.description,NEW.product_quantity, 'insert');
  ELSIF (TG_OP = 'UPDATE') THEN
    INSERT INTO table_log (id, user_id,provider,"cost",description,product_quantity, action) 
    VALUES (NEW.id, NEW.user_id,NEW.provider,NEW.cost,NEW.description,NEW.product_quantity, 'update');
  ELSIF (TG_OP = 'DELETE') THEN
    INSERT INTO table_log (id, user_id,provider,"cost",description,product_quantity, action) 
    VALUES (OLD.id, OLD.user_id,OLD.provider,OLD.cost,OLD.description,OLD.product_quantity, 'delete');
  END IF;
  RETURN NULL;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION product_log_function()
RETURNS TRIGGER AS $$
BEGIN
  IF (TG_OP = 'INSERT') THEN
    INSERT INTO table_log (id, collection_id,price,benefit,is_available, action) 
    VALUES (NEW.id, NEW.collection_id,NEW.price,NEW.benefit,NEW.is_available, 'insert');
  ELSIF (TG_OP = 'UPDATE') THEN
    INSERT INTO table_log (id, collection_id,price,benefit,is_available, action) 
    VALUES (NEW.id, NEW.collection_id,NEW.price,NEW.benefit,NEW.is_available, 'update');
  ELSIF (TG_OP = 'DELETE') THEN
    INSERT INTO table_log (id, collection_id,price,benefit,is_available, action) 
    VALUES (OLD.id, OLD.collection_id,OLD.price,OLD.benefit,OLD.is_available, 'delete');
  END IF;
  RETURN NULL;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION transaction_log_function()
RETURNS TRIGGER AS $$
BEGIN
  IF (TG_OP = 'INSERT') THEN
    INSERT INTO table_log (id, "type","value","date", action) VALUES (NEW.id, NEW.type,NEW.value,NEW.date, 'insert');
  ELSIF (TG_OP = 'UPDATE') THEN
    INSERT INTO table_log (id, "type","value","date", action) VALUES (NEW.id, NEW.type,NEW.value,NEW.date, 'update');
  ELSIF (TG_OP = 'DELETE') THEN
    INSERT INTO table_log (id, "type","value","date", action) VALUES (OLD.id, OLD.type,OLD.value,OLD.date, 'delete');
  END IF;
  RETURN NULL;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION product_operation_log_function()
RETURNS TRIGGER AS $$
BEGIN
  IF (TG_OP = 'INSERT') THEN
    INSERT INTO table_log (id, "type",collection_id,product_amount,"date",user_id,transaction_id, action) 
    VALUES (NEW.id, NEW.type,NEW.collection_id,NEW.product_amount,NEW.date,NEW.user_id,NEW.transaction_id, 'insert');
  ELSIF (TG_OP = 'UPDATE') THEN
    INSERT INTO table_log (id, "type",collection_id,product_amount,"date",user_id,transaction_id, action) 
    VALUES (NEW.id, NEW.type,NEW.collection_id,NEW.product_amount,NEW.date,NEW.user_id,NEW.transaction_id, 'update');
  ELSIF (TG_OP = 'DELETE') THEN
    INSERT INTO table_log (id, "type",collection_id,product_amount,"date",user_id,transaction_id, action) 
    VALUES (OLD.id, OLD.type,OLD.collection_id,OLD.product_amount,OLD.date,OLD.user_id,OLD.transaction_id, 'delete');
  END IF;
  RETURN NULL;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION sell_log_function()
RETURNS TRIGGER AS $$
BEGIN
  IF (TG_OP = 'INSERT') THEN
    INSERT INTO table_log (id, "date",collection_id,user_id,product_operation_id, action) 
    VALUES (NEW.id, NEW.collection_id,NEW.user_id,NEW.product_operation_id, 'insert');
  ELSIF (TG_OP = 'UPDATE') THEN
    INSERT INTO table_log (id, "date",collection_id,user_id,product_operation_id, action) 
    VALUES (NEW.id, NEW.collection_id,NEW.user_id,NEW.product_operation_id, 'update');
  ELSIF (TG_OP = 'DELETE') THEN
    INSERT INTO table_log (id, "date",collection_id,user_id,product_operation_id, action) 
    VALUES (OLD.id, OLD.collection_id,OLD.user_id,OLD.product_operation_id, 'delete');
  END IF;
  RETURN NULL;
END;
$$ LANGUAGE plpgsql;

/*Triggers*/
CREATE OR REPLACE TRIGGER type_trigger
AFTER INSERT OR UPDATE OR DELETE
ON public.type
FOR EACH ROW
EXECUTE FUNCTION type_log_function();

CREATE OR REPLACE TRIGGER user_trigger
AFTER INSERT OR UPDATE OR DELETE
ON public.user
FOR EACH ROW
EXECUTE FUNCTION user_log_function();

CREATE OR REPLACE TRIGGER product_collection_trigger
AFTER INSERT OR UPDATE OR DELETE
ON public.product_collection
FOR EACH ROW
EXECUTE FUNCTION product_collection_log_function();

CREATE OR REPLACE TRIGGER product_trigger
AFTER INSERT OR UPDATE OR DELETE
ON public.product
FOR EACH ROW
EXECUTE FUNCTION product_log_function();

CREATE OR REPLACE TRIGGER transaction_trigger
AFTER INSERT OR UPDATE OR DELETE
ON public.transaction
FOR EACH ROW
EXECUTE FUNCTION transaction_log_function();

CREATE OR REPLACE TRIGGER product_operation_trigger
AFTER INSERT OR UPDATE OR DELETE
ON public.product_operation
FOR EACH ROW
EXECUTE FUNCTION product_operation_log_function();

CREATE OR REPLACE TRIGGER sell_trigger
AFTER INSERT OR UPDATE OR DELETE
ON public.sell
FOR EACH ROW
EXECUTE FUNCTION sell_log_function();