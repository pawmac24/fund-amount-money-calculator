package demo.dataprovider;

import demo.model.FundType;
import demo.model.InvestmentFund;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TestDataProvider01 {

    private List<InvestmentFund> preparePolishInvestmentFunds(){
        List<InvestmentFund> investmentFundList = new ArrayList<>();
        investmentFundList.add(new InvestmentFund("FP01", "POLISH FUND 1", FundType.POLISH));
        investmentFundList.add(new InvestmentFund("FP02", "POLISH FUND 2", FundType.POLISH));
        return investmentFundList;
    }

    private List<InvestmentFund> prepareForeignInvestmentFunds(){
        List<InvestmentFund> investmentFundList = new ArrayList<>();
        investmentFundList.add(new InvestmentFund("FF01", "FOREIGN FUND 1", FundType.FOREIGN));
        investmentFundList.add(new InvestmentFund("FF02", "FOREIGN FUND 2", FundType.FOREIGN));
        investmentFundList.add(new InvestmentFund("FF03", "FOREIGN FUND 3", FundType.FOREIGN));
        return investmentFundList;
    }

    private List<InvestmentFund> prepareMoneyInvestmentFunds(){
        List<InvestmentFund> investmentFundList = new ArrayList<>();
        investmentFundList.add(new InvestmentFund("FM01", "MONEY FUND 1", FundType.MONEY));
        return investmentFundList;
    }

    public List<InvestmentFund> prepareInvestmentFunds(){
        List<InvestmentFund> investmentFundList = new ArrayList<>();
        investmentFundList.addAll(preparePolishInvestmentFunds());
        investmentFundList.addAll(prepareForeignInvestmentFunds());
        investmentFundList.addAll(prepareMoneyInvestmentFunds());
        return investmentFundList;
    }
}
