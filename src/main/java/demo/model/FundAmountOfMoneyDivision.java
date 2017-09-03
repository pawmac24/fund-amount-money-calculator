package demo.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class FundAmountOfMoneyDivision {
    private FundType fundType;
    private String fundName;
    private BigDecimal dividedMoney;
    private BigDecimal dividedPercent;
}
