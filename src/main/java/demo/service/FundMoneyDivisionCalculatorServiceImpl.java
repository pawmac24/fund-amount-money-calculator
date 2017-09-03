package demo.service;

import demo.model.FundAmountOfMoneyDivision;
import demo.model.InvestmentFund;
import demo.model.profiles.InvestmentProfile;
import demo.model.profiles.SafeInvestmentProfile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class FundMoneyDivisionCalculatorServiceImpl implements FundMoneyDivisionCalculatorService {

    @Override
    public List<FundAmountOfMoneyDivision> divideAmountOfMoney(BigDecimal investmentMoney,
                                                               InvestmentProfile investmentProfile,
                                                               List<InvestmentFund> investmentFunds) {
        System.out.println(investmentMoney);
        System.out.println("SafeInvestmentProfile = " + (investmentProfile instanceof SafeInvestmentProfile));
        System.out.println("investmentFunds is empty = " + (investmentFunds.isEmpty()));
        return new ArrayList<>();
    }
}
