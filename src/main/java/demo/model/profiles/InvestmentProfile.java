package demo.model.profiles;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public abstract class InvestmentProfile {
    private static final BigDecimal DENOMINATOR_PERCENT = new BigDecimal(100);

    private BigDecimal percentPolish = new BigDecimal(0);
    private BigDecimal percentForeign = new BigDecimal(0);
    private BigDecimal percentMoney = new BigDecimal(0);

    protected BigDecimal createPercent(BigDecimal nominator) {
        return nominator
                .divide(DENOMINATOR_PERCENT, 4, BigDecimal.ROUND_HALF_DOWN)
                .setScale(4, BigDecimal.ROUND_HALF_DOWN);
    }
}
