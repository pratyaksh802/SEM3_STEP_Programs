import java.util.*;

public class TextCompression {
    // Count frequencies
    static void countFrequency(String text, char[] chars, int[] freq) {
        int n = 0;
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            boolean found = false;
            for (int j = 0; j < n; j++) {
                if (chars[j] == c) {
                    freq[j]++;
                    found = true;
                    break;
                }
            }
            if (!found) {
                chars[n] = c;
                freq[n] = 1;
                n++;
            }
        }
    }

    // Build simple codes (shorter for frequent)
    static String[][] buildCodes(char[] chars, int[] freq, int size) {
        String[][] map = new String[size][2];
        for (int i = 0; i < size; i++) {
            map[i][0] = Character.toString(chars[i]);
            map[i][1] = Integer.toString(i, 36); // base36 for compact
        }
        return map;
    }

    static String compress(String text, String[][] map) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            for (String[] entry : map) {
                if (entry[0].charAt(0) == c) {
                    sb.append(entry[1]).append(" ");
                }
            }
        }
        return sb.toString().trim();
    }

    static String decompress(String comp, String[][] map) {
        StringBuilder sb = new StringBuilder();
        String[] tokens = comp.split(" ");
        for (String t : tokens) {
            for (String[] entry : map) {
                if (entry[1].equals(t)) sb.append(entry[0]);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter text to compress: ");
        String text = sc.nextLine();

        char[] chars = new char[text.length()];
        int[] freq = new int[text.length()];
        countFrequency(text, chars, freq);

        int uniqueCount = 0;
        for (int f : freq) if (f > 0) uniqueCount++;

        String[][] map = buildCodes(chars, freq, uniqueCount);
        String comp = compress(text, map);
        String decomp = decompress(comp, map);

        System.out.println("\nCharacter Frequency:");
        for (int i = 0; i < uniqueCount; i++) {
            System.out.println(chars[i] + " : " + freq[i]);
        }

        System.out.println("\nMapping:");
        for (String[] entry : map) {
            System.out.println(entry[0] + " -> " + entry[1]);
        }

        System.out.println("\nOriginal: " + text);
        System.out.println("Compressed: " + comp);
        System.out.println("Decompressed: " + decomp);

        double ratio = (1.0 * comp.length() / text.length()) * 100;
        System.out.printf("Compression Efficiency: %.2f%%%n", 100 - ratio);
    }
}
