public class TeslaModelS extends ElectricVehicle implements Autonomus{
    public TeslaModelS(String model, int batteryLevel) {
        super(model, batteryLevel);
    }

    @Override
    public String drive() {
        return "Silent acceleration...";
    }

    @Override
    public boolean canActivateAutoPilot() {
        if (getBatteryLevel() > MIN_BATTERY_FOR_AUTOPILOT){
            return true;
        }else {
            return false;
        }
    }
}
