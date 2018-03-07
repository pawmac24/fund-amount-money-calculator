package demo.service;

import demo.AppProperties;
import demo.model.FundType;
import demo.model.InvestmentFund;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
@Slf4j
public class FundProducerServiceImpl implements FundProducerService{

    private AppProperties app;

    @Autowired
    public void setApp(AppProperties app) {
        this.app = app;
    }

    private List<InvestmentFund> preparePolishInvestmentFunds(){
        return app.getPolishFunds()
                .stream()
                .map(f -> new InvestmentFund(f.getId(), f.getName(), FundType.POLISH))
                .collect(toList());
    }

    private List<InvestmentFund> prepareForeignInvestmentFunds(){
        return app.getPolishFunds()
                .stream()
                .map(f -> new InvestmentFund(f.getId(), f.getName(), FundType.FOREIGN))
                .collect(toList());
    }

    private List<InvestmentFund> prepareMoneyInvestmentFunds(){
        return app.getPolishFunds()
                .stream()
                .map(f -> new InvestmentFund(f.getId(), f.getName(), FundType.MONEY))
                .collect(toList());
    }

    public List<InvestmentFund> prepareInvestmentFunds(){
        List<InvestmentFund> investmentFundList = new ArrayList<>();
        investmentFundList.addAll(preparePolishInvestmentFunds());
        investmentFundList.addAll(prepareForeignInvestmentFunds());
        investmentFundList.addAll(prepareMoneyInvestmentFunds());
        return investmentFundList;
    }
}
