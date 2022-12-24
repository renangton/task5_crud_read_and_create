//package com.crud.mapper;
//
//import com.crud.entity.Game;
//import com.crud.entity.GamePlatform;
//import com.crud.entity.Platform;
//import com.crud.service.OrderBy;
//import com.github.database.rider.core.api.dataset.DataSet;
//import com.github.database.rider.core.api.dataset.ExpectedDataSet;
//import com.github.database.rider.spring.api.DBRider;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//import static org.assertj.core.api.Assertions.assertThat;
//import org.junit.jupiter.api.Test;
//import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//
//@DBRider
//@MybatisTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//class GameMapperTest {
//
//  @Autowired
//  GameMapper gameMapper;
//
//  @Test
//  @DataSet(value = "common/games.yml")
//  void 昇順が選択されている時_ゲームを昇順で全件取得できること() {
//    List<Platform> platformList1 = Arrays.asList(new Platform(1, "PS5"));
//    List<Platform> platformList2 = Arrays.asList(new Platform(1, "PS5"), new Platform(2, "Steam"));
//    List<Game> expectedGameList = Arrays.asList(new Game(1, "ELDENRING", "ARPG", 9000, platformList1),
//        new Game(2, "Apex", "FPS", 0, platformList2));
//    List<Game> games = gameMapper.findAll(OrderBy.from("asc"));
//    assertThat(games).hasSize(2).isEqualTo(expectedGameList);
//  }
//
//  @Test
//  @DataSet(value = "common/games.yml")
//  void 降順が選択されている時_ゲームを降順で全件取得できること() {
//    List<Platform> platformList1 = Arrays.asList(new Platform(1, "PS5"));
//    List<Platform> platformList2 = Arrays.asList(new Platform(1, "PS5"), new Platform(2, "Steam"));
//    List<Game> expectedGameList = Arrays.asList(new Game(2, "Apex", "FPS", 0, platformList2),
//        new Game(1, "ELDENRING", "ARPG", 9000, platformList1));
//    List<Game> games = gameMapper.findAll(OrderBy.from("desc"));
//    assertThat(games).hasSize(2).isEqualTo(expectedGameList);
//  }
//
//  @Test
//  @DataSet(value = "common/games.yml")
//  void 引数のidに対応したゲームを取得できること() {
//    List<Platform> platformList = Arrays.asList(new Platform(1, "PS5"));
//    Optional<Game> game = gameMapper.findById(1);
//    assertThat(game).contains(new Game(1, "ELDENRING", "ARPG", 9000, platformList));
//  }
//
//  @Test
//  @DataSet(value = "common/games.yml")
//  void 引数のidに対応したゲームが存在しない時_空のOptionalを取得すること() {
//    Optional<Game> emptyGame = gameMapper.findById(3);
//    assertThat(emptyGame).isEqualTo(Optional.empty());
//  }
//
//  @Test
//  @DataSet(value = "common/empty.yml")
//  @ExpectedDataSet(value = "insert/expectedAfterInsertGames.yml", ignoreCols = "id")
//  void ゲームを登録できること() {
//    gameMapper.createGame(new Game(1, "ELDENRING", "ARPG", 9000));
//  }
//
//  @Test
//  @DataSet(value = "common/gamesPlatformsEmpty.yml")
//  @ExpectedDataSet(value = "insert/expectedAfterInsertGamesPlatforms.yml", ignoreCols = "id")
//  void 中間テーブルを1件登録できること() {
//    List<GamePlatform> gamePlatformList = Arrays.asList(new GamePlatform(1, "1"));
//    gameMapper.createGamePlatform(gamePlatformList);
//  }
//
//  @Test
//  @DataSet(value = "common/gamesPlatformsEmpty.yml")
//  @ExpectedDataSet(value = "insert/expectedAfterInsertGamesPlatformsMulti.yml", ignoreCols = "id")
//  void gamePlatformListが複数件の時_中間テーブルを複数件登録できること() {
//    List<GamePlatform> gamePlatformList = Arrays.asList(new GamePlatform(1, "1"), new GamePlatform(1, "2"));
//    gameMapper.createGamePlatform(gamePlatformList);
//  }
//
//  @Test
//  @DataSet(value = "common/games.yml")
//  @ExpectedDataSet(value = "update/expectedAfterUpdateGames.yml")
//  void ゲームを更新できること() {
//    gameMapper.updateGame(new Game(1, "Pokemon", "RPG", 8000));
//  }
//
//  @Test
//  @DataSet(value = "common/games.yml")
//  @ExpectedDataSet(value = "delete/expectedAfterDeleteGames.yml")
//  void ゲームとゲームのIDに紐づく中間テーブルを削除できること() {
//    gameMapper.deleteGameAndGamePlatform(1);
//  }
//
//  @Test
//  @DataSet(value = "common/gameOnly.yml")
//  @ExpectedDataSet(value = "delete/expectedAfterDeleteGameOnly.yml")
//  void ゲームと紐づく中間テーブルがない時_ゲームを削除できること() {
//    gameMapper.deleteGame(2);
//  }
//
//  @Test
//  @DataSet(value = "common/gameOnly.yml")
//  @ExpectedDataSet(value = "common/gameOnly.yml")
//  void 存在しないゲームのIDでゲームを削除しようとする時_ゲームが削除されないこと() {
//    gameMapper.deleteGame(3);
//  }
//
//  @Test
//  @DataSet(value = "common/games.yml")
//  @ExpectedDataSet(value = "delete/expectedAfterDeleteGamesPlatformsGameId.yml")
//  void ゲームのIDが一致する中間テーブルを削除できること() {
//    gameMapper.deleteGamePlatformGameId(2);
//  }
//
//  @Test
//  @DataSet(value = "common/games.yml")
//  @ExpectedDataSet(value = "common/games.yml")
//  void 存在しないゲームのIDで中間テーブルを削除しようとする時_中間テーブルが削除されないこと() {
//    gameMapper.deleteGamePlatformGameId(3);
//  }
//}
