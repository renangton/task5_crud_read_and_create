package com.crud_read_and_create.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.crud_read_and_create.entity.Game;
import com.crud_read_and_create.service.GameService;

@SpringBootTest
@Transactional
class GameControllerTest {

	@Autowired
	private GameService gameService;

	/*
	 * @Autowired private GameFormPlatform formPlatform;
	 * 
	 * @Autowired private Game game;
	 * 
	 * @Autowired private Platform platform;
	 * 
	 * 
	 * @BeforeEach void setUp() { gameService = new GameService(null); }
	 */

	@Test
	void asc全件検索() {
		/*
		 * GameForm exampleForm = new GameForm(); exampleForm.setId(null);
		 * exampleForm.setOrder("asc");
		 */

		Game exampleGame = new Game();
		exampleGame.setName("DARKSOULS");
		/*
		 * exampleGame.setGenre("PS4"); exampleGame.setGenre("ARPG");
		 * exampleGame.setPrice(4200);
		 */

		String actual = gameService.getGamesAsc().get(8).getName();

		assertThat(exampleGame.getName()).isEqualTo(actual);
	}
}