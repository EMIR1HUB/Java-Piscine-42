public class Egg implements Runnable {
    Store store;
    private static int count;

    public Egg(int count, Store store) {
        Egg.count = count;
        this.store = store;
    }

    @Override
    public void run() {
        for (int i = 0; i < count; i++) {
            System.out.println("Egg");
            store.put();
        }
    }
}
