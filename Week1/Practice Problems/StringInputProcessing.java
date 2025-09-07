import java.util.Scanner;

public class StringInputProcessing {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Take user input
        System.out.print("Enter a string: ");
        String input = sc.nextLine();

        System.out.println("\n=== String Processing ===");

        // Length of string
        System.out.println("Length: " + input.length());

        // Convert to uppercase
        System.out.println("Uppercase: " + input.toUpperCase());

        // Convert to lowercase
        System.out.println("Lowercase: " + input.toLowerCase());

        // First and last characters
        if (input.length() > 0) {
            System.out.println("First character: " + input.charAt(0));
            System.out.println("Last character: " + input.charAt(input.length() - 1));
        }

        // Substring (if length > 3)
        if (input.length() >= 3) {
            System.out.println("Substring (0 to 3): " + input.substring(0, 3));
        }

        // Check if string contains "Java"
        System.out.println("Contains 'Java'? " + input.contains("Java"));

        // Replace spaces with hyphens
        System.out.println("Replace spaces: " + input.replace(" ", "-"));

        // Reverse the string (manual way)
        String reversed = "";
        for (int i = input.length() - 1; i >= 0; i--) {
            reversed += input.charAt(i);
        }
        System.out.println("Reversed: " + reversed);

        // Split into words
        String[] words = input.split(" ");
        System.out.println("Words in the string:");
        for (String word : words) {
            System.out.println("- " + word);
        }

        sc.close();
    }
}
