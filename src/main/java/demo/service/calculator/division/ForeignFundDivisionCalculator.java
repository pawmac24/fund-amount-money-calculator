package demo.service.calculator.division;

import demo.model.FundDivision;
import demo.model.FundType;
import demo.model.InvestmentFund;
import demo.model.profiles.InvestmentProfile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class ForeignFundDivisionCalculator extends FundDivisionAbstractCalculator {

    @Override
    public List<FundDivision> calculate(BigDecimal investmentMoney,
                                        InvestmentProfile investmentProfile,
                                        List<InvestmentFund> fundList) {

        List<InvestmentFund> foreignFundList = fundUtilService.filterFunds(fundList, FundType.FOREIGN);
        return calculateFundDivision(investmentMoney, investmentProfile.getPercentForeign(), foreignFundList);
    }
}
