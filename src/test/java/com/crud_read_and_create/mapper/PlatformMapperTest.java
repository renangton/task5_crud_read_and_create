package com.crud_read_and_create.mapper;

import com.crud_read_and_create.entity.Platform;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.github.database.rider.spring.api.DBRider;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DBRider
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PlatformMapperTest {

    @Autowired
    PlatformMapper platformMapper;

    @Test
    @DataSet(value = "common/games.yml")
    void プラットフォームを全件取得できること() {
        List<Platform> platformList = platformMapper.findPlatform();
        assertThat(platformList).hasSize(2).contains(new Platform(1, "PS5"), new Platform(2, "Steam"));
    }

    @Test
    @DataSet(value = "common/empty.yml")
    @ExpectedDataSet(value = "insert/expectedAfterInsertPlatforms.yml", ignoreCols = "id")
    void プラットフォームを登録できること() {
        platformMapper.createPlatform(new Platform(1, "PS5"));
    }

    @Test
    @DataSet(value = "common/games.yml")
    @ExpectedDataSet(value = "update/expectedAfterUpdatePlatforms.yml")
    void プラットフォームを更新できること() {
        platformMapper.updatePlatform(new Platform(1, "PS4"));
    }

    @Test
    @DataSet(value = "common/games.yml")
    @ExpectedDataSet(value = "delete/expectedAfterDeleteGamesPlatformsPlatformId.yml")
    void プラットフォームのIDが一致する中間テーブルを削除できること() {
        platformMapper.deleteGamePlatformPlatformId(2);
    }

    @Test
    @DataSet(value = "common/games.yml")
    @ExpectedDataSet(value = "delete/expectedAfterDeleteGamesPlatformsPlatformId.yml")
    void 存在しないプラットフォームのIDで中間テーブルを削除しようとした時_中間テーブルが削除されないこと() {
        platformMapper.deleteGamePlatformPlatformId(2);
    }

    @Test
    @DataSet(value = "common/games.yml")
    @ExpectedDataSet(value = "delete/expectedAfterDeletePlatforms.yml")
    void プラットフォームとプラットフォームのIDに紐づく中間テーブルを削除できること() {
        platformMapper.deletePlatformAndGamePlatform(2);
    }

    @Test
    @DataSet(value = "common/platformOnly.yml")
    @ExpectedDataSet(value = "delete/expectedAfterDeletePlatformOnly.yml")
    void プラットフォームと紐づく中間テーブルがない時_プラットフォームを削除できること() {
        platformMapper.deletePlatform(2);
    }

    @Test
    @DataSet(value = "common/platformOnly.yml")
    @ExpectedDataSet(value = "common/platformOnly.yml")
    void 存在しないプラットフォームのIDでプラットフォームを削除しようとした時_プラットフォームが削除されないこと() {
        platformMapper.deletePlatform(3);
    }
}
