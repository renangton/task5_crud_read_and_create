package com.crud.service;

import com.crud.entity.Platform;
import com.crud.mapper.PlatformMapper;
import com.crud.service.exception.DuplicateException;
import java.util.Arrays;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertThrows;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import static org.mockito.Mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class PlatformServiceTest {

  @MockBean(name = "platformMapper")
  private PlatformMapper platformMapper;

  @Autowired
  private PlatformService platformService;

  @Test
  void プラットフォームを全件取得できること() {
    List<Platform> platformList = Arrays.asList(new Platform(1, "PS5"), new Platform(2, "Switch"));
    doReturn(platformList).when(platformMapper).findPlatform();

    List<Platform> actualPlatformList = platformService.getPlatform();
    assertThat(actualPlatformList).hasSize(2).isEqualTo(platformList);
  }

  @Test
  void 入力したプラットフォームがすでに登録されている時_DuplicateExceptionが発生すること() {
    List<Platform> platformList = Arrays.asList(new Platform(1, "PS5"), new Platform(2, "Switch"));
    doReturn(platformList).when(platformMapper).findPlatform();
    assertThrows(DuplicateException.class, () -> platformService.createPlatform(1, "PS5"));
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
    doReturn(platformList).when(platformMapper).findPlatform();
    Platform platformData = new Platform(3, "Steam");

    platformService.createPlatform(platformData.getId(), platformData.getPlatform());
    verify(platformMapper, times(1)).createPlatform(platformCapture.capture());
    Platform actualPlatform = platformCapture.getValue();
    assertThat(actualPlatform).isEqualTo(platformData);
  }

  @Test
  void プラットフォームを更新できること() throws DuplicateException {
    List<Platform> platformList = Arrays.asList(new Platform(1, "PS5"), new Platform(2, "Switch"));
    doReturn(platformList).when(platformMapper).findPlatform();
    Platform platformData = new Platform(1, "Steam");

    platformService.updatePlatform(platformData.getId(), platformData.getPlatform());
    verify(platformMapper, times(1)).updatePlatform(platformCapture.capture());
    Platform actualPlatform = platformCapture.getValue();
    assertThat(actualPlatform).isEqualTo(platformData);
  }

  @Test
  void 更新したプラットフォームがすでに登録されている時_DuplicateExceptionが発生すること() {
    List<Platform> platformList = Arrays.asList(new Platform(1, "PS5"), new Platform(2, "Switch"));
    doReturn(platformList).when(platformMapper).findPlatform();
    assertThrows(DuplicateException.class, () -> platformService.updatePlatform(1, "PS5"));
    assertThatThrownBy(() -> {
      throw new DuplicateException("プラットフォームが重複しています。");
    })
        .hasMessage("プラットフォームが重複しています。");
  }

  @Captor
  ArgumentCaptor<Integer> idCapture;

  @Test
  void プラットフォームのIDが一致する中間テーブルを削除できること() {
    Integer id = 1;

    platformService.deleteGamePlatformPlatformId(id);
    verify(platformMapper, times(1)).deleteGamePlatformPlatformId(idCapture.capture());
    Integer actualId = idCapture.getValue();
    assertThat(actualId).isEqualTo(id);
  }

  @Test
  void プラットフォームとプラットフォームのIDに紐づく中間テーブルを削除できること() {
    Integer id = 1;

    platformService.deletePlatformAndGamePlatform(id);
    verify(platformMapper, times(1)).deletePlatformAndGamePlatform(idCapture.capture());
    Integer actualId = idCapture.getValue();
    assertThat(actualId).isEqualTo(id);
  }

  @Test
  void プラットフォームと紐づく中間テーブルがない時_プラットフォームを削除できること() {
    Integer id = 1;

    platformService.deletePlatform(id);
    verify(platformMapper, times(1)).deletePlatform(idCapture.capture());
    Integer actualId = idCapture.getValue();
    assertThat(actualId).isEqualTo(id);
  }
}
