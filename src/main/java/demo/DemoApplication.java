package demo;

import demo.model.FundAmountOfMoneyDivision;
import demo.model.InvestmentFund;
import demo.model.profiles.InvestmentProfile;
import demo.model.profiles.SafeInvestmentProfile;
import demo.service.FundMoneyDivisionCalculatorService;
import demo.service.FundProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.util.List;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	@Autowired
	private FundProducerService fundProducerService;

	@Autowired
	private FundMoneyDivisionCalculatorService fundMoneyDivisionCalculatorService;

	@Override
	public void run(String... args) {
		System.out.println("Hello");
		List<InvestmentFund> investmentFunds = fundProducerService.prepareInvestmentFunds();
		investmentFunds.stream().forEach(System.out::println);

		BigDecimal investmentMoney = new BigDecimal(10000);
		InvestmentProfile investmentProfile = new SafeInvestmentProfile();
		List<FundAmountOfMoneyDivision> fundAmountOfMoneyDivisions =
				fundMoneyDivisionCalculatorService.divideAmountOfMoney(
						investmentMoney,
						investmentProfile,
						investmentFunds);
		fundAmountOfMoneyDivisions.stream().forEach(System.out::println);
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
