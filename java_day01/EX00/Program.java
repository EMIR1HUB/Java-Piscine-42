
public class Program {
    public static void main(String[] args) {
        User sender = new User();
        sender.setID(0);
        sender.setName("Mike");
        sender.setBalance(2300);
        sender.printConsole();

        User recipient = new User();
        recipient.setID(1);
        recipient.setName("John");
        recipient.setBalance(1000);
        recipient.printConsole();

        Transaction transaction = new Transaction();
        transaction.setSender(sender);
        transaction.setRecipient(recipient);
        transaction.setTransferAmount(200);
        transaction.printConsole();






    }
}
