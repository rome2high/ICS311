DROP TABLE id_list IF EXISTS;

DROP TABLE account_owner IF EXISTS;

DROP INDEX cusomter_last_name_index IF EXISTS;
DROP INDEX cusomter_first_name_index IF EXISTS;
DROP TABLE customer IF EXISTS;

DROP TABLE account IF EXISTS;

DROP TABLE account_type IF EXISTS;

DROP INDEX address_city_index IF EXISTS;
DROP TABLE address IF EXISTS;

DROP TABLE state IF EXISTS;

CREATE TABLE state (
    id INTEGER PRIMARY KEY,
    code CHAR(2) NOT NULL UNIQUE,
    name VARCHAR(30) NOT NULL
);

CREATE TABLE address (
    id INTEGER PRIMARY KEY,
    street VARCHAR(255),
    city VARCHAR(150) NOT NULL,
    state_id INTEGER NOT NULL REFERENCES state(id),
    zip CHAR(5) NOT NULL
);

CREATE INDEX address_city_index ON address(city);

CREATE TABLE account_type (
    id INTEGER PRIMARY KEY,
    code VARCHAR(10) NOT NULL UNIQUE,
    name VARCHAR(40) NOT NULL
);

CREATE TABLE account (
    id INTEGER PRIMARY KEY,
    account_type INTEGER NOT NULL REFERENCES account_type(id),
    account_number VARCHAR(30) NOT NULL UNIQUE,
    balance NUMERIC(14, 2) NOT NULL
);

CREATE TABLE customer (
    id INTEGER PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    mailing_address INTEGER REFERENCES address(id)
);

CREATE INDEX cusomter_first_name_index ON customer(first_name);
CREATE INDEX cusomter_last_name_index ON customer(last_name);

CREATE TABLE account_owner (
    customer_id INTEGER NOT NULL REFERENCES customer(id),
    account_id INTEGER NOT NULL REFERENCES account(id),
    display_order INTEGER NOT NULL,
    PRIMARY KEY (customer_id, account_id),
    UNIQUE(account_id, display_order)
);

CREATE TABLE id_list (
    id INTEGER PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    last_id INTEGER NOT NULL
);

INSERT INTO id_list (id, name, last_id)
VALUES (1, 'id_list', 20);

INSERT INTO id_list (id, name, last_id)
VALUES (10, 'address', 7000);

INSERT INTO id_list (id, name, last_id)
VALUES (11, 'customer', 8000);

INSERT INTO id_list (id, name, last_id)
VALUES (12, 'account', 9000);

INSERT INTO id_list (id, name, last_id)
VALUES (13, 'state', 4000);

INSERT INTO id_list (id, name, last_id)
VALUES (14, 'account_type', 2000);



INSERT INTO state (id, code, name)
VALUES (11, 'IW', 'Iowa');
INSERT INTO state (id, code, name)
VALUES (12, 'CA', 'California');
INSERT INTO state (id, code, name)
VALUES (13, 'TX', 'Texas');

INSERT INTO account_type (id, code, name)
VALUES (1, 'SAV', 'Saving');
INSERT INTO account_type (id, code, name)
VALUES (2, 'CHK', 'Checking');
INSERT INTO account_type (id, code, name)
VALUES (3, 'CD', 'Certificate Deposit');

INSERT INTO address (id, street, city, state_id, zip)
VALUES (1001, '54 South 9th St', 'Minneapolis', 11, '55402');
INSERT INTO address (id, street, city, state_id, zip)
VALUES (1002, '123 cali dr', 'Los Angles', 12, '55402');
INSERT INTO address (id, street, city, state_id, zip)
VALUES (1003, '234 savage dr', 'Savage', 11, '55402');

INSERT INTO customer (id, last_name, first_name, mailing_address)
VALUES (11, 'Doe', 'John', 1001);
INSERT INTO customer (id, last_name, first_name, mailing_address)
VALUES (12, 'Doe', 'Jane', 1001);
insert into customer (id, last_name, first_name, mailing_address)
values (13, 'Mai', 'Romeo', 1003);
INSERT INTO customer (id, last_name, first_name, mailing_address)
VALUES (14, 'Mai', 'vivian', 1002);
INSERT INTO customer (id, last_name, first_name, mailing_address)
VALUES (15, 'Mai', 'Ryan', 1002);
insert into customer (id, last_name, first_name, mailing_address)
values (16, 'Mai', 'Vivian', 1003);




