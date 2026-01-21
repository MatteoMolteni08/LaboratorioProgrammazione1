public class ChargingStation {
    private String stationId;
    private int powerOutput;

    public ChargingStation(String stationId, int powerOutput) {
        this.stationId = stationId;
        this.powerOutput = powerOutput;
    }

    public String getStationId() {
        return stationId;
    }
    public void setStationId(String stationId) {
        this.stationId = stationId;
    }
    public int getPowerOutput() {
        return powerOutput;
    }
    public void setPowerOutput(int powerOutput) {
        this.powerOutput = powerOutput;
    }

    public String getDetails(){
        return "Station ["+ stationId + "] ([" + powerOutput +"] kW)"
    }
}
