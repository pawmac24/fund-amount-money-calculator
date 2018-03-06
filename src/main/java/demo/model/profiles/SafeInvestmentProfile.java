package demo.model.profiles;

import java.math.BigDecimal;

public class SafeInvestmentProfile extends InvestmentProfile {

    private static final BigDecimal FACTOR_POLISH = new BigDecimal(20);
    private static final BigDecimal FACTOR_FOREIGN = new BigDecimal(75);
    private static final BigDecimal FACTOR_MONEY = new BigDecimal(5);

    @Override
    public BigDecimal getPercentPolish() {
        return createPercent(FACTOR_POLISH);
    }

    @Override
    public BigDecimal getPercentForeign() {
        return createPercent(FACTOR_FOREIGN);
    }

    @Override
    public BigDecimal getPercentMoney() {
        return createPercent(FACTOR_MONEY);
    }
}
