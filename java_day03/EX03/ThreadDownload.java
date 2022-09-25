import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ThreadDownload implements Runnable {
    private final ConcurrentLinkedQueue<String> uris;
    private final Map<String, Integer> map;
    private final Integer index;

    public ThreadDownload(int index, ConcurrentLinkedQueue<String> uris, Map<String, Integer> map) {
        this.uris = uris;
        this.map = map;
        this.index = index;
    }

    @Override
    public void run() {
        while (!uris.isEmpty()) {
            String url = uris.poll();
            Integer fileNumber = map.get(url);
            String fileName = url.substring(url.lastIndexOf('/') + 1);

            System.out.printf("Thread-%d start download file number %d\n", index, fileNumber);
            try (InputStream inputStream = new URL(url).openStream()) {
                Files.copy(inputStream, Paths.get(fileName));
            } catch (FileAlreadyExistsException e) {
                System.err.println("File already exist");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.printf("Thread-%d finish download file number %d\n", index, fileNumber);
        }
    }
}
