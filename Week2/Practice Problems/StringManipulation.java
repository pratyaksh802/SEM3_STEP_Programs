import java.util.Scanner;

public class StringManipulation {
    public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    // TODO: Ask user to enter a sentence with mixed formatting
    System.out.println("Enter a sentence with mixed formatting:");
    String input = scanner.nextLine();
    // TODO: Process the input using the following methods:
    // 1. trim() - Remove extra spaces
    input = input.trim();
    // 2. replace() - Replace all spaces with underscores
    String processedText = input.trim().replace(" ", "_");
    // 3. replaceAll() - Remove all digits using regex
    processedText = processedText.replaceAll("\\d", "");
    // 4. split() - Split sentence into words array
    String[] words = processedText.split("_");
    // 5. join() - Rejoin words with " | " separator
    String joinedText = String.join(" | ", words);
    // TODO: Create additional processing methods:
    // - Remove all punctuation
    // - Capitalize first letter of each word
    // - Reverse the order of words
    // - Count word frequency
    scanner.close();
    }
    // TODO: Method to remove punctuation
    public static String removePunctuation(String text) {
        return text.replaceAll("[^a-zA-Z0-9_ ]", "");
    }
    // TODO: Method to capitalize each word
    public static String capitalizeWords(String text) {
        String[] words = text.split(" ");
        StringBuilder capitalized = new StringBuilder();
        for (String word : words) {
            capitalized.append(Character.toUpperCase(word.charAt(0)))
                       .append(word.substring(1)).append(" ");
        }
        return capitalized.toString().trim();
    }
    // TODO: Method to reverse word order
    public static String reverseWordOrder(String text) {
        String[] words = text.split(" ");
        StringBuilder reversed = new StringBuilder();
        for (int i = words.length - 1; i >= 0; i--) {
            reversed.append(words[i]).append(" ");
        }
        return reversed.toString().trim();
    }
    // TODO: Method to count word frequency
    public static void countWordFrequency(String text) {
        int count = 0;
        String[] words = text.split(" ");
        for (String word : words) {
            count++;
        }
        System.out.println("Word count: " + count);
    }
}