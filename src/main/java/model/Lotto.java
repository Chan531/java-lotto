package model;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

public class Lotto {

    private static final int LOTTO_COUNT = 6;

    private final List<LottoNumber> lottoNumbers;

    public Lotto(List<LottoNumber> lottoNumbers) {
        checkLotteryCount(lottoNumbers);
        checkLotteryOverlap(lottoNumbers);
        this.lottoNumbers = lottoNumbers;
    }

    private void checkLotteryCount(List<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != LOTTO_COUNT) {
            throw new IllegalArgumentException("[ERROR] 6개의 숫자를 입력해주세요.");
        }
    }

    private void checkLotteryOverlap(List<LottoNumber> lottoNumbers) {
        final HashSet<LottoNumber> overlapChecker = new HashSet<>(lottoNumbers);

        if (overlapChecker.size() != LOTTO_COUNT) {
            throw new IllegalArgumentException("[ERROR] 중복이 아닌 숫자를 입력해주세요.");
        }
    }

    public List<LottoNumber> getLotto() {
        return this.lottoNumbers;
    }

    public int getMatchCountOfWinningNumbers(Lotto winningNumbers) {
        return winningNumbers.drawLottoWithWinningNumbers(this.lottoNumbers);
    }

    private int drawLottoWithWinningNumbers(List<LottoNumber> lottoNumbers) {
        return (int) this.lottoNumbers.stream()
                .filter(getLottoNumberPredicate(lottoNumbers))
                .count();
    }

    private Predicate<LottoNumber> getLottoNumberPredicate(List<LottoNumber> lottoNumbers) {
        return lottoNumber -> lottoNumbers.stream().anyMatch(Predicate.isEqual(lottoNumber));
    }

    public int getMatchCountOfBonusNumber(LottoNumber bonusNumber) {
        if (drawLottoWithBonusNumber(bonusNumber)) {
            return 1;
        }

        return 0;
    }

    private boolean drawLottoWithBonusNumber(LottoNumber bonusNumber) {
        return this.lottoNumbers.stream()
                .anyMatch(lottoNumber -> lottoNumber.equals(bonusNumber));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lotto lotto1 = (Lotto) o;
        return Objects.equals(lottoNumbers, lotto1.lottoNumbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoNumbers);
    }
}
