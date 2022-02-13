package com.crud_read_and_create.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud_read_and_create.entity.Game;
import com.crud_read_and_create.mapper.GameMapper;

@Service
public class GameService {

	@Autowired
	GameMapper gameMapper;

	public List<Game> getGamesAsc() {

		return gameMapper.findAllAsc();

	}

	public List<Game> getGamesDesc() {

		return gameMapper.findAllDesc();

	}

	public Game findById(String id) {
		Game gameId = new Game();
		gameId.setId(id);
		return gameMapper.findById(gameId);
	}

	public int create(Game game) {
		return gameMapper.create(game);
	}
}
