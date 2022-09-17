public class Program {
    public static void main(String[] args) {
        Menu menu = new Menu();
        if(args.length == 0){
            System.err.println("None args");
            System.exit(-1);
        }
        else if(args[0].equals("--profile=dev")){
            menu.setDevMenu(true);
        }
        menu.startMenu();


//        User p1 = new User("Angle", 3600);
//        User p2 = new User("Bob", 1000);
//        User p3 = new User("Franke", -200);
//
//        TransactionsService service = new TransactionsService();
//        service.addUser(p1);
//        service.addUser(p2);
//        service.addUser(p3);
//
//        System.out.println("\nПользователи до транзакции");
//        System.out.println(p1);
//        System.out.println(p2);
//        System.out.println(p3);
//
//        service.transferTransaction(p1.getID(), p2.getID(), 1000);
//        service.transferTransaction(p1.getID(), p3.getID(), 300);
//
//        System.out.println("\nБаланс пользователей после одной транзакции");
//        System.out.println(service.retrieveUserBalance(p1));
//        System.out.println(service.retrieveUserBalance(p2));
//        System.out.println(service.retrieveUserBalance(p3));
//        System.out.println("\nПереводы пользователя");
//        service.retrieveUserTransaction(p1).print();
//        service.retrieveUserTransaction(p2).print();
//        service.retrieveUserTransaction(p3).print();
//
//        System.out.println("\nУдаление транзакции...");
//        int transactionIndex = 1;
//        service.removeTransactionFromUser(p1.getID(), service.retrieveUserTransaction(p1).toArray()[transactionIndex].getUID());
//        transactionIndex = 0;
//        service.removeTransactionFromUser(p3.getID(), service.retrieveUserTransaction(p3).toArray()[transactionIndex].getUID());
//
//        System.out.println("\nПереводы пользователя после удаления транзакции по ID");
//        service.retrieveUserTransaction(p1).print();
//        service.retrieveUserTransaction(p2).print();
//        service.retrieveUserTransaction(p3).print();
//
//        System.out.println("\nНепарные переходы");
//        printTransactionArray(service.checkValidityTransactions());
    }
}
