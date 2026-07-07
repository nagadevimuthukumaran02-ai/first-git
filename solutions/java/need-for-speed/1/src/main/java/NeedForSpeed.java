public class NeedForSpeed {

    private int speed;
    private int batteryDrain;
    private int battery = 100;
    private int distance = 0;

    public NeedForSpeed(int speed, int batteryDrain) {
        this.speed = speed;
        this.batteryDrain = batteryDrain;
    }

    public void drive() {
        if (!batteryDrained()) {
            distance += speed;
            battery -= batteryDrain;
        }
    }

    public int distanceDriven() {
        return distance;
    }

    public boolean batteryDrained() {
        return battery < batteryDrain;
    }

    public static NeedForSpeed nitro() {
        return new NeedForSpeed(50, 4);
    }

    public int getSpeed() {
        return speed;
    }

    public int getBatteryDrain() {
        return batteryDrain;
    }
}

class RaceTrack {

    private int distance;

    public RaceTrack(int distance) {
        this.distance = distance;
    }

    public boolean canFinishRace(NeedForSpeed car) {
        int maxDrives = 100 / car.getBatteryDrain();
        int maxDistance = maxDrives * car.getSpeed();
        return maxDistance >= distance;
    }
}