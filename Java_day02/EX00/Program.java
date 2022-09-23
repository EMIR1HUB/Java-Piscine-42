import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.security.Signature;
import java.util.ArrayList;


public class Program {

    private static final String SIGNATURES = "C:/рабочий стол/JAVA_Project/Java-Piscine-42/src/Java_day02/EX00/signatures.txt";
    private static final String RESULT = "result.txt";

    public static void main(String[] args) {
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(SIGNATURES);
        } catch (FileNotFoundException e) {
            System.err.println("Error: File not found");
            System.exit(-1);
        }

//        byte c;
//        while ((c = (byte) inputStream.read()) != -1){
//            System.out.format("%02X ", c);
//        }



        ArrayList<Signature> signatures = new ArrayList<>();







        try {
            inputStream.close();
        } catch (java.io.IOException e) {
            throw new RuntimeException(e);
        }

    }
}
