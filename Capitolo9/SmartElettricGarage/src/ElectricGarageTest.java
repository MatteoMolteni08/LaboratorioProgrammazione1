import static org.junit.jupiter.api.Assertions.*;

class ElectricGarageTest {
    TeslaModelS t = new TeslaModelS("S", 50);
    ElectricTruck e = new ElectricTruck("Truck", 90, 20);

    @Test
    void shouldIncreaseBatteryLevelWhenAmountIsPositive(){
        int expectedResult = 60;
        System.out.println(t.charge(10));
    }
}