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
        List<FundDivision> fundDivisionList = new ArrayList<>();
        BigDecimal count = new BigDecimal(fundList.size());

        //calculate dividedMoney
        BigDecimal multiplyMoneyByPercent = investmentPercent.multiply(investmentMoney).setScale(2, BigDecimal.ROUND_HALF_DOWN);
        BigDecimal remainderForMoney = multiplyMoneyByPercent.remainder(count).setScale(0, BigDecimal.ROUND_HALF_DOWN);
        log.debug("multiplyMoneyByPercent = " + multiplyMoneyByPercent
                + ", count = " + count
                + ", remainderForMoney = " + remainderForMoney);
        if (remainderForMoney.compareTo(BigDecimal.ZERO) > 0) {
            multiplyMoneyByPercent = multiplyMoneyByPercent
                    .subtract(remainderForMoney)
                    .setScale(2, BigDecimal.ROUND_HALF_DOWN);
        }
        log.debug("investmentMoney = " + investmentMoney
                + ", investmentPercent = " + investmentPercent
                + ", multiplyMoneyByPercent = " + multiplyMoneyByPercent
                + ", count = " + count
                + ", remainderForMoney = " + remainderForMoney);

        BigDecimal dividedMoney = multiplyMoneyByPercent.divide(count, BigDecimal.ROUND_HALF_DOWN)
                .setScale(0, BigDecimal.ROUND_HALF_DOWN);

        //calculate dividedPercent
        BigDecimal remainderForPercent = investmentPercent
                .multiply(new BigDecimal(10000))
                .remainder(count)
                .setScale(0, BigDecimal.ROUND_HALF_DOWN);
        BigDecimal dividedPercent = null;
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

        //
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
