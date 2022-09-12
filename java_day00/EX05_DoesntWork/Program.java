
import java.util.Scanner;

// НЕДОЛЕНАННЫЙ
public class Program {
/*    private static final int LESSON_IN_WEEK = 10;
    private static final int MAX_LEN_NAME = 10;*/

    private static final int LIMIT = 10;

    private static String[] insertChar(Scanner input) {
        String str = "";
        String[] temp = new String[LIMIT];
        for (int i = 0; i < LIMIT; ++i) {
            str = input.nextLine();
            if (str.length() < LIMIT + 1) {
                if (!str.equals(".")) {
                    temp[i] = str;
                } else break;
            } else {
                System.err.println("Максимальная длина 10");
                i -= 1;
                continue;
            }
        }
        return temp;
    }

    private static String[] insertAttend(Scanner input) {
        String str = "";
        String[] temp = new String[100];
        for (int i = 0; i < 100; ++i) {
            str = input.nextLine();
            if (!str.equals(".")) {
                temp[i] = str;
            } else break;
        }
        return temp;
    }

    private static String[] insertSeptember() {
        String[] month = new String[30];
        String[] week = new String[]{"MO", "TU", "WE", "TH", "FR", "SA", "SU"};
        int j = 1;                  // начинаем с 1, так как в 2020 сент. начинался со вторника

        for (int i = 0; i < month.length; i++) {
            if (j < 7) {
                month[i] = week[j];
                ++j;
            } else {
                j = 0;
                i -= 1;
            }
        }
        return month;
    }

    private static void printTimetable(String[] listStudent, String[] timeTable, String[] attendance) {
        String[] month = insertSeptember();
//        for (int i = 0; i < month.length; ++i) {
//            System.out.println(i + 1 + " " + month[i]);
//        }


    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

//        String[] listStudent = new String[]{"John", "Mike"};
//        String[] timeTable = new String[]{"2 MO", "4 WE"};
//        String[] attendance = new String[]{"Mike 2 28 NOT_HERE", "John 4 9 HERE", "Mike 4 9 HERE"};

        String[] listStudent = insertChar(input);
        String[] timeTable = insertChar(input);
        String[] attendance = insertAttend(input);

        printTimetable(listStudent, timeTable, attendance);


        System.err.println("Выход");

    }
}
