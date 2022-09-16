import java.util.UUID;

public class Transaction {
    private final UUID ID;
    private User sender;
    private User recipient;
    private int transferAmount;

    public Transaction(User sender, User recipient, int transferAmount) {
        ID = UUID.randomUUID();
        this.sender = sender;
        this.recipient = recipient;
        this.transferAmount = transferAmount;
    }

    public UUID getUID() {
        return ID;
    }

    public User getRecipient() {
        return recipient;
    }

    public User getSender() {
        return sender;
    }

    public int getTransferAmount() {
        return transferAmount;
    }

    public void doTransaction() {
        if (transferAmount >= 0) {
            if (sender.getBalance() < transferAmount) {
                throw new UserNotTransferAmountException();
            }
            sender.setTransferType("OUTCOME");
            recipient.setTransferType("INCOME");
        } else {
            if (Math.abs(recipient.getBalance()) < Math.abs(transferAmount) || recipient.getBalance() < Math.abs(transferAmount)) {
                throw new UserNotTransferAmountException();
            }
            sender.setTransferType("INCOME");
            recipient.setTransferType("OUTCOME");
        }

        sender.setBalance(sender.getBalance() - transferAmount);
        recipient.setBalance(recipient.getBalance() + transferAmount);

        sender.getTransactionsLinkedList().addTransaction(this);
        recipient.getTransactionsLinkedList().addTransaction(this);
    }

    @Override
    public String toString() {
        return "\n==================\nID: " + getUID() +
                "\nSender: " + getSender().getName() + " " + -1 * getTransferAmount() + " " + getSender().getTransferType() +
                "\nRecipient: " + getRecipient().getName() + " " + getTransferAmount() + " " + getRecipient().getTransferType() +
//                "\n\n||SENDER||\n" + "ID: " + sender.getID() + "\nName: " + sender.getName() + "\nBalance: " + sender.getBalance() +
//                "\n\n||RECIPIENT||\n" + "ID: " + recipient.getID() + "\nName: " + recipient.getName() + "\nBalance: " + recipient.getBalance() +
                "\n==================\n";
    }
}
