
DROP TABLE owner;
DROP TABLE account;
DROP TABLE account_type;
DROP TABLE customer;
DROP TABLE address;
DROP TABLE state;
DROP TABLE id_list;

CREATE TABLE id_list (
    id INTEGER PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    last_id INTEGER NOT NULL,
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
	street VARCHAR(30),
	city VARCHAR(30),
	state_ID INTEGER,
	zip VARCHAR(5),
	FOREIGN KEY (state_ID) REFERENCES state(id)
);

CREATE TABLE customer (
	id INTEGER PRIMARY KEY,
	lname VARCHAR(30),
	fname VARCHAR(25),
	address_ID INTEGER,
	FOREIGN KEY (address_ID) REFERENCES address(id)
);

CREATE TABLE account_type (
	id INTEGER PRIMARY KEY,
	code VARCHAR(3),
	name VARCHAR(30)
);

CREATE TABLE account (
	id INTEGER PRIMARY KEY,
	type_ID INTEGER,
	balace INTEGER,
	FOREIGN KEY (type_ID) REFERENCES account_type(id)
);

CREATE TABLE owner (
	id INTEGER PRIMARY KEY,
	customer_ID INTEGER,
	account_ID INTEGER,
	FOREIGN KEY (customer_ID) REFERENCES customer (id),
	FOREIGN KEY (account_ID) REFERENCES account (id)
);









