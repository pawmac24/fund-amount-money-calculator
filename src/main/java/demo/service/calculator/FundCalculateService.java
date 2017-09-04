package demo.service.calculator;

import demo.model.FundDivision;
import demo.model.InvestmentFund;

import java.math.BigDecimal;
import java.util.List;

public interface FundCalculateService {
	List<FundDivision> calculateFundDivision(BigDecimal investmentMoney, List<InvestmentFund> investmentFunds);
}
