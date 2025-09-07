import java.util.Scanner;

public class CompleteStringApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 1. Take user input
        System.out.print("Enter a sentence: ");
        String text = sc.nextLine();

        System.out.println("\n=== Original Text ===");
        System.out.println(text);

        // 2. String Methods
        System.out.println("\n=== String Methods ===");
        System.out.println("Length: " + text.length());
        System.out.println("Uppercase: " + text.toUpperCase());
        System.out.println("Lowercase: " + text.toLowerCase());
        if (text.length() >= 5) {
            System.out.println("Substring (0 to 5): " + text.substring(0, 5));
        }
        System.out.println("Replace spaces with '-': " + text.replace(" ", "-"));
        System.out.println("Does it contain 'Java'? " + text.contains("Java"));

        // 3. String Array Processing (words)
        String[] words = text.split(" ");
        System.out.println("\n=== Word List ===");
        for (int i = 0; i < words.length; i++) {
            System.out.println((i + 1) + ". " + words[i] + " (length " + words[i].length() + ")");
        }

        // 4. StringBuilder operations
        StringBuilder sb = new StringBuilder(text);
        System.out.println("\n=== StringBuilder Operations ===");
        sb.append(" [Appended]");
        System.out.println("After append: " + sb);

        sb.insert(0, "[Start] ");
        System.out.println("After insert: " + sb);

        if (sb.length() > 5) {
            sb.delete(0, 7);
            System.out.println("After delete (first 7 chars): " + sb);
        }

        System.out.println("Reversed: " + sb.reverse());

        // 5. Word count + character count
        System.out.println("\n=== Statistics ===");
        System.out.println("Word count: " + words.length);
        System.out.println("Character count: " + text.length());

        sc.close();
    }
}
