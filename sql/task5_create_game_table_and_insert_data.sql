DROP TABLE IF EXISTS games;

CREATE TABLE games
(
	id int NOT NULL,
	name VARCHAR(20) NOT NULL,
	genre VARCHAR(20) NOT NULL,
	price int(10) NOT NULL,

	PRIMARY KEY(id)
);

INSERT INTO games (id, name, genre, price) VALUES (1, "R6E", "FPS", 6600);
INSERT INTO games (id, name, genre, price) VALUES (2, "Apex", "FPS", 0);
INSERT INTO games (id, name, genre, price) VALUES (3, "Tarcov", "FPS", 14500);
INSERT INTO games (id, name, genre, price) VALUES (4, "WITCHER3", "ARPG", 6300);
INSERT INTO games (id, name, genre, price) VALUES (5, "Spider-Man MM", "OWAA", 6500);
INSERT INTO games (id, name, genre, price) VALUES (6, "BIOHAZARD8", "Horror", 7200);
INSERT INTO games (id, name, genre, price) VALUES (7, "POKEMON", "RPG", 6600);
INSERT INTO games (id, name, genre, price) VALUES (8, "Bomberman", "Action", 1200);
INSERT INTO games (id, name, genre, price) VALUES (9, "DARKSOULS", "ARPG", 4200);
INSERT INTO games (id, name, genre, price) VALUES (10, "Among Us", "Party", 0);

DROP TABLE IF EXISTS platforms;

CREATE TABLE platforms
(
	id int NOT NULL,
	platform VARCHAR(20) NOT NULL,
	
	PRIMARY KEY(id)
);

INSERT INTO platforms (id, platform) VALUES (1, "PS4");
INSERT INTO platforms (id, platform) VALUES (2, "PS5");
INSERT INTO platforms (id, platform) VALUES (3, "SWITCH");
INSERT INTO platforms (id, platform) VALUES (4, "Steam");
INSERT INTO platforms (id, platform) VALUES (5, "Origin");
INSERT INTO platforms (id, platform) VALUES (6, "UBI");
INSERT INTO platforms (id, platform) VALUES (7, "iOS");
INSERT INTO platforms (id, platform) VALUES (8, "PC");

DROP TABLE IF EXISTS games_platforms;

CREATE TABLE games_platforms
(
	id int unsigned AUTO_INCREMENT,
	games_id int,
	platforms_id int,
	
	PRIMARY KEY(id),
	FOREIGN KEY(games_id) REFERENCES games(id),
	FOREIGN KEY(platforms_id) REFERENCES platforms(id)
);

INSERT INTO games_platforms (games_id, platforms_id) VALUES (1, 6);
INSERT INTO games_platforms (games_id, platforms_id) VALUES (2, 4);
INSERT INTO games_platforms (games_id, platforms_id) VALUES (3, 8);
INSERT INTO games_platforms (games_id, platforms_id) VALUES (4, 5);
INSERT INTO games_platforms (games_id, platforms_id) VALUES (5, 2);
INSERT INTO games_platforms (games_id, platforms_id) VALUES (6, 2);
INSERT INTO games_platforms (games_id, platforms_id) VALUES (7, 3);
INSERT INTO games_platforms (games_id, platforms_id) VALUES (8, 3);
INSERT INTO games_platforms (games_id, platforms_id) VALUES (9, 1);
INSERT INTO games_platforms (games_id, platforms_id) VALUES (10, 7);