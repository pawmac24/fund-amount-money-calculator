package demo.service.calculator.division;

import demo.model.FundDivision;
import demo.model.InvestmentFund;
import demo.model.profiles.InvestmentProfile;

import java.math.BigDecimal;
import java.util.List;

public interface FundDivisionCalculatorService {
    List<FundDivision> calculate(BigDecimal investmentMoney,
                                 InvestmentProfile investmentProfile,
                                 List<InvestmentFund> fundList);
}
