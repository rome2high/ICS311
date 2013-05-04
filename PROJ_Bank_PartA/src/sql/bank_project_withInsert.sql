
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

INSERT INTO state (id, code, name)
VALUES (1, 'MN', 'Minnesota');
INSERT INTO state (id, code, name)
VALUES (2, 'CA', 'California');
INSERT INTO state (id, code, name)
VALUES (3, 'TX', 'Texas');
INSERT INTO account_type (id, code, name)
VALUES (1, 'SAV', 'Saving');
INSERT INTO account_type (id, code, name)
VALUES (2, 'CHK', 'Checking');
INSERT INTO account_type (id, code, name)
VALUES (3, 'CD', 'Certificate Deposit');

INSERT INTO address (id, street, city, stateID, zip)
VALUES (1001, '54 South 9th St', 'Minneapolis', 1, '55402');
INSERT INTO address (id, street, city, stateID, zip)
VALUES (1002, '123 cali dr', 'Los Angles', 2, '55402');
INSERT INTO address (id, street, city, stateID, zip)
VALUES (1003, '234 savage dr', 'Savage', 1, '55402');


select *
from address;

INSERT INTO customer (id, lname, fname, addressID)
VALUES (11, 'Doe', 'John', 1001);
INSERT INTO customer (id, lname, fname, addressID)
VALUES (12, 'Doe', 'Jane', 1001);
insert into customer (id, lname, fname, addressID)
values (13, 'Mai', 'Romeo', 1003);
INSERT INTO customer (id, lname, fname, addressID)
VALUES (14, 'Mai', 'vivian', 1002);
INSERT INTO customer (id, lname, fname, addressID)
VALUES (15, 'Mai', 'Ryan', 1002);
insert into customer (id, lname, fname, addressID)
values (16, 'Mai', 'Vivian', 1003);

select *
from customer;

INSERT INTO account (id, typeID, balance)
VALUES (111, 1, 400.50);
INSERT INTO account (id, typeID, balance)
VALUES (112, 2, 10000);
INSERT INTO account (id, typeID, balance)
VALUES (113, 3, 3000);
INSERT INTO account (id, typeID, balance)
VALUES (114, 3, 40.09);
INSERT INTO account (id, typeID, balance)
VALUES (115, 1, 400);

select *
from account;

INSERT INTO customer_account (id, customerID)
VALUES (1, 13);
INSERT INTO customer_account (id, customerID, accountID)
VALUES (2, 11, 111);
INSERT INTO customer_account (id, customerID, accountID)
VALUES (3, 12, 111);
INSERT INTO customer_account (id, accountID)
VALUES (4, 12);
INSERT INTO customer_account (id, customerID, accountID)
VALUES (5, 15, 113);
INSERT INTO customer_account (id, customerID, accountID)
VALUES (6, 15, 114);
INSERT INTO customer_account (id, customerID, accountID)
VALUES (7, 15, 115);
INSERT INTO customer_account (id, customerID, accountID)
VALUES (8, 16, 111);
INSERT INTO customer_account (id, customerID, accountID)
VALUES (9, 15, null);

select *
from customer_account;

select *
from customer_account ca
left join customer c on ca.customerID = c.id
left join address add on c.addressID = add.id
left join state st on add.stateid = st.id
left join account a on ca.accountID = a.id
left join account_type act on a.typeID = act.id
;

















