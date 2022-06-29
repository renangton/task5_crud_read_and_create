package com.crud_read_and_create.service.exception;

import com.crud_read_and_create.entity.Platform;
import com.crud_read_and_create.mapper.PlatformMapper;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.*;

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
}
