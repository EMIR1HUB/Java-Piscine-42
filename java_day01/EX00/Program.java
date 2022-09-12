
public class Program {
    public static void main(String[] args) {
        User sender = new User();
        sender.setID(0);
        sender.setName("Mike");
        sender.setTransferType("OUTCOME");
        sender.setBalance(2600);
        sender.printConsole();

        User recipient = new User();
        recipient.setID(1);
        recipient.setName("John");
        recipient.setTransferType("INCOME");
        recipient.setBalance(1000);
        recipient.printConsole();

        Transaction transaction = new Transaction();
        transaction.setSender(sender);
        transaction.setRecipient(recipient);
        transaction.setTransferAmount(500);
        transaction.printConsole();
        sender.printConsole();
        recipient.printConsole();

        transaction.setTransferAmount(5000);
        transaction.printConsole();
        sender.printConsole();
        recipient.printConsole();





    }
}
