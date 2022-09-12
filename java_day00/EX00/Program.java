

public class Program {
    public static void main(String[] args) {
        int number = 479598;
        int n = String.valueOf(number).length();    // преобразовываем int в string и находим его длину
        int suma = 0;

        for (int i = 0; i < n; ++i) {
            suma += number % 10;
            number /= 10;
        }

        System.out.println(suma);
    }
}
