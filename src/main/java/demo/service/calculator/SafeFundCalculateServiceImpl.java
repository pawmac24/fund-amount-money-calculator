package demo.service.calculator;

import demo.model.FundDivision;
import demo.model.InvestmentFund;
import demo.model.profiles.SafeInvestmentProfile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component("safeFundCalculateService")
public class SafeFundCalculateServiceImpl extends FundCalculateServiceAbstract {

	@Override
	public List<FundDivision> calculateFundDivision(BigDecimal investmentMoney, List<InvestmentFund> investmentFunds) {
		return calculateFundDivision(investmentMoney, investmentFunds, new SafeInvestmentProfile());
	}
}
