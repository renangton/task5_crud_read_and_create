DROP TABLE IF EXISTS games;

CREATE TABLE games
(
	id int unsigned AUTO_INCREMENT,
	name VARCHAR(100) NOT NULL,
	genru VARCHAR(100) NOT NULL,
	platform VARCHAR(100) NOT NULL,
	price INT(10) NOT NULL,
	
	PRIMARY KEY(id)
);

INSERT INTO games (name, genru, platform, price) VALUES ("R6E", "FPS", "UBI", 6600);
INSERT INTO games (name, genru, platform, price) VALUES ("Apex", "FPS", "Steam", 0);
INSERT INTO games (name, genru, platform, price) VALUES ("Tarcov", "FPS", "PC", 14500);
INSERT INTO games (name, genru, platform, price) VALUES ("WITCHER3", "ARPG", "Origin", 6300);
INSERT INTO games (name, genru, platform, price) VALUES ("Spider-Man MM", "OWAA", "PS5", 6500);
INSERT INTO games (name, genru, platform, price) VALUES ("BIOHAZARD8", "Horror", "PS5", 7200);
INSERT INTO games (name, genru, platform, price) VALUES ("POKEMON", "RPG", "SWITCH", 6600);
INSERT INTO games (name, genru, platform, price) VALUES ("Bomberman", "Action", "SWITCH", 1200);
INSERT INTO games (name, genru, platform, price) VALUES ("DARKSOULS", "ARPG", "PS4", 4200);
INSERT INTO games (name, genru, platform, price) VALUES ("Among Us", "Party", "iOS", 0);