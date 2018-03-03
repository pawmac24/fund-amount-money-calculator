package demo.service.calculator;

import demo.model.FundDivision;
import demo.model.InvestmentFund;
import demo.model.profiles.InvestmentProfile;
import demo.service.calculator.division.ForeignFundDivisionCalculator;
import demo.service.calculator.division.MoneyFundDivisionCalculator;
import demo.service.calculator.division.PolishFundDivisionCalculator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
abstract class FundCalculateServiceAbstract implements FundCalculateService {

    @Autowired
    private PolishFundDivisionCalculator polishFundDivisionCalculator;

    @Autowired
    private ForeignFundDivisionCalculator foreignFundDivisionCalculator;

    @Autowired
    private MoneyFundDivisionCalculator moneyFundDivisionCalculator;

    List<FundDivision> calculateFundDivision(BigDecimal investmentMoney, List<InvestmentFund> investmentFunds,
                                             InvestmentProfile investmentProfile) {
        List<FundDivision> fundDivisionList = new ArrayList<>();
        fundDivisionList.addAll(polishFundDivisionCalculator.calculate(investmentMoney, investmentProfile, investmentFunds));
        fundDivisionList.addAll(foreignFundDivisionCalculator.calculate(investmentMoney, investmentProfile, investmentFunds));
        fundDivisionList.addAll(moneyFundDivisionCalculator.calculate(investmentMoney, investmentProfile, investmentFunds));
        return fundDivisionList;
    }
}
