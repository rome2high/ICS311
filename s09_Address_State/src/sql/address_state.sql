
DROP VIEW v_address;
DROP TABLE address;
DROP TABLE state;

CREATE TABLE state (
    id INTEGER PRIMARY KEY,
    code CHAR(2) NOT NULL UNIQUE,
    name VARCHAR(30) NOT NULL
);

CREATE TABLE address (
    id INTEGER PRIMARY KEY,
    street VARCHAR(255),
    city VARCHAR(150),
    state_id INTEGER REFERENCES state(id),
    zip CHAR(5)
);


CREATE VIEW v_address AS
    SELECT a.id, a.street, a.city, s.code state_code, s.name state_name, a.zip
    FROM address a LEFT OUTER JOIN state s ON a.state_id = s.id;

INSERT INTO state (id, code, name)
VALUES (1001, 'MN', 'Minnesota');

INSERT INTO state (id, code, name)
VALUES (1002, 'MI', 'Michigan');

INSERT INTO state (id, code, name)
VALUES (1003, 'MS', 'Mississippi');

INSERT INTO state (id, code, name)
VALUES (1004, 'ND', 'North Dakota');

INSERT INTO address (id, street, city, state_id, zip)
VALUES (2001, '123 Main St', 'Oakwood', 1002, '09321');

INSERT INTO address (id, street, city)
VALUES (2002, '53 Maple Av', 'St. Paul');

INSERT INTO address (id, street, city, state_id, zip)
VALUES (2003, '91 River Rd', 'Minot', 1004, '56231');

INSERT INTO address (id, street, city, state_id, zip)
VALUES (2004, '55 Chestnut St', 'Bismarck', 1004, '56001');

SELECT *
FROM state
ORDER BY code;

SELECT *
FROM address
ORDER BY id;

SELECT *
FROM v_address;


