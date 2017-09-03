package demo;

import demo.model.InvestmentFund;
import demo.service.FundProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	@Autowired
	private FundProducerService fundProducerService;

	@Override
	public void run(String... args) {
		System.out.println("Hello");
		List<InvestmentFund> investmentFunds = fundProducerService.prepareInvestmentFunds();
		investmentFunds.stream().forEach(System.out::println);
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
