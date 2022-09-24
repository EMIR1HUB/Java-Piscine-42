public class Hen extends Thread {
    private static int count;

    public Hen(int count) {
        Hen.count = count;
    }

    @Override
    public void run() {
        for (int i = 0; i < count; i++) {
            System.out.println("Hen");
        }
    }
}
