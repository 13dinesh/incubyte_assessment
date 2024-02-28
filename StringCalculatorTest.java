import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class StringCalculatorTest {

    @Test
    public void testEmptyString() {
        assertEquals(0, StringCalculator.add(""));
    }

    @Test
    public void testSingleNumber() {
        assertEquals(5, StringCalculator.add("5"));
    }

    @Test
    public void testTwoNumbers() {
        assertEquals(9, StringCalculator.add("4,5"));
    }

    @Test
    public void testUnknownAmountOfNumbers() {
        assertEquals(15, StringCalculator.add("1,2,3,4,5"));
    }

    @Test
    public void testNewlineDelimiter() {
        assertEquals(6, StringCalculator.add("1\n2,3"));
    }

    @Test
    public void testCustomDelimiter() {
        assertEquals(6, StringCalculator.add("//;\n1;2;3"));
    }

    @Test
    public void testNegativeNumbers() {
        try {
            StringCalculator.add("-1,2,-3");
        } catch (IllegalArgumentException e) {
            assertEquals("Negatives not allowed: -1, -3", e.getMessage());
        }
    }

    @Test
    public void testIgnoreNumbersGreaterThan1000() {
        assertEquals(2, StringCalculator.add("2,1001"));
    }

    @Test
    public void testAnyLengthCustomDelimiter() {
        assertEquals(6, StringCalculator.add("//[***]\n1***2***3"));
    }

    @Test
    public void testMultipleCustomDelimiters() {
        assertEquals(6, StringCalculator.add("//[*][%]\n1*2%3"));
    }

    @Test
    public void testMultipleAnyLengthCustomDelimiters() {
        assertEquals(6, StringCalculator.add("//[**][%%]\n1**2%%3"));
    }
}
