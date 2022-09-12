public class User {
    private Integer ID;
    private String name;
    private String transferType;
    private int balance;

    public User(String name, String transferType, int balance){
        this.ID = UserIdsGenerator.getInstance().generateId();
        this.name = name;
        this.transferType = transferType;
        this.balance = balance;
    }
//    public String getSign() {
//        if(transferType.equals("INCOME")) {
//            return "+";
//        }
//        return "-";
//    }

    public void printConsole(){
        System.out.format("\nID: %d\nName: %s\nBalance: %d\n", ID, name, balance);
    }
}
