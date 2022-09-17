import java.util.Scanner;
import java.util.UUID;

public class Menu {
    private boolean devMenu;
    private int numberCount = 0;
    private TransactionsService service;
    private Scanner input;


    public Menu() {
        input = new Scanner(System.in);
        service = new TransactionsService();
    }

    public void setDevMenu(boolean devMenu) {
        this.devMenu = devMenu;
    }

    private void showMenu() {
        numberCount = 0;
        System.out.println(++numberCount + ". Add a user\n" +
                ++numberCount + ". View user balances\n" +
                ++numberCount + ". Perform a transfer\n" +
                ++numberCount + ". View all transactions for a specific user");
        if (devMenu) {
            System.out.println(++numberCount + ". DEV – remove a transfer by ID\n" +
                    ++numberCount + ". DEV – check transfer validity");
        }
        System.out.print(++numberCount + ". Finish execution" + "\n-> ");
    }

    private void printTransactionArray(Transaction[] transactionsArray, int userID){
        if (transactionsArray.length == 0)
            System.out.println("Empty Array\n");
        String str;
        for(int count = 0; count < transactionsArray.length; count++){
            if(transactionsArray[count].getSender().getID() == userID){
                str = "To " + transactionsArray[count].getSender().getName() + "(id = " + transactionsArray[count].getSender().getID() +
                        ") " + -1 * transactionsArray[count].getTransferAmount() + " " + transactionsArray[count].getSender().getTransferType() +
                        " id = " + transactionsArray[count].getUID();
            }
            else {
                str = "To " + transactionsArray[count].getRecipient().getName() + "(id = " + transactionsArray[count].getRecipient().getID() +
                        ") " + transactionsArray[count].getTransferAmount() + " " + transactionsArray[count].getRecipient().getTransferType() +
                        " id = " + transactionsArray[count].getUID();
            }
            System.out.println(str);
        }
    }

    private void checkTransferValidity(){
        System.out.print("Check results:\n-> ");
        Transaction [] unpairedTransactions = service.checkValidityTransactions();
        String string;
        User first;
        User second;
        for (int count = 0; count < unpairedTransactions.length; count++) {
            String direction;
            if (unpairedTransactions[count].getSender().getTransferType().equals("OUTCOME")) {
                first = service.getUserDataBase().findUserID(unpairedTransactions[count].getSender().getID());
                second = service.getUserDataBase().findUserID(unpairedTransactions[count].getRecipient().getID());
                direction = " to ";
            } else {
                first = service.getUserDataBase().findUserID(unpairedTransactions[count].getRecipient().getID());
                second = service.getUserDataBase().findUserID(unpairedTransactions[count].getSender().getID());
                direction = " from ";
            }
            string = first.getName() + "(id = " + first.getID()
                    + ") has an unacknowledged transfer "
                    + unpairedTransactions[count].getSender()
                    + direction + second.getName() + "(id = " + second.getID()
                    + ") for " + unpairedTransactions[count].getTransferAmount();
            System.out.println(string);
        }
    }

    private void removeTransferByID(){
        String temp = "";
        Integer userID = 0;
        UUID transactionID = UUID.randomUUID();
        boolean flag = false;
        input.nextLine();
        while (!flag){
            System.out.print("Enter a user ID and a transfer ID\n-> ");
            temp = input.nextLine();
            String[] args = temp.split(" ");
            if(args.length != 2){
                System.out.println("Wrong number of arguments");
                continue;
            }
            try {
                userID = Integer.parseInt(args[0]);
            }
            catch (NumberFormatException numberFormatException){
                System.out.println("User ID is not a numeric");
                continue;
            }
            try {
                transactionID = UUID.fromString(args[1]);
            } catch (IllegalArgumentException illegalArgumentException) {
                System.out.println("Transfer ID in bad format");
                continue;
            }
            if (userID < 0) {
                System.out.println("User ID is negative");
                continue;
            }
            flag = true;
        }
        try {
            Transaction transaction = service.getUserDataBase().findUserID(userID)
                    .getTransactionsLinkedList().findTransactionByID(transactionID);
            System.out.println("Transfer To " + service.getUserDataBase().findUserID(userID).getName() +
                    "(id = " + service.getUserDataBase().findUserID(userID).getID() + ") " +
                    transaction.getTransferAmount() + " removed");
            service.removeTransactionFromUser(userID,transactionID);
        }
        catch (UserNotFoundException userNotFoundException) {
            System.out.println("User is not found");
        }
        catch (TransactionNotFoundException transactionNotFoundException) {
            System.out.println("Transaction is not found");
        }
    }

