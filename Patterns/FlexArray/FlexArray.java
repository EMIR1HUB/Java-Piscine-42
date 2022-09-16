public class FlexArray {
    private String[] dataStr = new String[10];
    private int count = 0;

    public void addUser(String newStr) {     // способ гибкого массива
        if (count >= dataStr.length) {
            String[] temp = new String[dataStr.length * 2];     // увеличиваем размер массива в 2 раза
            for (int i = 0; i < dataStr.length; i++) {
                temp[i] = dataStr[i];
            }
            dataStr = temp;
        }
        dataStr[count++] = newStr;
    }
}
