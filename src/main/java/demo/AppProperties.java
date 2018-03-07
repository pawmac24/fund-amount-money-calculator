package demo;

import demo.model.InvestmentFund;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@PropertySource("classpath:application.properties")
@ConfigurationProperties(prefix = "app")
@Data
public class AppProperties {

    private List<InvestmentFund> polishFunds = new ArrayList<>();
    private List<InvestmentFund> foreignFunds = new ArrayList<>();
    private List<InvestmentFund> moneyFunds = new ArrayList<>();
}
