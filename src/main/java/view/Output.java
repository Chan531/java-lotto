package view;

import model.*;

import java.util.*;
import java.util.stream.Collectors;

public class Output {

    private static final String LEFT_BRACKET = "[";
    private static final String RIGHT_BRACKET_AND_ENTER = "]\n";
    private static final String COMMA_AND_BLANK = ", ";
    private static final String START_COMMENT = "\n당첨 통계\n---\n";
    private static final String CONNECTION = " - ";
    private static final String COUNT_AND_ENTER = "개\n";
    private static final int NONE = 0;
    private static final String Decimal_Criteria = "%.2f";

    private final StringBuilder stringBuilder = new StringBuilder();

    public void outputLotto(int number, Lottery lottery) {
        System.out.println("\n" + number + "개를 구매했습니다.");

        for (Lotto lotto : lottery.getLottery()) {
            stringBuilder.append(LEFT_BRACKET);
            stringBuilder.append(addLotto(lotto.getLotto()));
            stringBuilder.append(RIGHT_BRACKET_AND_ENTER);
        }

        System.out.println(stringBuilder);
    }

    private StringJoiner addLotto(List<LottoNumber> numbers) {
        final StringJoiner stringJoiner = new StringJoiner(COMMA_AND_BLANK);

        for (LottoNumber number : numbers) {
            stringJoiner.add(Integer.toString(number.getLottoNumber()));
        }

        return stringJoiner;
    }

    public void outputResult(HashMap<Winning, Integer> winningResult, EarningsRateCalculator earningsRateCalculator) {
        final List<Winning> winnings = Arrays.stream(Winning.values()).filter(w -> w != Winning.FAIL).collect(Collectors.toUnmodifiableList());

        stringBuilder.setLength(NONE);
        stringBuilder.append(START_COMMENT);

        for (Winning winning : winnings) {
            stringBuilder.append(Winning.outputDescription(winning));
            stringBuilder.append(CONNECTION);
            stringBuilder.append(winningCount(winning, winningResult));
            stringBuilder.append(COUNT_AND_ENTER);
        }

        System.out.print(stringBuilder);
        System.out.println("총 수익률은 " + String.format(Decimal_Criteria, earningsRateCalculator.getEarningsRate()) + "%입니다.");
    }

    private int winningCount(Winning winning, HashMap<Winning, Integer> winningResult) {
        if (winningResult.containsKey(winning)) {
            return winningResult.get(winning);
        }

        return NONE;
    }
}
