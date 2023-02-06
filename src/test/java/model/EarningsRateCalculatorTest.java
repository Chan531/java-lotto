package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class EarningsRateCalculatorTest {

    private static final double FIRST_PRIZE_EARNINGS_RATE = 20000000;

    @Test
    @DisplayName("수익률을 정확하게 계산할 수 있다.")
    void canGetRightValue() {
        // given
        final int money = 10000;
        final HashMap<Winning, Integer> winningResult = new HashMap<>();
        winningResult.put(Winning.FIRST, 1);

        // when
        final EarningsRateCalculator earningsRateCalculator = new EarningsRateCalculator(money, winningResult);

        // then
        assertThat(earningsRateCalculator.getEarningsRate()).isEqualTo(FIRST_PRIZE_EARNINGS_RATE);
    }
}