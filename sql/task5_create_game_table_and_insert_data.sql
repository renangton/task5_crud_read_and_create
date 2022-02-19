DROP TABLE IF EXISTS games;

CREATE TABLE games
(
	id int unsigned AUTO_INCREMENT,
	name VARCHAR(20) NOT NULL,
	genre VARCHAR(20) NOT NULL,
	platform VARCHAR(20) NOT NULL,
	price int(10) NOT NULL,
	
	PRIMARY KEY(id)
);

INSERT INTO games (name, genre, platform, price) VALUES ("R6E", "FPS", "UBI", 6600);
INSERT INTO games (name, genre, platform, price) VALUES ("Apex", "FPS", "Steam", 0);
INSERT INTO games (name, genre, platform, price) VALUES ("Tarcov", "FPS", "PC", 14500);
INSERT INTO games (name, genre, platform, price) VALUES ("WITCHER3", "ARPG", "Origin", 6300);
INSERT INTO games (name, genre, platform, price) VALUES ("Spider-Man MM", "OWAA", "PS5", 6500);
INSERT INTO games (name, genre, platform, price) VALUES ("BIOHAZARD8", "Horror", "PS5", 7200);
INSERT INTO games (name, genre, platform, price) VALUES ("POKEMON", "RPG", "SWITCH", 6600);
INSERT INTO games (name, genre, platform, price) VALUES ("Bomberman", "Action", "SWITCH", 1200);
INSERT INTO games (name, genre, platform, price) VALUES ("DARKSOULS", "ARPG", "PS4", 4200);
INSERT INTO games (name, genre, platform, price) VALUES ("Among Us", "Party", "iOS", 0);