DROP TABLE id_list;
DROP TABLE customer;
DROP TABLE address;

CREATE TABLE address (
	id INTEGER PRIMARY KEY,
	addr1 VARCHAR(30),
	addr2 VARCHAR(30),
	city VARCHAR(30),
	state VARCHAR(2),
	zip VARCHAR(5)
);

CREATE TABLE customer (
	id INTEGER PRIMARY KEY,
	lname VARCHAR(30),
	fname VARCHAR(25),
	email VARCHAR(40),
	bill_addr INTEGER,
	FOREIGN KEY (bill_addr) REFERENCES address(id)
);

CREATE TABLE id_list (
    id INTEGER PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    last_id INTEGER NOT NULL,
    UNIQUE (name)
);

INSERT INTO address (id, addr1, city, state, zip)
VALUES (1001, '54 South 9th St', 'Minneapolis', 'MN', '55402');

INSERT INTO address (id, addr1, city, state, zip)
VALUES (1002, '27 West Industrival Blvd', 'Edina', 'MN', '55423');

INSERT INTO address (id, addr1, city, state, zip)
VALUES (1003, '123 Main St', 'Plymouth', 'MN', '55442');

INSERT INTO address (id, addr1, city, state, zip)
VALUES (1004, '123 Main St', 'Plymouth', 'MA', '01003');

INSERT INTO customer (id, lname, fname, bill_addr)
VALUES (2001, 'Johnson', 'Jane', 1002);

INSERT INTO customer (id, lname, fname, bill_addr)
VALUES (2002, 'Johnson', 'John', 1002);

INSERT INTO customer (id, lname, fname, bill_addr)
VALUES (2003, 'Smith', 'Joe', 1001);

INSERT INTO customer (id, lname, fname, bill_addr)
VALUES (2004, 'Jones', 'Beth', 1003);

INSERT INTO customer (id, lname, fname, bill_addr)
VALUES (2005, 'Andrews', 'Erica', 1004);

SELECT * FROM customer;

SELECT * FROM address;

SELECT c.*, a.* 
FROM customer c, address a
WHERE c.bill_addr = a.id;


INSERT INTO id_list (id, name, last_id)
VALUES (1, 'id_list', 20);

INSERT INTO id_list (id, name, last_id)
VALUES (10, 'address', 7000);

INSERT INTO id_list (id, name, last_id)
VALUES (11, 'customer', 8000);

