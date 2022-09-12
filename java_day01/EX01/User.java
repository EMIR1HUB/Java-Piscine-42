public class User {
    private Integer ID;
    private String name;
    private int balance;

    public User(String name, int balance) {
        this.ID = UserIdsGenerator.getInstance().generateId();
        this.name = name;
        this.balance = balance;
    }

    public void printConsole() {
        System.out.format("\nID: %d\nName: %s\nBalance: %d\n", ID, name, balance);
    }
}
