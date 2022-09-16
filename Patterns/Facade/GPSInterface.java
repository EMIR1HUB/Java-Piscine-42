class GPSPower {
    public void powerOn() {
        System.out.println("Power ON");
    }

    public void powerOff() {
        System.out.println("Power OFF");
    }
}

class GPSNotifier {
    public void downloadRoadInfo() {
        System.out.println("Downloading road information...");
        System.out.println("Download complete!");
    }
}

class RoadAdvisor {
    public void route() {
        System.out.println("Create a route");
    }
}

class GPSInterface {
    private GPSPower power;
    private GPSNotifier notifier;
    private RoadAdvisor advisor;

    public GPSInterface(GPSPower power, GPSNotifier notifier, RoadAdvisor advisor) {
        this.power = power;
        this.notifier = notifier;
        this.advisor = advisor;
    }

    public void activate() {
        power.powerOn();
        notifier.downloadRoadInfo();
        advisor.route();
    }
}