package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class WinningLottoTest {

    private final LottoNumber one = new LottoNumber(1);
    private final LottoNumber two = new LottoNumber(2);
    private final LottoNumber three = new LottoNumber(3);
    private final LottoNumber four = new LottoNumber(4);
    private final LottoNumber five = new LottoNumber(5);
    private final LottoNumber six = new LottoNumber(6);

    @Test
    @DisplayName("당첨 번호와 중복되는 보너스 번호를 전달받을 때 예외를 발생시킨다.")
    void canThrowExceptionWhenBonusNumberOverlap() {
        // given
        final WinningLotto winningLotto = new WinningLotto(new Lotto(List.of(one, two, three, four, five, six)));
        final LottoNumber bonusNumber = six;

        // when, then
        assertThatThrownBy(() -> winningLotto.checkBonusNumberOverlap(bonusNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 중복이 아닌 보너스 번호를 입력해주세요.");
    }
}