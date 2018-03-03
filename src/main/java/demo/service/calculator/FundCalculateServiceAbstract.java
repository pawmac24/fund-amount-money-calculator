package demo.service.calculator;

import demo.model.FundDivision;
import demo.model.InvestmentFund;
import demo.model.profiles.InvestmentProfile;
import demo.service.calculator.division.ForeignFundDivisionCalculator;
import demo.service.calculator.division.MoneyFundDivisionCalculator;
import demo.service.calculator.division.PolishFundDivisionCalculator;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Slf4j
abstract class FundCalculateServiceAbstract implements FundCalculateService {

	List<FundDivision> calculateFundDivision(BigDecimal investmentMoney, List<InvestmentFund> investmentFunds,
											 InvestmentProfile investmentProfile) {
		List<FundDivision> fundDivisionList = new ArrayList<>();
		fundDivisionList.addAll(new PolishFundDivisionCalculator().calculate(investmentMoney, investmentProfile, investmentFunds));
		fundDivisionList.addAll(new ForeignFundDivisionCalculator().calculate(investmentMoney, investmentProfile, investmentFunds));
		fundDivisionList.addAll(new MoneyFundDivisionCalculator().calculate(investmentMoney, investmentProfile, investmentFunds));
		return fundDivisionList;
	}
}
