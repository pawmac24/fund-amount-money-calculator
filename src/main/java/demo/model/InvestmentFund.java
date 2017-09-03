package demo.model;

import lombok.Data;

@Data
public class InvestmentFund {
    private String id;
    private String name;
    private FundType type;
}
