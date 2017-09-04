package demo.service.calculator;

import demo.model.FundAmountOfMoneyDivision;
import demo.model.FundType;
import demo.model.InvestmentFund;
import demo.model.profiles.BalancedInvestmentProfile;
import demo.model.profiles.InvestmentProfile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component("balancedFundCalculateService")
public class BalancedFundCalculateServiceImpl extends FundCalculateServiceAbstract implements FundCalculateService {

	@Override
	public List<FundAmountOfMoneyDivision> divideAmountOfMoney(BigDecimal investmentMoney, List<InvestmentFund> investmentFunds) {
		List<FundAmountOfMoneyDivision> fundAmountOfMoneyDivisionList = new ArrayList<>();
		InvestmentProfile investmentProfile = new BalancedInvestmentProfile();

		List<InvestmentFund> polishFundList = investmentFunds.stream().filter(i -> i.getType().equals(FundType.POLISH))
				.collect(Collectors.toList());
		fundAmountOfMoneyDivisionList.addAll(divideMoneyAndPercent(investmentMoney, investmentProfile.getPercentPolish(), polishFundList));

		List<InvestmentFund> foreignFundList = investmentFunds.stream().filter(i -> i.getType().equals(FundType.FOREIGN))
				.collect(Collectors.toList());
		fundAmountOfMoneyDivisionList
				.addAll(divideMoneyAndPercent(investmentMoney, investmentProfile.getPercentForeign(), foreignFundList));

		List<InvestmentFund> moneyFundList = investmentFunds.stream().filter(i -> i.getType().equals(FundType.MONEY))
				.collect(Collectors.toList());
		fundAmountOfMoneyDivisionList.addAll(divideMoneyAndPercent(investmentMoney, investmentProfile.getPercentMoney(), moneyFundList));

		return fundAmountOfMoneyDivisionList;
	}
}
