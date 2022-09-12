import java.util.UUID;

public class Transaction {
    private final UUID ID;
    private User sender;
    private User recipient;
    private int transferAmount;

    Transaction() {
        ID = UUID.randomUUID();
    }

    public UUID getUID() {
        return ID;
    }

    public User getRecipient() {
        return recipient;
    }

    public void setRecipient(User recipient) {
        this.recipient = recipient;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public int getTransferAmount() {
        return transferAmount;
    }

    public void setTransferAmount(int transferAmount) {
        if (sender.getBalance() < transferAmount) {
            System.err.print("\nНе хватает средств для отправки " + transferAmount);
            System.exit(-1);
        }
        this.transferAmount = transferAmount;
    }


    public void printConsole() {
        sender.setBalance(sender.getBalance() - transferAmount);
        recipient.setBalance(recipient.getBalance() + transferAmount);
        System.out.print("\nID: " + getUID() +
                "\nSender: " + getSender().getName() + " " + getSender().getSign() + getTransferAmount() + " " + getSender().getTransferType() +
                "\nRecipient: " + getRecipient().getName() + " " + getRecipient().getSign() + getTransferAmount() + " " + getRecipient().getTransferType() + "\n");

    }
}
