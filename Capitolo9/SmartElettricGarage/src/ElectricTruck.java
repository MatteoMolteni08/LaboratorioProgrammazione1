public class ElectricTruck extends ElectricVehicle{
    private int cargoWeight;

    public ElectricTruck(String model, int batteryLevel, int cargoWeight) {
        super(model, batteryLevel);
        this.cargoWeight = cargoWeight;
    }

    @Override
    public String drive() {
        return "Heavy transport moving "+ cargoWeight +" kg...";
    }
}
