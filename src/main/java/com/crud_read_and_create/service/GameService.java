package com.crud_read_and_create.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.crud_read_and_create.controller.view.GameView;
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

	public void getGames(GameForm gameForm, Model model) {

		if (StringUtils.isEmpty(gameForm.getId())) {
			if (gameForm.getOrder().equals("asc")) {
				List<Game> gameListAsc = gameMapper.findAllAsc();
				List<GameView> gameViewAsc = gameListAsc.stream().map(GameView::new).collect(Collectors.toList());
				model.addAttribute("gameList", gameViewAsc);
			} else if (gameForm.getOrder().equals("desc")) {
				List<Game> gameListDesc = gameMapper.findAllDesc();
				List<GameView> gameViewDesc = gameListDesc.stream().map(GameView::new).collect(Collectors.toList());
				model.addAttribute("gameList", gameViewDesc);
			}
		} else {
			if (NumberUtils.isNumber(gameForm.getId())) {
				List<Game> gameId = gameMapper.findById(gameForm.getId());
				if (gameId.size() == 0) {
					model.addAttribute("notFound", "レコードは存在しませんでした。");
				} else {
					List<GameView> gameViewId = gameId.stream().map(GameView::new).collect(Collectors.toList());
					model.addAttribute("gameList", gameViewId);
				}
			} else {
				model.addAttribute("mojiretsu", "数字を入力して下さい。");
			}
		}
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

	public int createPlatform(PlatformForm platformForm) {
		return gameMapper.createPlatform(platformForm);
	}
}
