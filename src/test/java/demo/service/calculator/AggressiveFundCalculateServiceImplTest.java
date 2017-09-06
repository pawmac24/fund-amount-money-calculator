package demo.service.calculator;

import demo.dataprovider.TestDataProvider01;
import demo.model.FundDivision;
import demo.model.InvestmentFund;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class AggressiveFundCalculateServiceImplTest {

    @Autowired
    private TestDataProvider01 testDataProvider01;

    @Autowired
    @Qualifier("aggressiveFundCalculateService")
    private FundCalculateService fundCalculateService;

    @Test
    public void testAggressiveCalculateFundDivision01() throws Exception {
        BigDecimal investmentMoney = new BigDecimal(10000);

        //given
        List<InvestmentFund> investmentFundList = testDataProvider01.prepareInvestmentFunds();

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
        assertEquals(new BigDecimal(2000), fundDivisions.get(0).getDividedMoney());
        assertEquals(new BigDecimal(2000), fundDivisions.get(1).getDividedMoney());
        assertEquals(new BigDecimal(668), fundDivisions.get(2).getDividedMoney());
        assertEquals(new BigDecimal(666), fundDivisions.get(3).getDividedMoney());
        assertEquals(new BigDecimal(666), fundDivisions.get(4).getDividedMoney());
        assertEquals(new BigDecimal(4000), fundDivisions.get(5).getDividedMoney());
        assertEquals(new BigDecimal(0.2000).setScale(4, BigDecimal.ROUND_HALF_DOWN), fundDivisions.get(0).getDividedPercent());
        assertEquals(new BigDecimal(0.2000).setScale(4, BigDecimal.ROUND_HALF_DOWN), fundDivisions.get(1).getDividedPercent());
        assertEquals(new BigDecimal(0.0668).setScale(4, BigDecimal.ROUND_HALF_DOWN), fundDivisions.get(2).getDividedPercent());
        assertEquals(new BigDecimal(0.0666).setScale(4, BigDecimal.ROUND_HALF_DOWN), fundDivisions.get(3).getDividedPercent());
        assertEquals(new BigDecimal(0.0666).setScale(4, BigDecimal.ROUND_HALF_DOWN), fundDivisions.get(4).getDividedPercent());
        assertEquals(new BigDecimal(0.4000).setScale(4, BigDecimal.ROUND_HALF_DOWN), fundDivisions.get(5).getDividedPercent());
        BigDecimal actualMoneySum = BigDecimal.ZERO;
        for (FundDivision fundDivision : fundDivisions) {
            actualMoneySum = actualMoneySum.add(fundDivision.getDividedMoney());
        }
        assertEquals(investmentMoney, actualMoneySum);
    }
}