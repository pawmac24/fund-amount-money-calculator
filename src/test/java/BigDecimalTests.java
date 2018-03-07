import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class BigDecimalTests {

    @Test
    public void testDivideBigDecimal() {
        BigDecimal numerator = new BigDecimal(29.57);
        BigDecimal denominator = new BigDecimal(100);
        BigDecimal result = numerator
                .divide(denominator, 4, BigDecimal.ROUND_HALF_DOWN)
                .setScale(4, BigDecimal.ROUND_HALF_DOWN);
        BigDecimal expectedResult = new BigDecimal(0.2957).setScale(4, BigDecimal.ROUND_HALF_DOWN);
        assertEquals(expectedResult, result);
    }

    @Test
    public void testDivideBigDecimal2() {
        double numerator = 29.57;
        double denominator = 100;
        BigDecimal result = new BigDecimal(numerator / denominator).setScale(4, BigDecimal.ROUND_HALF_DOWN);
        BigDecimal expectedResult = new BigDecimal(0.2957).setScale(4, BigDecimal.ROUND_HALF_DOWN);
        assertEquals(expectedResult, result);
    }

    @Test
    public void testCalulateDivision(){
        BigDecimal investmentMoney = new BigDecimal(10001);
        BigDecimal investmentPercent = new BigDecimal(0.20);
        BigDecimal count = new BigDecimal(2);

        System.out.println("1- " + investmentMoney);
        BigDecimal moneyMultipliedByPercent = investmentPercent.multiply(investmentMoney).setScale(2, BigDecimal.ROUND_HALF_DOWN);
        System.out.println("2- " + moneyMultipliedByPercent);
        System.out.println("3- " + moneyMultipliedByPercent.remainder(count));
        System.out.println("4- " + moneyMultipliedByPercent.divide(count, BigDecimal.ROUND_HALF_DOWN));

        BigDecimal fractionalPart = moneyMultipliedByPercent.remainder( BigDecimal.ONE );
        System.out.println("5- " + fractionalPart);
        BigDecimal integralPart = moneyMultipliedByPercent.subtract(fractionalPart);
        System.out.println("6- " + integralPart);

        BigDecimal moneyCorrected = integralPart.divide(investmentPercent, BigDecimal.ROUND_HALF_DOWN);
        System.out.println("7- " + moneyCorrected);
        BigDecimal moneyReturn = investmentMoney.subtract(moneyCorrected);
        System.out.println("8- " + moneyReturn);
    }

    @Test
    public void testDividingPercents()
    {
        BigDecimal nominator = new BigDecimal(20);
        BigDecimal count = new BigDecimal(3);

        BigDecimal percent = nominator
                .divide(new BigDecimal(100), 4, BigDecimal.ROUND_HALF_DOWN)
                .setScale(4, BigDecimal.ROUND_HALF_DOWN);
        System.out.println(percent);
        BigDecimal divideBy = nominator
                .divide(count, 4, BigDecimal.ROUND_HALF_DOWN)
                .setScale(4, BigDecimal.ROUND_HALF_DOWN);
        System.out.println(divideBy);

        BigDecimal remainderForPercent = percent
                .multiply(new BigDecimal(10000))
                .remainder(count)
                .setScale(0, BigDecimal.ROUND_HALF_DOWN);
        System.out.println(remainderForPercent);
    }
}
