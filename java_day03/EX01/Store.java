public class Store {
    public synchronized void put() {
        try {
            notify();
            wait();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
