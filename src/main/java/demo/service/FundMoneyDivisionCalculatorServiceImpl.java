package demo.service;

import demo.model.FundAmountOfMoneyDivision;
import demo.model.FundType;
import demo.model.InvestmentFund;
import demo.model.profiles.InvestmentProfile;
import demo.model.profiles.SafeInvestmentProfile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FundMoneyDivisionCalculatorServiceImpl implements FundMoneyDivisionCalculatorService {

    @Override
    public List<FundAmountOfMoneyDivision> divideAmountOfMoney(BigDecimal investmentMoney,
                                                               InvestmentProfile investmentProfile,
                                                               List<InvestmentFund> investmentFunds) {
        System.out.println(investmentMoney);
        System.out.println("SafeInvestmentProfile = " + (investmentProfile instanceof SafeInvestmentProfile));

        List<InvestmentFund> polishFundList = investmentFunds
                .stream()
                .filter(i -> i.getType().equals(FundType.POLISH))
                .collect(Collectors.toList());
        System.out.println("polishFundList size = " + polishFundList.size());

        List<InvestmentFund> foreignFundList = investmentFunds
                .stream()
                .filter(i -> i.getType().equals(FundType.FOREIGN))
                .collect(Collectors.toList());
        System.out.println("foreignFundList size = " + foreignFundList.size());

        List<InvestmentFund> moneyFundList = investmentFunds
                .stream()
                .filter(i -> i.getType().equals(FundType.MONEY))
                .collect(Collectors.toList());
        System.out.println("moneyFundList size = " + moneyFundList.size());

        return new ArrayList<>();
    }
}
