public class User {
    private Integer ID;
    private String name;
    private String transferType;
    private int balance;

    public int getID(){
        return ID;
    }

    public void setID(int ID){
        this.ID = ID;
    }

    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSign() {
        if(transferType.equals("INCOME")) {
            return "+";
        }
        return "-";
    }

    public void setTransferType(String transferType) {
        if(!transferType.equals("INCOME") && !transferType.equals("OUTCOME")){
            System.err.println("Неверный код транзакции");
        }
        this.transferType = transferType;
    }

    public String getTransferType(){
        return transferType;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        if (balance >= 0) {
            this.balance = balance;
        }
    }

    public void printConsole(){
        System.out.format("\nID: %d\nName: %s\nBalance: %d\n", ID, name, balance);
    }
}
