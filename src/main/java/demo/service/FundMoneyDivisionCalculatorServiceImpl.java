package demo.service;

import demo.model.FundAmountOfMoneyDivision;
import demo.model.FundType;
import demo.model.InvestmentFund;
import demo.model.profiles.InvestmentProfile;
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
        fundAmountOfMoneyDivisionList.addAll(divideMoneyAndPercent(
                investmentMoney,
                investmentProfile.getPercentPolish(),
                polishFundList));

        List<InvestmentFund> foreignFundList = investmentFunds
                .stream()
                .filter(i -> i.getType().equals(FundType.FOREIGN))
                .collect(Collectors.toList());
        fundAmountOfMoneyDivisionList.addAll(divideMoneyAndPercent(
                investmentMoney,
                investmentProfile.getPercentForeign(),
                foreignFundList));


        List<InvestmentFund> moneyFundList = investmentFunds
                .stream()
                .filter(i -> i.getType().equals(FundType.MONEY))
                .collect(Collectors.toList());
        fundAmountOfMoneyDivisionList.addAll(divideMoneyAndPercent(
                investmentMoney,
                investmentProfile.getPercentMoney(),
                moneyFundList));

        return fundAmountOfMoneyDivisionList;
    }

    private List<FundAmountOfMoneyDivision> divideMoneyAndPercent(BigDecimal investmentMoney,
                                                                  BigDecimal investmentPercent,
                                                                  List<InvestmentFund> fundList) {
        List<FundAmountOfMoneyDivision> fundAmountOfMoneyDivisionList = new ArrayList<>();
        BigDecimal fundListSize = new BigDecimal(fundList.size()).setScale(4, BigDecimal.ROUND_HALF_DOWN);
        for (InvestmentFund investmentFund : fundList) {
            FundAmountOfMoneyDivision fundAmountOfMoneyDivision = new FundAmountOfMoneyDivision();
            fundAmountOfMoneyDivision.setFundType(investmentFund.getType());
            fundAmountOfMoneyDivision.setFundName(investmentFund.getName());

            BigDecimal dividedMoney = investmentMoney
                    .multiply(investmentPercent)
                    .divide(fundListSize, 4, BigDecimal.ROUND_HALF_DOWN)
                    .setScale(2, BigDecimal.ROUND_HALF_DOWN);
            fundAmountOfMoneyDivision.setDividedMoney(dividedMoney);
            BigDecimal dividedPercent = investmentPercent
                    .divide(fundListSize, 4, BigDecimal.ROUND_HALF_DOWN)
                    .setScale(4, BigDecimal.ROUND_HALF_DOWN);
            fundAmountOfMoneyDivision.setDividedPercent(dividedPercent);

            fundAmountOfMoneyDivisionList.add(fundAmountOfMoneyDivision);
        }
        return fundAmountOfMoneyDivisionList;
    }
}
