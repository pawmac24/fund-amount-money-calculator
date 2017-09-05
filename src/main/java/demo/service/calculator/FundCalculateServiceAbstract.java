package demo.service.calculator;

import demo.model.FundDivision;
import demo.model.FundType;
import demo.model.InvestmentFund;
import demo.model.profiles.InvestmentProfile;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
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
		int count = fundList.size();
		double percent = investmentPercent.doubleValue();
		double money = investmentMoney.doubleValue();

		BigDecimal dividedMoneyByPercent = new BigDecimal(money * percent).setScale(2, BigDecimal.ROUND_HALF_DOWN);
		BigDecimal remainder = dividedMoneyByPercent.remainder(new BigDecimal(count)).setScale(0, BigDecimal.ROUND_HALF_DOWN);
		log.debug("dividedMoneyByPercent = " + dividedMoneyByPercent
				+ ", count = " + count
				+ ", remainder = " + remainder);
		if (remainder.compareTo(BigDecimal.ZERO) > 0) {
			dividedMoneyByPercent = dividedMoneyByPercent
					.subtract(remainder)
					.setScale(2, BigDecimal.ROUND_HALF_DOWN);
		}
		log.debug("investmentMoney = " + investmentMoney
				+ ", investmentPercent = " + investmentPercent
				+ ", dividedMoneyByPercent = " + dividedMoneyByPercent
				+ ", count = " + count
				+ ", remainder = " + remainder);

		BigDecimal dividedMoney = new BigDecimal(dividedMoneyByPercent.doubleValue() / count)
				.setScale(0, BigDecimal.ROUND_HALF_DOWN);
		BigDecimal dividedPercent = new BigDecimal(percent / count)
				.setScale(4, BigDecimal.ROUND_HALF_DOWN);

		for (InvestmentFund investmentFund : fundList) {
			FundDivision fundDivision = new FundDivision();
			fundDivision.setFundType(investmentFund.getType());
			fundDivision.setFundName(investmentFund.getName());
			BigDecimal dividedMoneyCopy = new BigDecimal(dividedMoney.doubleValue())
					.setScale(0, BigDecimal.ROUND_HALF_DOWN);
			if(remainder.compareTo(BigDecimal.ZERO) > 0){
				dividedMoneyCopy = dividedMoneyCopy.add(remainder);
				remainder = BigDecimal.ZERO;
			}
			fundDivision.setDividedMoney(dividedMoneyCopy);
			fundDivision.setDividedPercent(dividedPercent);
			log.debug(fundDivision.toString());

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
