package demo.service;

import demo.model.FundAmountOfMoneyDivision;
import demo.model.FundType;
import demo.model.InvestmentFund;
import demo.model.profiles.InvestmentProfile;
import demo.model.profiles.SafeInvestmentProfile;
import org.apache.commons.collections.ArrayStack;
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

        List<FundAmountOfMoneyDivision> fundAmountOfMoneyDivisionList = new ArrayList<>();

        List<InvestmentFund> polishFundList = investmentFunds
                .stream()
                .filter(i -> i.getType().equals(FundType.POLISH))
                .collect(Collectors.toList());
        BigDecimal fundListSize = new BigDecimal(polishFundList.size()).setScale(4, BigDecimal.ROUND_HALF_DOWN);
        for(InvestmentFund investmentFund : polishFundList){
            FundAmountOfMoneyDivision fundAmountOfMoneyDivision = new FundAmountOfMoneyDivision();
            fundAmountOfMoneyDivision.setFundType(investmentFund.getType());
            fundAmountOfMoneyDivision.setFundName(investmentFund.getName());
            BigDecimal dividedMoney = investmentMoney.multiply(investmentProfile.getPercentPolish())
                    .divide(fundListSize, 4, BigDecimal.ROUND_HALF_DOWN)
                    .setScale(0, BigDecimal.ROUND_HALF_DOWN);
            fundAmountOfMoneyDivision.setDividedMoney(dividedMoney);
            BigDecimal dividedPercent = investmentProfile.getPercentPolish()
                    .divide(fundListSize, 4, BigDecimal.ROUND_HALF_DOWN)
                    .setScale(4, BigDecimal.ROUND_HALF_DOWN);
            fundAmountOfMoneyDivision.setDividedPercent(dividedPercent);
            System.out.println(fundAmountOfMoneyDivision.toString());
            fundAmountOfMoneyDivisionList.add(fundAmountOfMoneyDivision);
        }

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

        return fundAmountOfMoneyDivisionList;
    }
}
