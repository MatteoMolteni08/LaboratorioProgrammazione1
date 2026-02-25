
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    @Test
    void shouldReturnCorrectSumWhenTwoPositiveIntegersAreAdded() {
        Calculator calc = new Calculator();
        int firstOperand = 5;
        int secondOperand = 10;
        int expectedResult = 15;

        int actualResult = calc.sum(firstOperand, secondOperand);

        assertEquals(expectedResult, actualResult, "La somma di 5 e 10 dovrebbe essere 15");
    }
}