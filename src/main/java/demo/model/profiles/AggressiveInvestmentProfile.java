package demo.model.profiles;

import java.math.BigDecimal;

public class AggressiveInvestmentProfile extends InvestmentProfile {

    private static final BigDecimal FACTOR_POLISH = new BigDecimal(40);
    private static final BigDecimal FACTOR_FOREIGN = new BigDecimal(20);
    private static final BigDecimal FACTOR_MONEY = new BigDecimal(40);

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
