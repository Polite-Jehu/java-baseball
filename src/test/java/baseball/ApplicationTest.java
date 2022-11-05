package baseball;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static camp.nextstep.edu.missionutils.test.Assertions.*;
import static org.assertj.core.api.Assertions.*;

class ApplicationTest extends NsTest {
    final GameReadyMachine gameReadyMachine = new GameReadyMachine();
    final GameProcessor gameProcessor = new GameProcessor();
    @Test
    void 자리수_구분되는_숫자생성_1_3(){
        assertThat(gameReadyMachine.checkDigitValue(gameReadyMachine.setAnswerValue())).isEqualTo(true);
    }

    @Test
    void getThreeDigitNumber() {
        assertThat(gameReadyMachine.setValue()).isEqualTo(true);
    }

    @Test
    void 자리수다른지_체크_성공() {
        assertThat(gameReadyMachine.checkDigitValue(574)).isEqualTo(true);
        assertThat(gameReadyMachine.checkDigitValue(686)).isEqualTo(false);
    }

    @Test
    void 테스트1_4와1_5(){
        gameReadyMachine.gameReadyComplete();
        assertThat(gameReadyMachine.checkDigitValue(gameReadyMachine.getAnswerValue())).isEqualTo(true);
    }

    @Test
    void 테스트2_1_with_테스트2_6(){
        String testData = "234";
        InputStream in = new ByteArrayInputStream(testData.getBytes());
        System.setIn(in);
        gameProcessor.userInput();
        assertThat(gameProcessor.getLastInput()).isEqualTo("234");
    }



    @Test
    void 게임종료_후_재시작() {
        assertRandomNumberInRangeTest(
                () -> {
                    run("246", "135", "1", "597", "589", "2");
                    assertThat(output()).contains("낫싱", "3스트라이크", "1볼 1스트라이크", "3스트라이크", "게임 종료");
                },
                1, 3, 5, 5, 8, 9
        );
    }

    @Test
    void 예외_테스트() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("1234"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
