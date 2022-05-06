package com.crud_read_and_create.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.crud_read_and_create.controller.view.GameView;
import com.crud_read_and_create.entity.Game;
import com.crud_read_and_create.entity.GamePlatform;
import com.crud_read_and_create.entity.Platform;
import com.crud_read_and_create.mapper.GameMapper;
import com.crud_read_and_create.service.exception.DuplicateException;
import com.crud_read_and_create.service.exception.NotFoundException;

@SpringBootTest
class GameServiceTest {

	@MockBean(name = "gameMapper")
	private GameMapper gameMapper;

	@Autowired
	private GameService gameService;

	@Test
	void idがnullの時_ゲームを全件取得できること() throws NotFoundException {
		List<Platform> platforms = Arrays.asList(new Platform("1", "PS5"), new Platform("2", "Switch"));
		List<Game> gameList = Arrays.asList(new Game(1, "R6E", "FPS", 6600, platforms),
				new Game(2, "BIOHAZARD8", "Horror", 7200, platforms),
				new Game(3, "DARKSOULS", "ARPG", 4200, platforms));
		doReturn(gameList).when(gameMapper).findAll(OrderBy.from("ASC"));
		List<GameView> gameView = gameList.stream().map(GameView::new).collect(Collectors.toList());

		List<GameView> actualGames = gameService.getGames(null, "asc");
		assertThat(actualGames).hasSize(3).isEqualTo(gameView);
	}

	@Test
	void idに数字が入力されていてレコードが存在しない時_NotFoundExceptionが発生すること() throws NotFoundException {
		Optional<Game> emptyGame = Optional.empty();
		doReturn(emptyGame).when(gameMapper).findById(1);
		assertThrows(NotFoundException.class, () -> gameService.getGames(1, "asc"));
	}

	@Test
	void idに数字が入力されていてレコードが存在する時_対象のゲームを取得できること() throws NotFoundException {
		List<Platform> platforms = Arrays.asList(new Platform("1", "PS5"), new Platform("2", "Switch"));
		Optional<Game> gameId = Optional.of(new Game(1, "R6E", "FPS", 6600, platforms));
		doReturn(gameId).when(gameMapper).findById(1);
		List<GameView> gameView = gameId.stream().map(GameView::new).collect(Collectors.toList());

		List<GameView> actualGame = gameService.getGames(1, "asc");
		assertThat(actualGame).isEqualTo(gameView);
	}

	@Test
	void プラットフォームを全件取得できること() {
		List<Platform> platforms = Arrays.asList(new Platform("1", "PS5"), new Platform("2", "Switch"));
		doReturn(platforms).when(gameMapper).findPlatform();

		List<Platform> actualPlatforms = gameService.getPlatform();
		assertThat(actualPlatforms).hasSize(2).isEqualTo(platforms);
	}

	@Captor
	ArgumentCaptor<Game> gameCapture;

	@Captor
	ArgumentCaptor<List<GamePlatform>> gamePlatformCapture;

	@Test
	void プラットフォームが１件のみ選択されている時_各引数が設定されたgameとgameplatformList１件が返却されること() {
		Game game = new Game(1, "ELDENRING", "ARPG", 9000);
		String[] platformIds = { "1" };
		List<GamePlatform> gamePlatformList = Arrays.asList(new GamePlatform(game.getId(), platformIds[0]));

		gameService.createGame(game.getId(), game.getName(), game.getGenre(), game.getPrice(), platformIds);
		verify(gameMapper, times(1)).createGame(gameCapture.capture());
		verify(gameMapper, times(1)).createGamePlatform(gamePlatformCapture.capture());
		Game actualGame = gameCapture.getValue();
		List<GamePlatform> actualGamePlatformList = gamePlatformCapture.getValue();
		assertThat(actualGame).isEqualTo(game);
		assertThat(actualGamePlatformList).isEqualTo(gamePlatformList);
	}

	@Test
	void プラットフォームが複数件選択されている時_各引数が設定されたgameとgameplatformList複数件が返却されること() {
		Game game = new Game(1, "ELDENRING", "ARPG", 9000);
		String[] platformIds = { "1", "2" };
		List<GamePlatform> gamePlatformList = Arrays.asList(new GamePlatform(game.getId(), platformIds[0]),
				new GamePlatform(game.getId(), platformIds[1]));
		gameService.createGame(game.getId(), game.getName(), game.getGenre(), game.getPrice(), platformIds);
		verify(gameMapper, times(1)).createGame(gameCapture.capture());
		verify(gameMapper, times(1)).createGamePlatform(gamePlatformCapture.capture());
		Game actualGame = gameCapture.getValue();
		List<GamePlatform> actualGamePlatformList = gamePlatformCapture.getValue();
		assertThat(actualGame).isEqualTo(game);
		assertThat(actualGamePlatformList).isEqualTo(gamePlatformList);
	}

	@Test
	void 入力したプラットフォームがすでに登録されている時_DuplicateExceptionが発生すること() throws DuplicateException {
		List<Platform> platforms = Arrays.asList(new Platform("1", "PS5"), new Platform("2", "Switch"));
		doReturn(platforms).when(gameMapper).findPlatform();
		assertThrows(DuplicateException.class, () -> gameService.createPlatform("1", "PS5"));
	}

	@Test
	void 入力したプラットフォームが未登録の時_platformが設定されたplatformListが返却されること() throws DuplicateException {
		ArgumentCaptor<String> idCapture = ArgumentCaptor.forClass(String.class);
		ArgumentCaptor<String> platformCapture = ArgumentCaptor.forClass(String.class);
		List<Platform> platforms = Arrays.asList(new Platform("1", "PS5"), new Platform("2", "Switch"));
		doReturn(platforms).when(gameMapper).findPlatform();
		String id = "3";
		String platform = "Steam";
		gameService.createPlatform(id, platform);
		verify(gameMapper, times(1)).createPlatform(idCapture.capture(), platformCapture.capture());
		String actualId = idCapture.getValue();
		String actualPlatform = platformCapture.getValue();
		assertThat(actualId).isEqualTo(id);
		assertThat(actualPlatform).isEqualTo(platform);
	}
}
