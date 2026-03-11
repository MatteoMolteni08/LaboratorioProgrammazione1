package test.java.forme;

import main.java.forme.Rettangolo;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class RettangoloTest {
    @ParameterizedTest
    @CsvSource({"3.0, 4.0, 12.0", "5.0, 5.0, 25.0", "1.0, 10.0, 10.0", "2.5, 4.0, 10.0"})
    public void ShouldReturnCorrectArea(double base, double altezza, double expectedArea){
        Rettangolo r = new Rettangolo(base, altezza);
        double area = r.area();

        assertEquals(area, expectedArea, 0.001);
    }
}