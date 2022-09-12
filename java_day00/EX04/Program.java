
import java.util.Scanner;

public class Program {
    private static final int MAX_CHAR_CODES = 65535;
    private static final byte MAX_HIGH = 10;

    private static int[] getChar(String input) {
        int[] temp = new int[MAX_CHAR_CODES];
        char[] inputAsArray = input.toCharArray();

        for (int i = 0; i < input.length(); i++) {
            temp[inputAsArray[i]]++;    // ASCII:  temp[65 = 'A'] += 1
        }
        return temp;
    }

    private static char[] getTopTenChars(int[] parsedInput) {
        char[] temp = new char[MAX_HIGH];

        for (int i = 0; i < MAX_CHAR_CODES; i++) {
            int iCharCount = parsedInput[i];
            if (iCharCount > 0) {
                for (int j = 0; j < MAX_HIGH; j++) {
                    if (parsedInput[temp[j]] < iCharCount) {
                        temp = insertCharAt(temp, (char) i, j);
                        break;
                    }
                }
            }
        }
        return (temp);
    }

    private static char[] insertCharAt(char[] base, char c, int index) {
        char[] temp = new char[MAX_HIGH];
        for (int i = 0; i < index; i++) {
            temp[i] = base[i];
        }
        temp[index] = c;
        for (int i = index + 1; i < MAX_HIGH; i++) {
            temp[i] = base[i - 1];
        }
        return temp;
    }

    private static void printGraph(char[] topTenChars, int[] charCount) {
        int max = charCount[topTenChars[0]];
        byte maxHeight = (byte) (max <= 10 ? max : 10);
        byte totalLines = (byte) (2 + maxHeight);
        byte[] graphs = new byte[MAX_HIGH];

        for (int i = 0; i < MAX_HIGH; i++) {
            if (max <= 10) {
                graphs[i] = (byte) charCount[topTenChars[i]];
            } else {
                graphs[i] = (byte) (charCount[topTenChars[i]] * 10 / max);
            }
        }
        System.out.println();
        for (int i = 0; i < totalLines; i++) {
            for (int j = 0; j < MAX_HIGH; j++) {
                if (topTenChars[j] != 0) {
                    if (i + graphs[j] + 2 == totalLines) {
                        System.out.format("%3d", charCount[topTenChars[j]]);
                    } else if (i == totalLines - 1) {
                        System.out.format("%3c", topTenChars[j]);
                    } else if (i + graphs[j] >= maxHeight) {
                        System.out.format("%3c", '#');
                    }
                    if (j != MAX_HIGH - 1 && topTenChars[j + 1] != 0 && i + graphs[j + 1] >= maxHeight) {
                        System.out.format("%c", ' ');
                    }
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str = in.next();
//        String str = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAASSSSSSSSSSSSSSSSSSSSSSSSDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDWEWWKFKKDKKDSKAKLSLDKSKALLLLLLLLLLRTRTETWTWWWWWWWWWWOOOOOOO42";
        int[] charCount = getChar(str);
        char[] topTenChar = getTopTenChars(charCount);
        printGraph(topTenChar, charCount);

        System.exit(0);
    }
}

