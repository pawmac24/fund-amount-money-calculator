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

		//calculate dividedMoney
		BigDecimal dividedMoneyByPercent = new BigDecimal(money * percent).setScale(2, BigDecimal.ROUND_HALF_DOWN);
		BigDecimal remainderForMoney = dividedMoneyByPercent.remainder(new BigDecimal(count)).setScale(0, BigDecimal.ROUND_HALF_DOWN);
		log.debug("dividedMoneyByPercent = " + dividedMoneyByPercent
				+ ", count = " + count
				+ ", remainderForMoney = " + remainderForMoney);
		if (remainderForMoney.compareTo(BigDecimal.ZERO) > 0) {
			dividedMoneyByPercent = dividedMoneyByPercent
					.subtract(remainderForMoney)
					.setScale(2, BigDecimal.ROUND_HALF_DOWN);
		}
		log.debug("investmentMoney = " + investmentMoney
				+ ", investmentPercent = " + investmentPercent
				+ ", dividedMoneyByPercent = " + dividedMoneyByPercent
				+ ", count = " + count
				+ ", remainderForMoney = " + remainderForMoney);

		BigDecimal dividedMoney = new BigDecimal(dividedMoneyByPercent.doubleValue() / count)
				.setScale(0, BigDecimal.ROUND_HALF_DOWN);

		//calculate dividedPercent
		List<BigDecimal> dividedPercentList = calculateDividedPercentList(investmentPercent, count);

		//
		int i = 0;
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
			fundDivision.setDividedPercent(dividedPercentList.get(i));
			log.debug(fundDivision.toString());

			fundDivisionList.add(fundDivision);
			i++;
		}
		return fundDivisionList;
	}

	private List<InvestmentFund> filterFunds(List<InvestmentFund> investmentFunds, FundType fundType) {
		return investmentFunds.stream()
				.filter(i -> i.getType().equals(fundType))
				.collect(Collectors.toList());
	}

	private List<BigDecimal> calculateDividedPercentList(BigDecimal investmentPercent, int count){
		BigDecimal remainderForPercent = investmentPercent
				.multiply(new BigDecimal(10000))
				.remainder(new BigDecimal(count))
				.setScale(0, BigDecimal.ROUND_HALF_DOWN);
		BigDecimal dividedPercent = null;
		if (remainderForPercent.compareTo(BigDecimal.ZERO) > 0) {
			log.debug("remainderForPercent=" + remainderForPercent);
			dividedPercent = investmentPercent.multiply(new BigDecimal(10000))
					.subtract(remainderForPercent)
					.divide(new BigDecimal(10000), BigDecimal.ROUND_HALF_DOWN)
					.divide(new BigDecimal(count), BigDecimal.ROUND_HALF_DOWN)
					.setScale(4, BigDecimal.ROUND_HALF_DOWN);
			log.debug("dividedPercent=" + dividedPercent);
		}
		else{
			dividedPercent = investmentPercent
					.divide(new BigDecimal(count), BigDecimal.ROUND_HALF_DOWN)
					.setScale(4, BigDecimal.ROUND_HALF_DOWN);
			log.debug("dividedPercent=" + dividedPercent);
		}
		BigDecimal firstDividedPercent = new BigDecimal(dividedPercent.doubleValue())
				.setScale(4, BigDecimal.ROUND_HALF_DOWN);
		if(remainderForPercent.compareTo(BigDecimal.ZERO) > 0){
			firstDividedPercent = firstDividedPercent
					.add(new BigDecimal(remainderForPercent.doubleValue() * 0.0001))
					.setScale(4, BigDecimal.ROUND_HALF_DOWN);
		}
		List<BigDecimal> dividedPercentList = new ArrayList<>();
		dividedPercentList.add(firstDividedPercent);
		for(int i = 1; i < count; i++){
			dividedPercentList.add(dividedPercent);
		}
		return dividedPercentList;
	}
}
