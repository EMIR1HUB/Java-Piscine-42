public class Program {
    public static void main(String[] args) {
        User p1 = new User("Mike", 2600);
        User p2 = new User("John", 1000);
        User p3 = new User("Nikol", -200);

        System.out.println(p1);
        System.out.println(p2);
        System.out.println(p3);

        System.out.println("\n||Выполнение транзакций||");
        Transaction t1 = new Transaction(p1, p2, 1000);
        t1.doTransaction();
        System.out.println(t1);
        Transaction t2 = new Transaction(p1, p2, -500);
        t2.doTransaction();
        System.out.println(t2);
        Transaction t3 = new Transaction(p2, p3, 200);
        t3.doTransaction();
        System.out.println(t3);
        Transaction t4 = new Transaction(p2, p3, 400);
        t4.doTransaction();
        System.out.println(t4);

//        Добавление транзакций в двусвязанный список
        TransactionsLinkedList tl = new TransactionsLinkedList();
        tl.addTransaction(t1);
        tl.addTransaction(t2);
        tl.addTransaction(t3);
        tl.addTransaction(t4);
        System.out.println("\nУдаление Транзакции ID: " + t2.getUID());
        tl.removeTransaction(t2.getUID());

        System.out.println("\n||Вывод транзакций||");
        tl.print();

//        Вывод списка транзакций определенного пользователя
//        p1.getTransactionsLinkedList().print();


//        UsersArrayList u1 = new UsersArrayList();
//        u1.addUser(p1);
//        u1.addUser(p3);
//        u1.addUser(p2);
//        System.out.println(u1.findUserID(0));
//        System.out.println(u1.findUsertIndex(2));
//        System.out.println(u1.getAmountUser());
    }
}
