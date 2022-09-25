import java.io.*;
import java.util.LinkedHashMap;
import java.util.concurrent.*;

public class Program {
    private static final String FILE_URLS_NAME = "src/java_day03/EX03/file_urls.txt";
    private static final LinkedHashMap<String, Integer> mapUrls = new LinkedHashMap<>();
    private static final ConcurrentLinkedQueue<String> uris = new ConcurrentLinkedQueue<>();

    private static void checkArgs(String[] args) {
        if (args.length != 1) {
            System.err.println("Error: wrong args");
            System.exit(-1);
        }
        if (!args[0].matches("--threadsCount=\\d++")) {
            System.err.println("Error: wrong flag");
            System.exit(-1);
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        checkArgs(args);
        int threadsCount = Integer.parseInt(args[0].split("=")[1]);
        File file = new File(FILE_URLS_NAME);
        ExecutorService executorService = Executors.newFixedThreadPool(threadsCount);

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String stringBuff;
            int index = 0;
            while ((stringBuff = bufferedReader.readLine()) != null) {
                mapUrls.put(stringBuff, ++index);
                uris.add(stringBuff);
            }
            for (int i = 0; i < threadsCount; i++) {
                executorService.submit(new ThreadDownload(i + 1, uris, mapUrls));
            }
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            executorService.shutdown();
        }
    }
}
