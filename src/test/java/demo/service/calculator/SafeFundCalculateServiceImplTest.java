package demo.service.calculator;

import demo.model.FundDivision;
import demo.model.InvestmentFund;
import demo.service.FundProducerService;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


@RunWith(SpringRunner.class)
@SpringBootTest
public class SafeFundCalculateServiceImplTest {

    @Autowired
    private FundProducerService fundProducerService;

    @Autowired
    @Qualifier("safeFundCalculateService")
    private FundCalculateService fundCalculateService;

    @Test
    public void calculateFundDivision() {
        BigDecimal investmentMoney = new BigDecimal(10000);

        //given
        List<InvestmentFund> investmentFundList = fundProducerService.prepareInvestmentFunds();

        //when
        List<FundDivision> fundDivisions = fundCalculateService.calculateFundDivision(investmentMoney, investmentFundList);

        //then
        assertTrue(CollectionUtils.isNotEmpty(fundDivisions));
        assertEquals(6, fundDivisions.size());
        assertEquals("POLISH FUND 1", fundDivisions.get(0).getFundName());
        assertEquals("POLISH FUND 2", fundDivisions.get(1).getFundName());
        assertEquals("FOREIGN FUND 1", fundDivisions.get(2).getFundName());
        assertEquals("FOREIGN FUND 2", fundDivisions.get(3).getFundName());
        assertEquals("FOREIGN FUND 3", fundDivisions.get(4).getFundName());
        assertEquals("MONEY FUND 1", fundDivisions.get(5).getFundName());
        assertEquals(new BigDecimal(1000.00).setScale(2, BigDecimal.ROUND_HALF_DOWN), fundDivisions.get(0).getDividedMoney());
        assertEquals(new BigDecimal(1000.00).setScale(2, BigDecimal.ROUND_HALF_DOWN), fundDivisions.get(1).getDividedMoney());
        assertEquals(new BigDecimal(2500.00).setScale(2, BigDecimal.ROUND_HALF_DOWN), fundDivisions.get(2).getDividedMoney());
        assertEquals(new BigDecimal(2500.00).setScale(2, BigDecimal.ROUND_HALF_DOWN), fundDivisions.get(3).getDividedMoney());
        assertEquals(new BigDecimal(2500.00).setScale(2, BigDecimal.ROUND_HALF_DOWN), fundDivisions.get(4).getDividedMoney());
        assertEquals(new BigDecimal(500.00).setScale(2, BigDecimal.ROUND_HALF_DOWN), fundDivisions.get(5).getDividedMoney());
        assertEquals(new BigDecimal(0.1000).setScale(4, BigDecimal.ROUND_HALF_DOWN), fundDivisions.get(0).getDividedPercent());
        assertEquals(new BigDecimal(0.1000).setScale(4, BigDecimal.ROUND_HALF_DOWN), fundDivisions.get(1).getDividedPercent());
        assertEquals(new BigDecimal(0.2500).setScale(4, BigDecimal.ROUND_HALF_DOWN), fundDivisions.get(2).getDividedPercent());
        assertEquals(new BigDecimal(0.2500).setScale(4, BigDecimal.ROUND_HALF_DOWN), fundDivisions.get(3).getDividedPercent());
        assertEquals(new BigDecimal(0.2500).setScale(4, BigDecimal.ROUND_HALF_DOWN), fundDivisions.get(4).getDividedPercent());
        assertEquals(new BigDecimal(0.0500).setScale(4, BigDecimal.ROUND_HALF_DOWN), fundDivisions.get(5).getDividedPercent());
    }

}