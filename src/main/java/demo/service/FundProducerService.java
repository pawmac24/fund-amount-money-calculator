package demo.service;

import demo.model.InvestmentFund;

import java.util.List;

public interface FundProducerService {
    List<InvestmentFund> prepareInvestmentFunds();
}
