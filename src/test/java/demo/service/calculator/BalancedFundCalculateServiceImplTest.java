package demo.service.calculator;

import demo.model.FundDivision;
import demo.model.FundType;
import demo.model.InvestmentFund;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


@RunWith(SpringRunner.class)
@SpringBootTest
public class BalancedFundCalculateServiceImplTest {

    @Autowired
    @Qualifier("balancedFundCalculateService")
    private FundCalculateService fundCalculateService;

    @Test
    public void testBalancedCalculateFundDivision(){
        BigDecimal investmentMoney = new BigDecimal(10000);

        //given
        List<InvestmentFund> investmentFundList = new ArrayList<>();
        investmentFundList.add(new InvestmentFund("FP01", "POLISH FUND 1", FundType.POLISH));
        investmentFundList.add(new InvestmentFund("FP02", "POLISH FUND 2", FundType.POLISH));

        investmentFundList.add(new InvestmentFund("FF01", "FOREIGN FUND 1", FundType.FOREIGN));
        investmentFundList.add(new InvestmentFund("FF02", "FOREIGN FUND 2", FundType.FOREIGN));
        investmentFundList.add(new InvestmentFund("FF03", "FOREIGN FUND 3", FundType.FOREIGN));

        investmentFundList.add(new InvestmentFund("FM01", "MONEY FUND 1", FundType.MONEY));

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
        assertEquals(new BigDecimal(1500.00).setScale(2, BigDecimal.ROUND_HALF_DOWN), fundDivisions.get(0).getDividedMoney());
        assertEquals(new BigDecimal(1500.00).setScale(2, BigDecimal.ROUND_HALF_DOWN), fundDivisions.get(1).getDividedMoney());
        assertEquals(new BigDecimal(2000.00).setScale(2, BigDecimal.ROUND_HALF_DOWN), fundDivisions.get(2).getDividedMoney());
        assertEquals(new BigDecimal(2000.00).setScale(2, BigDecimal.ROUND_HALF_DOWN), fundDivisions.get(3).getDividedMoney());
        assertEquals(new BigDecimal(2000.00).setScale(2, BigDecimal.ROUND_HALF_DOWN), fundDivisions.get(4).getDividedMoney());
        assertEquals(new BigDecimal(1000.00).setScale(2, BigDecimal.ROUND_HALF_DOWN), fundDivisions.get(5).getDividedMoney());
        assertEquals(new BigDecimal(0.1500).setScale(4, BigDecimal.ROUND_HALF_DOWN), fundDivisions.get(0).getDividedPercent());
        assertEquals(new BigDecimal(0.1500).setScale(4, BigDecimal.ROUND_HALF_DOWN), fundDivisions.get(1).getDividedPercent());
        assertEquals(new BigDecimal(0.2000).setScale(4, BigDecimal.ROUND_HALF_DOWN), fundDivisions.get(2).getDividedPercent());
        assertEquals(new BigDecimal(0.2000).setScale(4, BigDecimal.ROUND_HALF_DOWN), fundDivisions.get(3).getDividedPercent());
        assertEquals(new BigDecimal(0.2000).setScale(4, BigDecimal.ROUND_HALF_DOWN), fundDivisions.get(4).getDividedPercent());
        assertEquals(new BigDecimal(0.1000).setScale(4, BigDecimal.ROUND_HALF_DOWN), fundDivisions.get(5).getDividedPercent());
    }
}