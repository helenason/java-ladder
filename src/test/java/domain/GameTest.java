package domain;

import static org.assertj.core.api.Assertions.*;

import java.util.HashMap;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import strategy.RandomConnectionStrategy;

public class GameTest {

    @Test
    @DisplayName("게임 객체 생성 성공")
    void test_ok_createObject() {
        Members members = Members.from("a,b,c,d");

        Lines lines = Lines.of(4, 5, new RandomConnectionStrategy());

        assertThatCode(() -> new Game(members, lines))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("이동 성공: 사다리를 타고 참여자와 결과를 매칭한다.")
    void test_ok_matchResult() {

        Members members = Members.from("a,b,c,d");

        Lines lines = Lines.of(4, 5, new RandomConnectionStrategy() {
            @Override
            public Connection generateConnection() {
                return Connection.DISCONNECTED;
            }
        });

        Result aResult = new Result("a!");
        Result bResult = new Result("b!");
        Result cResult = new Result("c!");
        Result dResult = new Result("d!");
        Results results = new Results(List.of(
                aResult,
                bResult,
                cResult,
                dResult),
                4);

        Game game = new Game(members, lines);

        HashMap<String, Result> actual = game.matchResult(results);

        assertThat(actual.get("a")).isEqualTo(aResult);
        assertThat(actual.get("b")).isEqualTo(bResult);
        assertThat(actual.get("c")).isEqualTo(cResult);
        assertThat(actual.get("d")).isEqualTo(dResult);
    }
}