    private void viewAllTransactionsForUser(){
        String temp = "";
        int userID = 0;
        input.nextLine();
        while (true){
            System.out.print("Enter a user ID\n-> ");
            temp = input.nextLine();
            String[] args = temp.split(" ");
            if(args.length != 1){
                System.out.println("Wrong number of arguments");
                continue;
            }
            try {
                userID = Integer.parseInt(args[0]);
            }
            catch (NumberFormatException numberFormatException){
                System.out.println("ID is not a numeric");
            }
            try {
                printTransactionArray(service.retrieveUserTransaction(userID), userID);
            }
            catch (UserNotFoundException userNotFoundException){
                System.out.println("User not found");
            }
            return;
        }
    }
    private void performTransfer() {
        String temp = "";
        int sender;
        int recipient;
        int transferAmount;
        input.nextLine();
        while (true) {
            System.out.print("Enter a sender ID, a recipient ID, and a transfer amount\n-> ");
            temp = input.nextLine();
            String[] args = temp.split(" ");
            if (args.length != 3) {
                System.out.println("Wrong number of arguments");
                continue;
            }
            try {
                sender = Integer.parseInt(args[0]);
                recipient = Integer.parseInt(args[1]);
                transferAmount = Integer.parseInt(args[2]);
            } catch (NumberFormatException numberFormatException) {
                System.out.println("All arguments must be numbers");
                continue;
            }
            if (sender == recipient) {
                System.out.println("User can't made transaction to himself");
                continue;
            }
            try {
                service.transferTransaction(sender, recipient, transferAmount);
            } catch (UserNotFoundException userNotFoundException) {
                System.out.println("User not found");
                return;
            } catch (IllegalTransactionException illegalTransactionException) {
                System.out.println("Not enough money");
                return;
            }
            System.out.println("The transfer is completed");
            return;
        }
    }

    private void viewUserBalances() {
        String temp = "";
        Integer ID;
        input.nextLine();
        while (true) {
            System.out.print("Enter a user ID\n-> ");
            temp = input.nextLine();
            String[] args = temp.split(" ");
            if (args.length != 1) {
                System.out.println("Wrong number of arguments");
                continue;
            }
            try {
                ID = Integer.parseInt(args[0]);
            } catch (NumberFormatException numberFormatException) {
                System.out.println("ID is not a numeric");
                continue;
            }
            try {
                System.out.print(service.getUserDataBase().findUserID(ID).getName() + " - " +
                        service.retrieveUserBalance(ID));
            } catch (UserNotFoundException userNotFoundException) {
                System.out.println("User not found");
            }
            return;
        }
    }

    private void addNewUser() {
        String temp = "";
        Integer balance = 0;
        boolean flag = false;
        input.nextLine();
        while (!flag) {
            System.out.print("Enter a user name and a balance\n-> ");
            temp = input.nextLine();
            String[] args = temp.split(" ");
            if (args.length != 2) {
                System.out.println("Wrong number of arguments");
                continue;
            }
            try {
                balance = Integer.parseInt(args[1]);
            } catch (NumberFormatException numberFormatException) {
                System.out.println("Balance is not a numeric");
                continue;
            }
            temp = args[0];
            flag = true;
        }
        User user = new User(temp, balance);
        service.addUser(user);
        System.out.format("User with id = %d is added", user.getID());
    }

    private void enterNumberOption(int menuOption) {
        int numberOption = menuOption;
        if (numberOption < 1 || numberOption > numberCount) {
            System.out.println("Enter a number 0 - " + numberCount + "\n");
            startMenu();
        }
        switch (numberOption) {
            case 1:
                addNewUser();
                break;
            case 2:
                viewUserBalances();
                break;
            case 3:
                performTransfer();
                break;
            case 4:
                viewAllTransactionsForUser();
                break;
            case 5:
                if(!devMenu){
                    System.exit(0);
                }
                else {
                    removeTransferByID();
                }
                break;
            case 6:
                checkTransferValidity();
                break;
            case 7:
                System.exit(0);
        }
    }

    public void startMenu() {
        int numberCommand;
        while (true) {
            showMenu();
            enterNumberOption(input.nextInt());
            System.out.println("\n---------------------------------------------------------");
        }
    }
}
