public class User {
    private Integer ID;
    private String name;
    private int balance;
    private String transferType;

    public User(String name, int balance) {
        this.ID = UserIdsGenerator.getInstance().generateId();
        this.name = name;
        this.balance = balance;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public void setBalance(int balance) {
        if (balance >= 0) {
            this.balance = balance;
        }
    }
    public int getBalance() {
        return balance;
    }

    public void setTransferType(String transferType) {
        this.transferType = transferType;
    }
    public String getTransferType() {
        return transferType;
    }

    public void printConsole() {
        System.out.format("\nID: %d\nName: %s\nBalance: %d\n", ID, name, balance);
    }

    @Override
    public String toString(){
        return "\nID: " + ID +
                "\nName: " + name +
                "\nBalance: " + balance;
    }
}
