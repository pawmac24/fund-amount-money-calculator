package demo.service.calculator.division;

import demo.model.FundDivision;
import demo.model.FundType;
import demo.model.InvestmentFund;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
abstract class FundDivisionAbstractCalculator implements FundDivisionCalculatorService{

    List<InvestmentFund> filterFunds(List<InvestmentFund> investmentFunds, FundType fundType) {
        return investmentFunds.stream()
                .filter(i -> i.getType().equals(fundType))
                .collect(Collectors.toList());
    }

    List<FundDivision> calculateFundDivision(BigDecimal investmentMoney, BigDecimal investmentPercent,
                                                     List<InvestmentFund> fundList) {
        BigDecimal count = new BigDecimal(fundList.size());

        BigDecimal moneyPerFundType = investmentPercent.multiply(investmentMoney).setScale(2, BigDecimal.ROUND_HALF_DOWN);
        moneyPerFundType = removeFractionFromMoney(moneyPerFundType);

        BigDecimal remainderForMoney = moneyPerFundType.remainder(count).setScale(0, BigDecimal.ROUND_HALF_DOWN);
        log.debug("moneyPerFundType = " + moneyPerFundType
                + ", count = " + count
                + ", remainderForMoney = " + remainderForMoney);
        moneyPerFundType = subtractRemainderOfDivisionFromMoney(moneyPerFundType, remainderForMoney);
        log.debug("investmentMoney = " + investmentMoney
                + ", investmentPercent = " + investmentPercent
                + ", moneyPerFundType = " + moneyPerFundType
                + ", count = " + count
                + ", remainderForMoney = " + remainderForMoney);

        BigDecimal dividedMoney = moneyPerFundType.divide(count, BigDecimal.ROUND_HALF_DOWN)
                .setScale(0, BigDecimal.ROUND_HALF_DOWN);

        BigDecimal remainderForPercent = calculateRemainderForPercent(investmentPercent, count);
        BigDecimal dividedPercent = calculateDividedPercent(investmentPercent, count, remainderForPercent);
        return createFundDivisionList(fundList, remainderForMoney, dividedMoney, remainderForPercent, dividedPercent);
    }

    private BigDecimal subtractRemainderOfDivisionFromMoney(BigDecimal money, BigDecimal remainderForMoney) {
        if (remainderForMoney.compareTo(BigDecimal.ZERO) > 0) {
            money = money
                    .subtract(remainderForMoney)
                    .setScale(2, BigDecimal.ROUND_HALF_DOWN);
        }
        return money;
    }

    private BigDecimal removeFractionFromMoney(BigDecimal money) {
        BigDecimal fractionalPart = money.remainder( BigDecimal.ONE );
        if(fractionalPart.compareTo(BigDecimal.ZERO) > 0){
            money = money
                    .subtract(fractionalPart)
                    .setScale(2, BigDecimal.ROUND_HALF_DOWN);
        }
        return money;
    }

    private BigDecimal calculateRemainderForPercent(BigDecimal investmentPercent, BigDecimal count) {
        return investmentPercent
                    .multiply(new BigDecimal(10000))
                    .remainder(count)
                    .setScale(0, BigDecimal.ROUND_HALF_DOWN);
    }

    private BigDecimal calculateDividedPercent(BigDecimal investmentPercent, BigDecimal count, BigDecimal remainderForPercent) {
        BigDecimal dividedPercent;
        if (remainderForPercent.compareTo(BigDecimal.ZERO) > 0) {
            log.debug("remainderForPercent=" + remainderForPercent);
            dividedPercent = investmentPercent.multiply(new BigDecimal(10000))
                    .subtract(remainderForPercent)
                    .divide(new BigDecimal(10000), BigDecimal.ROUND_HALF_DOWN)
                    .divide(count, BigDecimal.ROUND_HALF_DOWN)
                    .setScale(4, BigDecimal.ROUND_HALF_DOWN);
            log.debug("dividedPercent=" + dividedPercent);
        }
        else{
            dividedPercent = investmentPercent
                    .divide(count, BigDecimal.ROUND_HALF_DOWN)
                    .setScale(4, BigDecimal.ROUND_HALF_DOWN);
            log.debug("dividedPercent=" + dividedPercent);
        }
        return dividedPercent;
    }

    private List<FundDivision> createFundDivisionList(List<InvestmentFund> fundList, BigDecimal remainderForMoney, BigDecimal dividedMoney, BigDecimal remainderForPercent, BigDecimal dividedPercent) {
        List<FundDivision> fundDivisionList = new ArrayList<>();
        for (InvestmentFund investmentFund : fundList) {
            FundDivision fundDivision = new FundDivision();
            fundDivision.setFundType(investmentFund.getType());
            fundDivision.setFundName(investmentFund.getName());
            BigDecimal dividedMoneyCopy = new BigDecimal(dividedMoney.doubleValue())
                    .setScale(0, BigDecimal.ROUND_HALF_DOWN);
            if(remainderForMoney.compareTo(BigDecimal.ZERO) > 0){
                dividedMoneyCopy = dividedMoneyCopy.add(remainderForMoney);
                remainderForMoney = BigDecimal.ZERO;
            }
            fundDivision.setDividedMoney(dividedMoneyCopy);

            BigDecimal dividedPercentCopy = new BigDecimal(dividedPercent.doubleValue())
                    .setScale(4, BigDecimal.ROUND_HALF_DOWN);
            if(remainderForPercent.compareTo(BigDecimal.ZERO) > 0){
                dividedPercentCopy = dividedPercentCopy
                        .add(new BigDecimal(remainderForPercent.doubleValue() * 0.0001))
                        .setScale(4, BigDecimal.ROUND_HALF_DOWN);
                remainderForPercent = BigDecimal.ZERO;
            }
            fundDivision.setDividedPercent(dividedPercentCopy);
            log.debug(fundDivision.toString());

            fundDivisionList.add(fundDivision);
        }

        return fundDivisionList;
    }
}
