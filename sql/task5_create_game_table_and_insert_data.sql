DROP TABLE IF EXISTS games;

CREATE TABLE games
(
	id int unsigned AUTO_INCREMENT,
	name VARCHAR(20) NOT NULL,
	genre VARCHAR(20) NOT NULL,
	price int(10) NOT NULL,

	PRIMARY KEY(id)
);

INSERT INTO games (name, genre, price) VALUES ("R6E", "FPS", 6600);
INSERT INTO games (name, genre, price) VALUES ("Apex", "FPS", 0);
INSERT INTO games (name, genre, price) VALUES ("Tarcov", "FPS", 14500);
INSERT INTO games (name, genre, price) VALUES ("WITCHER3", "ARPG", 6300);
INSERT INTO games (name, genre, price) VALUES ("Spider-Man MM", "OWAA", 6500);
INSERT INTO games (name, genre, price) VALUES ("BIOHAZARD8", "Horror", 7200);
INSERT INTO games (name, genre, price) VALUES ("POKEMON", "RPG", 6600);
INSERT INTO games (name, genre, price) VALUES ("Bomberman", "Action", 1200);
INSERT INTO games (name, genre, price) VALUES ("DARKSOULS", "ARPG", 4200);
INSERT INTO games (name, genre, price) VALUES ("Among Us", "Party", 0);
INSERT INTO games (name, genre, price) VALUES ("MOTHER3", "RPG", 26000);

DROP TABLE IF EXISTS platforms;

CREATE TABLE platforms
(
	id int unsigned AUTO_INCREMENT,
	platform VARCHAR(20) NOT NULL,
	
	PRIMARY KEY(id)
);

INSERT INTO platforms (platform) VALUES ("PS4");
INSERT INTO platforms (platform) VALUES ("PS5");
INSERT INTO platforms (platform) VALUES ("Switch");
INSERT INTO platforms (platform) VALUES ("Steam");
INSERT INTO platforms (platform) VALUES ("Origin");
INSERT INTO platforms (platform) VALUES ("UBI");
INSERT INTO platforms (platform) VALUES ("iOS");
INSERT INTO platforms (platform) VALUES ("PC");
INSERT INTO platforms (platform) VALUES ("NEOGEO");

DROP TABLE IF EXISTS games_platforms;

CREATE TABLE games_platforms
(
	id int unsigned AUTO_INCREMENT,
	games_id int unsigned,
	platforms_id int unsigned,
	
	PRIMARY KEY(id),

	FOREIGN KEY(games_id) REFERENCES games(id),
	FOREIGN KEY(platforms_id) REFERENCES platforms(id)
);

INSERT INTO games_platforms (games_id, platforms_id) VALUES (1, 6);
INSERT INTO games_platforms (games_id, platforms_id) VALUES (2, 4);
INSERT INTO games_platforms (games_id, platforms_id) VALUES (3, 8);
INSERT INTO games_platforms (games_id, platforms_id) VALUES (4, 5);
INSERT INTO games_platforms (games_id, platforms_id) VALUES (4, 1);
INSERT INTO games_platforms (games_id, platforms_id) VALUES (4, 3);
INSERT INTO games_platforms (games_id, platforms_id) VALUES (4, 4);
INSERT INTO games_platforms (games_id, platforms_id) VALUES (5, 2);
INSERT INTO games_platforms (games_id, platforms_id) VALUES (6, 2);
INSERT INTO games_platforms (games_id, platforms_id) VALUES (7, 3);
INSERT INTO games_platforms (games_id, platforms_id) VALUES (8, 3);
INSERT INTO games_platforms (games_id, platforms_id) VALUES (9, 1);
INSERT INTO games_platforms (games_id, platforms_id) VALUES (10, 7);
