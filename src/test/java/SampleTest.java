import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SampleTest {

    @Test
    void 失敗するサンプルテスト() {
        assertThat(1).isEqualTo(2);
    }
}
