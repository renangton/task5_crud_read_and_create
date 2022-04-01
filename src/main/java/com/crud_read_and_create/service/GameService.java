package com.crud_read_and_create.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crud_read_and_create.controller.view.GameView;
import com.crud_read_and_create.entity.Game;
import com.crud_read_and_create.entity.Platform;
import com.crud_read_and_create.form.GameForm;
import com.crud_read_and_create.form.GamePlatformForm;
import com.crud_read_and_create.form.PlatformForm;
import com.crud_read_and_create.mapper.GameMapper;
import com.crud_read_and_create.service.exception.DuplicateException;
import com.crud_read_and_create.service.exception.NotFoundException;
import com.crud_read_and_create.service.exception.PatternIntException;

@Service
public class GameService {
	private final GameMapper gameMapper;

	public GameService(GameMapper gameMapper) {
		this.gameMapper = gameMapper;
	}

	public List<GameView> getGames(GameForm gameForm) throws NotFoundException, PatternIntException {

		List<GameView> gameView = new ArrayList<GameView>();
		if (StringUtils.isEmpty(gameForm.getId())) {
			OrderBy orderBy = null;
			if (gameForm.getOrder().equals("asc")) {
				orderBy = OrderBy.ASC;
			} else if (gameForm.getOrder().equals("desc")) {
				orderBy = OrderBy.DESC;
			}
			List<Game> gameList = gameMapper.findAll(orderBy);
			gameView = gameList.stream().map(GameView::new).collect(Collectors.toList());
		} else {
			if (NumberUtils.isNumber(gameForm.getId())) {
				List<Game> gameId = gameMapper.findById(gameForm.getId());
				if (gameId.size() == 0) {
					throw new NotFoundException("レコードは存在しませんでした。");
				} else {
					gameView = gameId.stream().map(GameView::new).collect(Collectors.toList());
				}
			} else {
				throw new PatternIntException("数字を入力して下さい。");
			}
		}
		return gameView;
	}

	public List<Platform> getPlatform() {
		return gameMapper.findPlatform();
	}

	@Transactional
	public void createGame(GameForm gameForm, GamePlatformForm gamePlatformForm) {
		gameMapper.createGame(gameForm);

		List<GamePlatformForm> gamePlatformList = new ArrayList<GamePlatformForm>();
		String[] platforms = gameForm.getPlatformId();
		for (String value : platforms) {
			GamePlatformForm gpList = new GamePlatformForm(gameForm.getId(), value);
			gamePlatformList.add(gpList);
		}
		gameMapper.createGamePlatform(gamePlatformList);
	}

	public int createPlatform(PlatformForm platformForm) throws DuplicateException {
		List<Platform> platformList = gameMapper.findPlatform();
		for (int i = 0; i < platformList.size(); i++) {
			if (platformForm.getPlatform().equals(platformList.get(i).getPlatform())) {
				throw new DuplicateException("プラットフォームが重複しています。");
			}
		}
		return gameMapper.createPlatform(platformForm);
	}
}
