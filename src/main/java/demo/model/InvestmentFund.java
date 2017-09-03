package demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
public class InvestmentFund {
    private String id;
    private String name;
    private FundType type;
}
