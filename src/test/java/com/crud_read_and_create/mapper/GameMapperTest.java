package com.crud_read_and_create.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import com.crud_read_and_create.entity.Game;
import com.crud_read_and_create.entity.GamePlatform;
import com.crud_read_and_create.entity.Platform;
import com.crud_read_and_create.service.OrderBy;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.github.database.rider.spring.api.DBRider;

@DBRider
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class GameMapperTest {

	@Autowired
	GameMapper gameMapper;

	@Test
	@DataSet(value = "games.yml")
	void ゲームを全件取得できること() {
		List<Platform> platformList1 = Arrays.asList(new Platform("1", "PS5"));
		List<Platform> platformList2 = Arrays.asList(new Platform("1", "PS5"), new Platform("2", "Steam"));
		List<Game> games = gameMapper.findAll(OrderBy.from("ASC"));
		assertThat(games).hasSize(2).contains(new Game(1, "ELDENRING", "ARPG", 9000, platformList1),
				new Game(2, "Apex", "FPS", 0, platformList2));
	}

	@Test
	@DataSet(value = "games.yml")
	void 引数のidに対応したゲームを取得できること() {
		List<Platform> platformList = Arrays.asList(new Platform("1", "PS5"));
		Optional<Game> game = gameMapper.findById(1);
		assertThat(game).contains(new Game(1, "ELDENRING", "ARPG", 9000, platformList));
	}

	@Test
	@DataSet(value = "games.yml")
	void プラットフォームを全件取得できること() {
		List<Platform> platformList = gameMapper.findPlatform();
		assertThat(platformList).hasSize(2).contains(new Platform("1", "PS5"), new Platform("2", "Steam"));
	}

	@Test
	@DataSet(value = "empty.yml")
	@ExpectedDataSet(value = "expectedAfterInsertGames.yml", ignoreCols = "id")
	void ゲームを登録できること() {
		gameMapper.createGame(new Game(1, "ELDENRING", "ARPG", 9000));
	}

	@Test
	@DataSet(value = "gamesPlatformsEmpty.yml")
	@ExpectedDataSet(value = "expectedAfterInsertGamesPlatforms.yml", ignoreCols = "id")
	void 中間テーブルを1件登録できること() {
		List<GamePlatform> gamePlatformList = Arrays.asList(new GamePlatform(1, "1"));
		gameMapper.createGamePlatform(gamePlatformList);
	}

	@Test
	@DataSet(value = "gamesPlatformsEmpty.yml")
	@ExpectedDataSet(value = "expectedAfterInsertGamesPlatformsMulti.yml", ignoreCols = "id")
	void gamePlatformListが複数件の時_中間テーブルを複数件登録できること() {
		List<GamePlatform> gamePlatformList = Arrays.asList(new GamePlatform(1, "1"), new GamePlatform(1, "2"));
		gameMapper.createGamePlatform(gamePlatformList);
	}

	@Test
	@DataSet(value = "empty.yml")
	@ExpectedDataSet(value = "expectedAfterInsertPlatforms.yml", ignoreCols = "id")
	void プラットフォームを登録できること() {
		gameMapper.createPlatform(null, "PS5");
	}
}
