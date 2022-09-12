import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Введите число: ");
        int num = in.nextInt();

        boolean b = true;
        int i = 0;

        if(num > 1){
            for(i = 2; i < num; ++i){
                if(num % i == 0) {
                    b = false;
                    break;
                } else if (i == Math.round(Math.sqrt(num))) {
                    break;
                }
            }
        }
        else{
            System.err.format("%d\n%s", num, "Illegal Argument");
            System.exit(-1);
        }

        System.out.format("%d\n%b %d", num, b, i - 1);
    }
}
