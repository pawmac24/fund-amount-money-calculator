package demo.model.profiles;

import java.math.BigDecimal;

public class SafeInvestmentProfile extends InvestmentProfile {

    @Override
    public BigDecimal getPercentPolish() {
        return createPercent(new BigDecimal(20));
    }

    @Override
    public BigDecimal getPercentForeign() {
        return createPercent(new BigDecimal(75));
    }

    @Override
    public BigDecimal getPercentMoney() {
        return createPercent(new BigDecimal(5));
    }
}
