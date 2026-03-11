package test.java.forme;

import main.java.forme.Cerchio;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CerchioTest {

    Cerchio c = new Cerchio(5.0);
    double expectedResultForArea = 25 * Math.PI;
    double expectedResultForPerimetro = 10 * Math.PI;

    @Test
    public void shouldReturnCorrectArea() {
        double actualResultForArea = c.area();
        assertEquals(actualResultForArea, expectedResultForArea, 0.001);
    }

    @Test
    public void shouldReturnCorrectPerimetro() {
        double actualResultForPerimetro = c.perimetro();
        assertEquals(actualResultForPerimetro, expectedResultForPerimetro, 0.001);
    }
}