package demo.service.calculator;

import demo.model.FundDivision;
import demo.model.FundType;
import demo.model.InvestmentFund;
import demo.model.profiles.InvestmentProfile;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

abstract class FundCalculateServiceAbstract implements FundCalculateService {

	List<FundDivision> calculateFundDivision(BigDecimal investmentMoney, List<InvestmentFund> investmentFunds,
											 InvestmentProfile investmentProfile) {
		List<FundDivision> fundDivisionList = new ArrayList<>();
		fundDivisionList.addAll(calculatePolishFundDivision(investmentMoney, investmentProfile, investmentFunds));
		fundDivisionList.addAll(calculateForeignFundDivision(investmentMoney, investmentProfile, investmentFunds));
		fundDivisionList.addAll(calculateMoneyFundDivision(investmentMoney, investmentProfile, investmentFunds));
		return fundDivisionList;
	}

	private List<FundDivision> calculatePolishFundDivision(BigDecimal investmentMoney, InvestmentProfile investmentProfile,
												   List<InvestmentFund> fundList) {
		List<InvestmentFund> polishFundList = filterFunds(fundList, FundType.POLISH);
		return calculateFundDivision(investmentMoney, investmentProfile.getPercentPolish(), polishFundList);
	}

	private List<FundDivision> calculateForeignFundDivision(BigDecimal investmentMoney, InvestmentProfile investmentProfile,
													List<InvestmentFund> fundList) {
		List<InvestmentFund> foreignFundList = filterFunds(fundList, FundType.FOREIGN);
		return calculateFundDivision(investmentMoney, investmentProfile.getPercentForeign(), foreignFundList);
	}

	private List<FundDivision> calculateMoneyFundDivision(BigDecimal investmentMoney, InvestmentProfile investmentProfile,
												  List<InvestmentFund> fundList) {
		List<InvestmentFund> moneyFundList = filterFunds(fundList, FundType.MONEY);
		return calculateFundDivision(investmentMoney, investmentProfile.getPercentMoney(), moneyFundList);
	}

	private List<FundDivision> calculateFundDivision(BigDecimal investmentMoney, BigDecimal investmentPercent,
													 List<InvestmentFund> fundList) {
		List<FundDivision> fundDivisionList = new ArrayList<>();
		for (InvestmentFund investmentFund : fundList) {

			double money = investmentMoney.doubleValue();
			double percent = investmentPercent.doubleValue();
			int count = fundList.size();

			BigDecimal dividedMoney = new BigDecimal((money * percent) / count)
					.setScale(2, BigDecimal.ROUND_HALF_DOWN);
			BigDecimal dividedPercent = new BigDecimal(percent / count)
					.setScale(4, BigDecimal.ROUND_HALF_DOWN);

			FundDivision fundDivision = new FundDivision();
			fundDivision.setFundType(investmentFund.getType());
			fundDivision.setFundName(investmentFund.getName());
			fundDivision.setDividedMoney(dividedMoney);
			fundDivision.setDividedPercent(dividedPercent);

			fundDivisionList.add(fundDivision);
		}
		return fundDivisionList;
	}

	private List<InvestmentFund> filterFunds(List<InvestmentFund> investmentFunds, FundType fundType) {
		return investmentFunds.stream()
				.filter(i -> i.getType().equals(fundType))
				.collect(Collectors.toList());
	}
}
