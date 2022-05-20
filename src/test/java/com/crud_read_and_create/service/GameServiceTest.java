package com.crud_read_and_create.service;

import com.crud_read_and_create.controller.view.GameView;
import com.crud_read_and_create.entity.Game;
import com.crud_read_and_create.entity.GamePlatform;
import com.crud_read_and_create.entity.Platform;
import com.crud_read_and_create.mapper.GameMapper;
import com.crud_read_and_create.service.exception.DuplicateException;
import com.crud_read_and_create.service.exception.NotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
class GameServiceTest {

    @MockBean(name = "gameMapper")
    private GameMapper gameMapper;

    @Autowired
    private GameService gameService;

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
        assertThatThrownBy(() -> {
            throw new NotFoundException("レコードは存在しませんでした。");
        })
                .hasMessage("レコードは存在しませんでした。");
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
    void プラットフォームを全件取得できること() {
        List<Platform> platformList = Arrays.asList(new Platform(1, "PS5"), new Platform(2, "Switch"));
        doReturn(platformList).when(gameMapper).findPlatform();

        List<Platform> actualPlatformList = gameService.getPlatform();
        assertThat(actualPlatformList).hasSize(2).isEqualTo(platformList);
    }

    @Captor
    ArgumentCaptor<Game> gameCapture;

    @Captor
    ArgumentCaptor<List<GamePlatform>> gamePlatformCapture;

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
    void 入力したプラットフォームがすでに登録されている時_DuplicateExceptionが発生すること() {
        List<Platform> platformList = Arrays.asList(new Platform(1, "PS5"), new Platform(2, "Switch"));
        doReturn(platformList).when(gameMapper).findPlatform();
        assertThrows(DuplicateException.class, () -> gameService.createPlatform(1, "PS5"));
        assertThatThrownBy(() -> {
            throw new DuplicateException("プラットフォームが重複しています。");
        })
                .hasMessage("プラットフォームが重複しています。");
    }

    @Captor
    ArgumentCaptor<Platform> platformCapture;

    @Test
    void 入力したプラットフォームが未登録の時_引数にしていたPlatformが正常に登録処理が実行されること() throws DuplicateException {
        List<Platform> platformList = Arrays.asList(new Platform(1, "PS5"), new Platform(2, "Switch"));
        doReturn(platformList).when(gameMapper).findPlatform();
        Platform platformData = new Platform(3, "Steam");

        gameService.createPlatform(platformData.getId(), platformData.getPlatform());
        verify(gameMapper, times(1)).createPlatform(platformCapture.capture());
        Platform actualPlatform = platformCapture.getValue();
        assertThat(actualPlatform).isEqualTo(platformData);
    }
}
