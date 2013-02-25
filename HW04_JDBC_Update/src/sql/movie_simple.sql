DROP TABLE movie;

CREATE TABLE movie (
	name VARCHAR(255),
	director VARCHAR(50),
	rating CHAR(5),
	runtime INTEGER
);

INSERT INTO movie
(name, director, rating, runtime) 
VALUES ('Star Wars', 'George Lucas', 'PG', 121);

INSERT INTO movie
(name, director, rating, runtime) 
VALUES ('2001: A Space Odyssey', 'Stanley Kubrick', 'G', 139);

INSERT INTO movie
(name, director, rating, runtime) 
VALUES ('Aliens', 'James Cameron', 'R', 137);

INSERT INTO movie
(name, director, rating, runtime) 
VALUES ('Planes, Trains, & Automobiles', 'John Hughes', 'R', 93);

INSERT INTO movie
(name, director, rating, runtime) 
VALUES ('Full Metal Jacket', 'Stanley Kubrick', 'R', 116);

INSERT INTO movie
(name, director, rating, runtime) 
VALUES ('Titanic', 'James Cameron', 'PG-13', 194);

INSERT INTO movie
(name, director, rating, runtime) 
VALUES ('The Shining', 'Stanley Kubrick', 'R', 119);

INSERT INTO movie
(name, director, rating, runtime) 
VALUES ('She''s Having a Baby', 'John Hughes', 'PG-13', 106);

INSERT INTO movie
(name, director, rating, runtime) 
VALUES ('Ferris Bueller''s Day Off', 'John Hughes', 'PG-13', 102);

SELECT *
FROM movie;
