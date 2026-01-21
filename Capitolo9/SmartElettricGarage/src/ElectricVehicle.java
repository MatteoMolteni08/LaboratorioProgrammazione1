public abstract class ElectricVehicle {
    private String model;
    private int batteryLevel;

    public ElectricVehicle(String model, int batteryLevel) {
        this.model = model;
        this.batteryLevel = batteryLevel;
    }

    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public int getBatteryLevel() {
        return batteryLevel;
    }
    public void setBatteryLevel(int batteryLevel) {
        this.batteryLevel = batteryLevel;
    }

    public int charge(int amount) {
        if (amount < 0) {
            return batteryLevel;
        }else if (batteryLevel + amount > 100) {
            batteryLevel = 100;
        }else {
            batteryLevel += amount;
        }
        return batteryLevel;
    }

    public abstract String drive();
}
