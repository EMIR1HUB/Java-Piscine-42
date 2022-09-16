public class Program {
    public static void main(String... args) {
        GPSPower power = new GPSPower();
        GPSNotifier notifier = new GPSNotifier();
        RoadAdvisor advisor = new RoadAdvisor();

        GPSInterface gps = new GPSInterface(power, notifier, advisor);

        //Водитель включает навигационную систему
        gps.activate();

        //Водитель выключает навигационную систему
        power.powerOff();
    }
}
