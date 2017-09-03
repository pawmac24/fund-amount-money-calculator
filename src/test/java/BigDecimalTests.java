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
}
