package demo.service.calculator;

import demo.model.FundAmountOfMoneyDivision;
import demo.model.InvestmentFund;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public abstract class FundCalculateServiceAbstract implements FundCalculateService {

	public List<FundAmountOfMoneyDivision> divideMoneyAndPercent(BigDecimal investmentMoney, BigDecimal investmentPercent,
                                                                 List<InvestmentFund> fundList) {
		List<FundAmountOfMoneyDivision> fundAmountOfMoneyDivisionList = new ArrayList<>();
		BigDecimal fundListSize = new BigDecimal(fundList.size()).setScale(4, BigDecimal.ROUND_HALF_DOWN);
		for (InvestmentFund investmentFund : fundList) {
			FundAmountOfMoneyDivision fundAmountOfMoneyDivision = new FundAmountOfMoneyDivision();
			fundAmountOfMoneyDivision.setFundType(investmentFund.getType());
			fundAmountOfMoneyDivision.setFundName(investmentFund.getName());

			BigDecimal dividedMoney = investmentMoney.multiply(investmentPercent).divide(fundListSize, 4, BigDecimal.ROUND_HALF_DOWN)
					.setScale(2, BigDecimal.ROUND_HALF_DOWN);
			fundAmountOfMoneyDivision.setDividedMoney(dividedMoney);
			BigDecimal dividedPercent = investmentPercent.divide(fundListSize, 4, BigDecimal.ROUND_HALF_DOWN)
					.setScale(4, BigDecimal.ROUND_HALF_DOWN);
			fundAmountOfMoneyDivision.setDividedPercent(dividedPercent);

			fundAmountOfMoneyDivisionList.add(fundAmountOfMoneyDivision);
		}
		return fundAmountOfMoneyDivisionList;
	}
}
