public class Hen implements Runnable {
    Store store;
    private static int count;

    public Hen(int count, Store store) {
        Hen.count = count;
        this.store = store;
    }

    @Override
    public void run() {
        for (int i = 0; i < count; i++) {
            System.out.println("Hen");
            store.put();
        }
    }
}
