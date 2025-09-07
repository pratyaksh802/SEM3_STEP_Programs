import java.util.*;

public class Problem2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter text:");
        String text = sc.nextLine();

        String upper = toUpper(text);
        String lower = toLower(text);
        String title = toTitleCase(text);

        System.out.println("\n--- Results ---");
        System.out.printf("%-15s %-30s\n", "Original:", text);
        System.out.printf("%-15s %-30s\n", "Uppercase:", upper);
        System.out.printf("%-15s %-30s\n", "Lowercase:", lower);
        System.out.printf("%-15s %-30s\n", "Title Case:", title);
        sc.close();
    }

    public static String toUpper(String text) {
        StringBuilder sb = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (c >= 'a' && c <= 'z') sb.append((char)(c - 32));
            else sb.append(c);
        }
        return sb.toString();
    }

    public static String toLower(String text) {
        StringBuilder sb = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (c >= 'A' && c <= 'Z') sb.append((char)(c + 32));
            else sb.append(c);
        }
        return sb.toString();
    }

    public static String toTitleCase(String text) {
        String lower = toLower(text);
        StringBuilder sb = new StringBuilder();
        boolean cap = true;
        for (char c : lower.toCharArray()) {
            if (c == ' ') cap = true;
            else if (cap && c >= 'a' && c <= 'z') {
                c = (char)(c - 32);
                cap = false;
            } else {
                cap = false;
            }
            sb.append(c);
        }
        return sb.toString();
    }
}
