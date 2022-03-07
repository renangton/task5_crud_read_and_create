package com.crud_read_and_create.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.crud_read_and_create.entity.Game;
import com.crud_read_and_create.mapper.GameMapper;

@SpringBootTest
class GameServiceTest {

	@Autowired
	private GameService gameService;

	@MockBean
	private GameMapper gameMapper;

	@Test
	void ゲームの全件が昇順で取得できること() {
		Game exampleGame = new Game();
		exampleGame.setName("DARKSOULS");
		Mockito.when(gameMapper.findAllAsc()).thenReturn(null);
		String actual = gameService.getGamesAsc().get(8).getName();
		assertThat(exampleGame.getName()).isEqualTo(actual);
	}
}