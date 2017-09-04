package demo.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class FundDivision {
    private FundType fundType;
    private String fundName;
    private BigDecimal dividedMoney;
    private BigDecimal dividedPercent;
}
