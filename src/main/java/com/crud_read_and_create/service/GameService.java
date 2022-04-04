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
import com.crud_read_and_create.entity.GamePlatform;
import com.crud_read_and_create.entity.Platform;
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

	public List<GameView> getGames(String Id, String order) throws NotFoundException, PatternIntException {

		List<GameView> gameView = new ArrayList<GameView>();
		if (StringUtils.isEmpty(Id)) {
			List<Game> gameList = gameMapper.findAll(OrderBy.from(order));
			gameView = gameList.stream().map(GameView::new).collect(Collectors.toList());
		} else {
			if (NumberUtils.isNumber(Id)) {
				List<Game> gameId = gameMapper.findById(Id);
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
	public void createGame(String id, String name, String genre, Integer price, String[] platformId) {
		// 登録するGameの各値を詰め込む（中間テーブルに登録するgameIdを取得するために使用）
		Game game = new Game();
		game.setId(id);
		game.setName(name);
		game.setGenre(genre);
		game.setPrice(price);
		gameMapper.createGame(game);
		List<GamePlatform> gamePlatformList = new ArrayList<GamePlatform>();
		for (String value : platformId) {
			GamePlatform gpList = new GamePlatform(game.getId(), value);
			gamePlatformList.add(gpList);
		}
		gameMapper.createGamePlatform(gamePlatformList);
	}

	public int createPlatform(String id, String platform) throws DuplicateException {
		List<Platform> platformList = gameMapper.findPlatform();
		for (int i = 0; i < platformList.size(); i++) {
			if (platform.equals(platformList.get(i).getPlatform())) {
				throw new DuplicateException("プラットフォームが重複しています。");
			}
		}
		return gameMapper.createPlatform(id, platform);
	}
}
