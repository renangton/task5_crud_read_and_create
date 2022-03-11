package com.crud_read_and_create.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.crud_read_and_create.entity.Game;
import com.crud_read_and_create.mapper.GameMapper;

@SpringBootTest
class GameServiceTest {

	@MockBean(name = "gameMapper")
	private GameMapper gameMapper;

	@Autowired
	private GameService gameService;

	@Test
	void ゲームの全件が昇順で取得できること() {
		Game exampleGame = new Game();
		exampleGame.setName("DARKSOULS");
		List<Game> findAsc = new ArrayList<Game>();
		findAsc.add(exampleGame);
		Mockito.when(gameMapper.findAllAsc()).thenReturn(findAsc);
		String actual = gameService.getGamesAsc().get(0).getName();
		assertThat(exampleGame.getName()).isEqualTo(actual);
	}

	@Test
	void ゲームの全件が降順で取得できること() {
		Game exampleGame = new Game();
		exampleGame.setName("Apex");
		List<Game> findDesc = new ArrayList<Game>();
		findDesc.add(exampleGame);
		Mockito.when(gameMapper.findAllDesc()).thenReturn(findDesc);
		String actual = gameService.getGamesDesc().get(0).getName();
		assertThat(exampleGame.getName()).isEqualTo(actual);
	}
}