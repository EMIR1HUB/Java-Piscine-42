import java.util.Scanner;

public class Program {
    private static final int EOF = 42;
    private static int coffeAmount = 0;

    public static void PrimeNumber(int prinum) {

        if (prinum > 1) {
            for (int i = 2; i < prinum; ++i) {
                if (prinum % i == 0) return;
            }
            coffeAmount++;
        } else {
            System.err.format("%d\n%s", prinum, "Illegal Argument");
            System.exit(-1);
        }
    }

    public static void SumNumber(int num) {
        int n = String.valueOf(num).length();    // преобразовываем int в string и находим его длину
        int suma = 0;

        for (int i = 0; i < n; ++i) {
            suma += num % 10;
            num /= 10;
        }
        PrimeNumber(suma);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int num;
        System.out.println("Введите числа: ");

        do {
            num = in.nextInt();
            SumNumber(num);
        } while (num != EOF);

        System.out.println("Count of coffee-request – " + coffeAmount);
        System.exit(0);
    }
}
