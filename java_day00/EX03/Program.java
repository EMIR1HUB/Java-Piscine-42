
import java.util.Scanner;

public class Program {
    private static final String WEEK_TITLE = "Week ";
    private static final String EOF = "42";
    private static final int WEEKS = 18;
    private static int gradeStorage = 0;

    private static void display(int length) {
        while (length > 0) {
            System.out.print("=");
            --length;
        }
        System.out.println(">");
    }

    private static int unzipStorage() {
        int result = (int) (gradeStorage % 9 + 1);
        gradeStorage /= 9;
        return result;
    }

    private static void createStorage(int grade, int position) {
        gradeStorage += (grade - 1) * Math.pow(9, position);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String weekTitle;
        int weekCount = 0;

        while (weekCount < WEEKS) {
            weekTitle = in.nextLine();

            if (weekTitle.equals(EOF))
                break;
            if (!weekTitle.equals(WEEK_TITLE + (weekCount + 1))) {
                System.err.println("IllegalArgument");
                System.exit(-1);
            }

            int grade, minGrade = 9;
            for (int i = 0; i < 5; ++i) {
                grade = in.nextInt();
                if (grade < 1 || grade > 9) {
                    System.err.println("IllegalArgument");
                    System.exit(-1);
                }
                if (grade < minGrade) {
                    minGrade = grade;
                }
            }
            createStorage(minGrade, weekCount);
            weekCount++;
            in.nextLine();
        }
        for (int i = 1; i <= weekCount; ++i) {
            System.out.print(WEEK_TITLE + (i) + " ");
            display(unzipStorage());
        }

        System.exit(0);
    }
}
