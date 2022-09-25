import java.util.ArrayList;
import java.util.Random;

public class Program {
    private static final int MAX_MODULO = 1000;
    public static int threadSum = 0;

    private static void displayArraySum(Integer[] arr) {
        int suma = 0;
        for (Integer integer : arr) {
            suma += integer;
        }
        System.out.println("Sum: " + suma);
    }

    private static Integer[] createArr(int size) {
        Random random = new Random();
        Integer[] arr = new Integer[size];
        int i = 0;
        while (i < size) {
            arr[i++] = random.nextInt(MAX_MODULO - 1);
        }
        return arr;
    }

    private static void checkArgs(String[] args) {
        if (args.length != 2) {
            System.err.println("Error: wrong args");
            System.exit(-1);
        }
        if (!args[0].matches("--arraySize=\\d++") || !args[1].matches("--threadsCount=\\d++")) {
            System.err.println("Error: wrong flag");
            System.exit(-1);
        }
    }

    public static void main(String[] args) {
        checkArgs(args);
        int arraySize = Integer.parseInt((args[0].split("=")[1]));
        int threadsCount = Integer.parseInt((args[1].split("=")[1]));
        if (arraySize > 2000000 || threadsCount > arraySize) {
            System.err.println("Error: big size");
            System.exit(-1);
        }

        Integer[] arr = createArr(arraySize);
        displayArraySum(arr);
        int sectionSize = arraySize / threadsCount;
        int start = 0;
        int end = sectionSize;
        ArrayList<Thread> threads = new ArrayList<>();

        for (int i = 0; i < threadsCount - 1; i++) {
            threads.add(new Thread(new ThreadSuma(i + 1, start, end, arr)));
            start += sectionSize + 1;
            end += start;
        }
        threads.add(new Thread(new ThreadSuma(threadsCount, start, arraySize - 1, arr)));
        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Sum by threads: " + threadSum);
    }
}
