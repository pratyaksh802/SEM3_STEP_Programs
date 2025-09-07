import java.util.Scanner;

public class ASCIIProcessor {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Ask user to enter a string
        System.out.print("Enter a string: ");
        String input = scanner.nextLine();

        System.out.println("\nCharacter Analysis:");
        for (char ch : input.toCharArray()) {
            int ascii = (int) ch;
            System.out.println("Character: '" + ch + "' | ASCII: " + ascii);

            if (Character.isUpperCase(ch)) {
                System.out.println("Type: Uppercase Letter");
                char lower = Character.toLowerCase(ch);
                System.out.println("Lowercase: '" + lower + "' | ASCII: " + (int) lower);
                System.out.println("Difference (Upper - Lower): " + (ascii - (int) lower));
            } else if (Character.isLowerCase(ch)) {
                System.out.println("Type: Lowercase Letter");
                char upper = Character.toUpperCase(ch);
                System.out.println("Uppercase: '" + upper + "' | ASCII: " + (int) upper);
                System.out.println("Difference (Upper - Lower): " + ((int) upper - ascii));
            } else if (Character.isDigit(ch)) {
                System.out.println("Type: Digit");
            } else {
                System.out.println("Type: Special Character");
            }
            System.out.println();
        }

        // ASCII Art using character codes
        System.out.println("ASCII Art:");
        for (char ch : input.toCharArray()) {
            int ascii = (int) ch;
            for (int i = 0; i < ascii % 10 + 1; i++) {
                System.out.print(ch);
            }
            System.out.println();
        }

        // Simple Caesar cipher (shift by 3)
        System.out.print("\nCaesar Cipher (shift by 3): ");
        StringBuilder cipher = new StringBuilder();
        for (char ch : input.toCharArray()) {
            if (Character.isLetter(ch)) {
                char base = Character.isUpperCase(ch) ? 'A' : 'a';
                char shifted = (char) ((ch - base + 3) % 26 + base);
                cipher.append(shifted);
            } else {
                cipher.append(ch);
            }
        }
        System.out.println(cipher);

        scanner.close();
    }
}