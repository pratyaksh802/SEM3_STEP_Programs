import java.util.*;

public class Problem1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the main text:");
        String text = sc.nextLine();
        System.out.println("Enter substring to find:");
        String find = sc.nextLine();
        System.out.println("Enter substring to replace with:");
        String replace = sc.nextLine();

        String manualResult = manualReplace(text, find, replace);
        String builtinResult = text.replace(find, replace);

        System.out.println("\n--- Results ---");
        System.out.println("Manual Replace: " + manualResult);
        System.out.println("Built-in Replace: " + builtinResult);
        System.out.println("Match with built-in? " + manualResult.equals(builtinResult));
        sc.close();
    }

    public static String manualReplace(String text, String find, String replace) {
        StringBuilder result = new StringBuilder();
        int i = 0;
        while (i < text.length()) {
            if (i <= text.length() - find.length() && text.substring(i, i + find.length()).equals(find)) {
                result.append(replace);
                i += find.length();
            } else {
                result.append(text.charAt(i));
                i++;
            }
        }
        return result.toString();
    }
}
