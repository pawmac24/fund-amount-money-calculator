package demo;

import demo.model.FundDivision;
import demo.model.InvestmentFund;
import demo.service.FundProducerService;
import demo.service.calculator.FundCalculateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.util.List;

@SpringBootApplication
@Slf4j
public class DemoApplication implements CommandLineRunner {

	@Autowired
	private FundProducerService fundProducerService;

	@Autowired
	@Qualifier("safeFundCalculateService")
	private FundCalculateService saveFundCalculateService;

	@Autowired
	@Qualifier("balancedFundCalculateService")
	private FundCalculateService balancedFundCalculateService;

	@Autowired
	@Qualifier("aggressiveFundCalculateService")
	private FundCalculateService aggressiveFundCalculateService;

	@Override
	public void run(String... args) {
		log.debug("=== PREPARE ===");
		List<InvestmentFund> investmentFunds = fundProducerService.prepareInvestmentFunds();
		investmentFunds.stream().forEach(f -> log.debug(f.toString()));

		BigDecimal investmentMoney = new BigDecimal(10000);

		log.debug("=== SAVE ===");
		List<FundDivision> saveFundDivisions = saveFundCalculateService.calculateFundDivision(investmentMoney, investmentFunds);
		saveFundDivisions.stream().forEach(f -> log.debug(f.toString()));

		log.debug("=== BALANCED ===");
		List<FundDivision> balancedFundDivisions = balancedFundCalculateService.calculateFundDivision(investmentMoney, investmentFunds);
		balancedFundDivisions.stream().forEach(f -> log.debug(f.toString()));

		log.debug("=== AGGRESSIVE ===");
		List<FundDivision> aggressiveFundDivisions = aggressiveFundCalculateService.calculateFundDivision(investmentMoney, investmentFunds);
		aggressiveFundDivisions.stream().forEach(f -> log.debug(f.toString()));
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
