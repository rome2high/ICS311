
DROP INDEX state_index;
DROP INDEX city_index;
DROP INDEX custName_index;

DROP TABLE customer_account;
DROP TABLE account;
DROP TABLE account_type;
DROP TABLE customer;
DROP TABLE address;
DROP TABLE state;
DROP TABLE id_list;

CREATE TABLE id_list (
    id INTEGER PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    lastID INTEGER NOT NULL,
    UNIQUE (name)
);

CREATE TABLE state (
	id INTEGER PRIMARY KEY,
	code VARCHAR(2) NOT NULL,
	name VARCHAR(30) NOT NULL,
	UNIQUE (code, name)
);

CREATE TABLE address (
	id INTEGER PRIMARY KEY,
	street VARCHAR(30) NOT NULL,
	city VARCHAR(30),
	stateID INTEGER NOT NULL,
	zip VARCHAR(5),
	FOREIGN KEY (stateID) REFERENCES state(id)
);

CREATE TABLE customer (
	id INTEGER PRIMARY KEY,
	lname VARCHAR(30) NOT NULL,
	fname VARCHAR(30),
	addressID INTEGER NOT NULL,
	FOREIGN KEY (addressID) REFERENCES address(id)
);

CREATE TABLE account_type (
	id INTEGER PRIMARY KEY,
	code VARCHAR(3) NOT NULL,
	name VARCHAR(30) NOT NULL
);

CREATE TABLE account (
	id INTEGER PRIMARY KEY,
	typeID INTEGER NOT NULL,
	balance BIGINT NOT NULL,
	FOREIGN KEY (typeID) REFERENCES account_type(id)
);

CREATE TABLE customer_account (
	id INTEGER PRIMARY KEY,
	customerID INTEGER NOT NULL,
	accountID INTEGER,
	FOREIGN KEY (customerID) REFERENCES customer (id),
	FOREIGN KEY (accountID) REFERENCES account (id)
);

CREATE INDEX custName_index ON customer(lname, fname);
CREATE INDEX city_index ON address(city);
CREATE INDEX state_index ON state(code, name);