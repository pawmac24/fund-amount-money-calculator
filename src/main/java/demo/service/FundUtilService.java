package demo.service;

import demo.model.FundType;
import demo.model.InvestmentFund;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FundUtilService {

    public List<InvestmentFund> filterFunds(List<InvestmentFund> investmentFunds, FundType fundType) {
        return investmentFunds.stream()
                .filter(i -> i.getType().equals(fundType))
                .collect(Collectors.toList());
    }
}
