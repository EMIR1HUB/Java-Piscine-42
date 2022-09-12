public class Program {
    public static void main(String[] args) {
        User p1 = new User("Mike", "OUTCOME", 2600);
        User p2 = new User("John", "INCOME", 1000);
        User p3 = new User("Nikol","INCOME", 1500);

        p1.printConsole();
        p2.printConsole();
        p3.printConsole();
    }
}
