import java.util.UUID;

public class TransactionsService {
    private UsersList userDataBase;

    TransactionsService() {
        userDataBase = new UsersArrayList();
    }

    public void addUser(User user) {
        userDataBase.addUser(user);
    }

    public User retrieveUserBalance(User user) {
        User userData = userDataBase.findUserID(user.getID());
        return userData;
    }

    public TransactionsLinkedList retrieveUserTransaction(User user) {
        User userData = userDataBase.findUserID(user.getID());
        TransactionsLinkedList arr = userData.getTransactionsLinkedList();
        return arr;
    }

    public void removeTransactionFromUser(int userID, UUID transactionID) {
        User user = userDataBase.findUserID(userID);
        user.getTransactionsLinkedList().removeTransaction(transactionID);
    }

    public void transferTransaction(int senderID, int recipientID, int transferAmount) {
        User sender = userDataBase.findUserID(senderID);
        if (sender.getBalance() < transferAmount) {
            throw new IllegalTransactionException();
        }
        User recipient = userDataBase.findUserID(recipientID);
        Transaction transaction = new Transaction(sender, recipient, transferAmount);
        transaction.doTransaction();
    }

    public Transaction[] checkValidityTransactions() {
        int unpairedTransactionsCount = 0;
        for (int count = 0; count < userDataBase.getAmountUser(); count++) {
            unpairedTransactionsCount += countUnpairedTransactions(userDataBase.findUserIndex(count));
        }
        Transaction[] unpairedTransactionsArray = new Transaction[unpairedTransactionsCount];
        int position = 0;
        for (int count = 0; count < userDataBase.getAmountUser(); count++) {
            position = addTransactionsUserToArrayPosition(userDataBase.findUserIndex(count),
                    unpairedTransactionsArray, position);
        }
        return unpairedTransactionsArray;
    }

    private boolean checkUnpairedTransactionArray(User user, Transaction[] userTransactionArray, int userTransactionsCounter){
        Transaction[] partnerTransactionArray;
        if (userTransactionArray[userTransactionsCounter].getRecipient().getID() == user.getID()) {
            partnerTransactionArray = userDataBase.findUserID(userTransactionArray[userTransactionsCounter].getSender().getID())
                    .getTransactionsLinkedList().toArray();
        } else {
            partnerTransactionArray = userDataBase.findUserID(userTransactionArray[userTransactionsCounter].getRecipient().getID())
                    .getTransactionsLinkedList().toArray();
        }
        boolean notFound = true;
        for (int partnerCounter = 0; partnerCounter < partnerTransactionArray.length; ++partnerCounter) {
            if (userTransactionArray[userTransactionsCounter].getUID()
                    == partnerTransactionArray[partnerCounter].getUID()) {
                notFound = false;
                break;
            }
        }
        return notFound;
    }

    private int addTransactionsUserToArrayPosition(User user, Transaction[] transactions, int position) {
        Transaction[] userTransactionArray = user.getTransactionsLinkedList().toArray();
        for (int userTransactionsCounter = 0; userTransactionsCounter < userTransactionArray.length; ++userTransactionsCounter) {
            boolean notFound = checkUnpairedTransactionArray(user, userTransactionArray, userTransactionsCounter);
            if (notFound) {
                transactions[position] = userTransactionArray[userTransactionsCounter];
                position++;
            }
        }
        return position;
    }

    private int countUnpairedTransactions(User user) {
        int count = 0;
        Transaction[] userTransactionArray = user.getTransactionsLinkedList().toArray();
        for (int userTransactionsCounter = 0; userTransactionsCounter < userTransactionArray.length; ++userTransactionsCounter) {
            boolean notFound = checkUnpairedTransactionArray(user, userTransactionArray, userTransactionsCounter);
            if (notFound) {
                count++;
            }
        }
        return count;
    }
}