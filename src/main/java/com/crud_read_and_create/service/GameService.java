package com.crud_read_and_create.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crud_read_and_create.entity.Game;
import com.crud_read_and_create.entity.Platform;
import com.crud_read_and_create.form.GameForm;
import com.crud_read_and_create.form.GamePlatformForm;
import com.crud_read_and_create.form.PlatformForm;
import com.crud_read_and_create.mapper.GameMapper;

@Service
public class GameService {
	private final GameMapper gameMapper;

	public GameService(GameMapper gameMapper) {
		this.gameMapper = gameMapper;
	}

	public List<Game> getGamesAsc() {
		return gameMapper.findAllAsc();
	}

	public List<Game> getGamesDesc() {
		return gameMapper.findAllDesc();
	}

	public List<Game> getGamesId(String id) {
		Game gameId = new Game();
		gameId.setId(id);
		return gameMapper.findById(gameId);
	}

	public List<Platform> getPlatform() {
		return gameMapper.findPlatform();
	}

	@Transactional
	public void createGame(GameForm gameForm, GamePlatformForm gamePlatformForm) {
		gameMapper.createGame(gameForm);
		gamePlatformForm.setGameId(gameForm.getId());
		String[] platforms = gameForm.getPlatformId();
		for (String value : platforms) {
			gamePlatformForm.setPlatformId(value);
			gameMapper.createGamePlatform(gamePlatformForm);
		}
	}

	public int createPlatform(PlatformForm platformForm) {
		return gameMapper.createPlatform(platformForm);
	}
}
