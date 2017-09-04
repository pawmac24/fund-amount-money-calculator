package demo.service.calculator;

import demo.model.FundAmountOfMoneyDivision;
import demo.model.InvestmentFund;

import java.math.BigDecimal;
import java.util.List;

public interface FundCalculateService {
	List<FundAmountOfMoneyDivision> divideAmountOfMoney(BigDecimal investmentMoney, List<InvestmentFund> investmentFunds);
}
