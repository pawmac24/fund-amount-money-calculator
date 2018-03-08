package demo.service.calculator;

import demo.model.FundDivision;
import demo.model.InvestmentFund;
import demo.model.profiles.AggressiveInvestmentProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component("aggressiveFundCalculateService")
public class AggressiveFundCalculateServiceImpl extends FundCalculateServiceAbstract {

	@Autowired
	private AggressiveInvestmentProfile aggressiveInvestmentProfile;

	@Override
	public List<FundDivision> calculateFundDivision(BigDecimal investmentMoney, List<InvestmentFund> investmentFunds) {
		return calculateFundDivision(investmentMoney, investmentFunds, aggressiveInvestmentProfile);
	}
}
