package demo.service;

import demo.model.FundAmountOfMoneyDivision;
import demo.model.InvestmentFund;
import demo.model.profiles.InvestmentProfile;

import java.math.BigDecimal;
import java.util.List;

public interface FundMoneyDivisionCalculatorService {
    List<FundAmountOfMoneyDivision> divideAmountOfMoney(BigDecimal investmentMoney,
                                                        InvestmentProfile investmentProfile, List<InvestmentFund> investmentFunds);
}
