package com.crud.service;

import com.crud.controller.view.GameView;
import com.crud.entity.Game;
import com.crud.entity.GamePlatform;
import com.crud.entity.Platform;
import com.crud.mapper.GameMapper;
import com.crud.service.exception.NotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import static org.mockito.Mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class GameServiceTest {

  @MockBean(name = "gameMapper")
  private GameMapper gameMapper;

  @Autowired
  private GameService gameService;

  @Captor
  ArgumentCaptor<Game> gameCapture;

  @Captor
  ArgumentCaptor<List<GamePlatform>> gamePlatformCapture;

  @Captor
  ArgumentCaptor<Integer> idCapture;

  @Test
  void idがnullの時_ゲームを全件取得できること() throws NotFoundException {
    List<Platform> platformList = Arrays.asList(new Platform(1, "PS5"), new Platform(2, "Switch"));
    List<Game> gameList = Arrays.asList(new Game(1, "R6E", "FPS", 6600, platformList),
        new Game(2, "BIOHAZARD8", "Horror", 7200, platformList),
        new Game(3, "DARKSOULS", "ARPG", 4200, platformList));
    doReturn(gameList).when(gameMapper).findAll(OrderBy.from("ASC"));
    List<GameView> gameViewList = gameList.stream().map(GameView::new).collect(Collectors.toList());

    List<GameView> actualGameList = gameService.getGames(null, "asc");
    assertThat(actualGameList).hasSize(3).isEqualTo(gameViewList);
  }

  @Test
  void idに数字が入力されていてレコードが存在しない時_NotFoundExceptionが発生すること() {
    doReturn(Optional.empty()).when(gameMapper).findById(1);
    assertThrows(NotFoundException.class, () -> gameService.getGames(1, "asc"));
  }

  @Test
  void idに数字が入力されていてレコードが存在する時_対象のゲームを取得できること() throws NotFoundException {
    List<Platform> platformList = Arrays.asList(new Platform(1, "PS5"), new Platform(2, "Switch"));
    Optional<Game> gameId = Optional.of(new Game(1, "R6E", "FPS", 6600, platformList));
    doReturn(gameId).when(gameMapper).findById(1);
    List<GameView> gameViewList = gameId.stream().map(GameView::new).collect(Collectors.toList());

    List<GameView> actualGameList = gameService.getGames(1, "asc");
    assertThat(actualGameList).isEqualTo(gameViewList);
  }

  @Test
  void idに紐づくレコードが存在する時_対象のゲームを取得できること() throws NotFoundException {
    List<Platform> platformList = Arrays.asList(new Platform(1, "PS5"), new Platform(2, "Switch"));
    Optional<Game> game = Optional.of(new Game(1, "R6E", "FPS", 6600, platformList));
    doReturn(game).when(gameMapper).findById(1);

    Game actualGame = gameService.getGameByid(1);
    assertThat(actualGame).isEqualTo(game.get());
  }

  @Test
  void idに紐づくレコードが存在しない時_NotFoundExceptionが発生すること() {
    doReturn(Optional.empty()).when(gameMapper).findById(1);
    assertThrows(NotFoundException.class, () -> gameService.getGameByid(1));
  }

  @Test
  void プラットフォームが1件のみ選択されている時_引数にしていたGameとそれに紐づくGameとPlatformの中間テーブルに正常に登録処理が実行されること() {
    Game game = new Game(1, "ELDENRING", "ARPG", 9000);
    String[] platformIds = {"1"};
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
  void プラットフォームが複数件選択されている時_引数にしていたGameとそれに紐づくGameとPlatformの中間テーブルに正常に登録処理が実行されること() {
    Game game = new Game(1, "ELDENRING", "ARPG", 9000);
    String[] platformIds = {"1", "2"};
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
  void ゲームを更新できること() {
    Game game = new Game(1, "ELDENRING", "ARPG", 9000);
    String[] platformIds = {"1", "2"};
    List<GamePlatform> gamePlatformList = Arrays.asList(new GamePlatform(game.getId(), platformIds[0]),
        new GamePlatform(game.getId(), platformIds[1]));

    gameService.updateGame(game.getId(), game.getName(), game.getGenre(), game.getPrice(), platformIds);
    verify(gameMapper, times(1)).updateGame(gameCapture.capture());
    verify(gameMapper, times(1)).deleteGamePlatformGameId(idCapture.capture());
    verify(gameMapper, times(1)).createGamePlatform(gamePlatformCapture.capture());
    Game actualGame = gameCapture.getValue();
    Integer actualId = idCapture.getValue();
    List<GamePlatform> actualGamePlatformList = gamePlatformCapture.getValue();
    assertThat(actualGame).isEqualTo(game);
    assertThat(actualId).isEqualTo(game.getId());
    assertThat(actualGamePlatformList).isEqualTo(gamePlatformList);
  }

  @Test
  void ゲームとゲームのIDに紐づく中間テーブルを削除できること() {
    Integer id = 1;

    gameService.deleteGame(id);
    verify(gameMapper, times(1)).deleteGamePlatformGameId(idCapture.capture());
    verify(gameMapper, times(1)).deleteGame(idCapture.capture());
    Integer actualId = idCapture.getValue();
    assertThat(actualId).isEqualTo(id);
  }
}
