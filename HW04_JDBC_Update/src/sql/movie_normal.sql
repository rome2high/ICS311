DROP VIEW v_movie;
DROP TABLE id_list;
DROP TABLE movie;
DROP TABLE director;
DROP TABLE rating;

CREATE TABLE rating (
	id INTEGER PRIMARY KEY,
	name CHAR(5),
	description VARCHAR(255)
);

CREATE TABLE director (
	id INTEGER PRIMARY KEY,
	fname VARCHAR(25),
	lname VARCHAR(30)
);

CREATE TABLE movie (
	id INTEGER PRIMARY KEY,
	name VARCHAR(255),
	director_id INTEGER,
	rating_id INTEGER,
	runtime INTEGER,
	FOREIGN KEY (director_id) REFERENCES director(id),
	FOREIGN KEY (rating_id) REFERENCES rating(id)
);

CREATE TABLE id_list (
	id INTEGER PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    last_id INTEGER NOT NULL,
    UNIQUE (name)
);

CREATE VIEW v_movie AS 
  SELECT m.name, d.fname AS director_fname, d.lname AS director_lname, 
    	 r.name AS rating, m.runtime
    FROM movie m, director d, rating r
   WHERE m.director_id = d.id
     AND m.rating_id = r.id;

INSERT INTO rating
(id, name, description)
VALUES (1, 'G', 'All audiences');

INSERT INTO rating
(id, name, description)
VALUES (2, 'PG', 'Parental guidance suggested');

INSERT INTO rating
(id, name, description)
VALUES (3, 'PG-13', 
	'Parental guidance suggested, no one under 13 admitted without parent');

INSERT INTO rating
(id, name, description)
VALUES (4, 'R', 'Restricted, no one under 18 admitted without parent');

INSERT INTO director
(id, fname, lname)
VALUES (1, 'George', 'Lucas');

INSERT INTO director
(id, fname, lname)
VALUES (2, 'James', 'Cameron');

INSERT INTO director
(id, fname, lname)
VALUES (3, 'Stanley', 'Kubrick');

INSERT INTO director
(id, fname, lname)
VALUES (4, 'John', 'Hughes');

INSERT INTO movie
(id, name, director_id, rating_id, runtime) 
VALUES (1, 'Star Wars', 1, 2, 121);

INSERT INTO movie
(id, name, director_id, rating_id, runtime) 
VALUES (2, '2001: A Space Odyssey', 3, 1, 139);

INSERT INTO movie
(id, name, director_id, rating_id, runtime) 
VALUES (3, 'Aliens', 2, 4, 137);

INSERT INTO movie
(id, name, director_id, rating_id, runtime) 
VALUES (4, 'Planes, Trains, & Automobiles', 4, 4, 93);

INSERT INTO movie
(id, name, director_id, rating_id, runtime) 
VALUES (5, 'Full Metal Jacket', 3, 4, 116);

INSERT INTO movie
(id, name, director_id, rating_id, runtime) 
VALUES (6, 'Titanic', 2, 3, 194);

INSERT INTO movie
(id, name, director_id, rating_id, runtime) 
VALUES (7, 'The Shining', 3, 4, 119);

INSERT INTO movie
(id, name, director_id, rating_id, runtime) 
VALUES (8, 'She''s Having a Baby', 4, 3, 106);

INSERT INTO movie
(id, name, director_id, rating_id, runtime) 
VALUES (9, 'Ferris Bueller''s Day Off', 4, 3, 102);

SELECT *
FROM movie;

SELECT *
FROM rating;

SELECT * 
FROM director;

SELECT m.id, m.name, d.fname, d.lname, r.name, m.runtime
FROM movie m, director d, rating r
WHERE m.director_id = d.id 
AND m.rating_id = r.id;

INSERT INTO id_list (id, name, last_id) VALUES (0, 'id_list', 10);
INSERT INTO id_list (id, name, last_id) VALUES (1, 'movie', 200);
INSERT INTO id_list (id, name, last_id) VALUES (2, 'director', 300);
INSERT INTO id_list (id, name, last_id) VALUES (3, 'rating', 400);

SELECT *
FROM id_list;
