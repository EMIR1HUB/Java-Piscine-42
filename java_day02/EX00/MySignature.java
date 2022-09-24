import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MySignature {

    private static final HashMap<String, String> mapSignatures = new HashMap<>();
    private static String extension;

    public MySignature() {
    }

    public static void extensionWrite(OutputStream outputStream){
        try {
            outputStream.write(extension.getBytes(), 0, extension.length());
            outputStream.write('\n');
        }
        catch (java.io.IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void createFromSignatureFile(BufferedReader bufferReader) {
        String stringInput;
        try {
            while ((stringInput = bufferReader.readLine()) != null) {
                String[] arrString = stringInput.split(", ");
                mapSignatures.put(arrString[0], arrString[1]);
            }
            bufferReader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean checkSignature(BufferedInputStream inputBuffer){
        ArrayList<Integer> arrayHexBytes = new ArrayList<>();
        int countByte = 0;
        try {
            int i = 0;
            while ((i = inputBuffer.read()) != -1 && countByte < 8){
                arrayHexBytes.add(i);
                countByte++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return compareMupValueWithArrayHex(arrayHexBytes);
    }

    private static boolean compareMupValueWithArrayHex(ArrayList<Integer> array){
        boolean flag = false;
        for(Map.Entry<String, String> item: mapSignatures.entrySet()){
            String[] elementsSignature = item.getValue().split(" ");
            for(int i = 0; i < elementsSignature.length; i++){
                flag = Integer.parseInt(elementsSignature[i], 16) == array.get(i);
            }
            if(flag){
                extension = item.getKey();
                break;
            }
        }
        return flag;
    }
}
