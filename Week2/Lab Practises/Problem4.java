import java.util.*;

public class Problem4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter text: ");
        String text = sc.nextLine();
        System.out.print("Enter shift value: ");
        int shift = sc.nextInt();

        String encrypted = encrypt(text, shift);
        String decrypted = decrypt(encrypted, shift);

        System.out.println("\nOriginal:   " + text);
        System.out.println("Encrypted:  " + encrypted);
        System.out.println("Decrypted:  " + decrypted);
        System.out.println("Valid Decryption? " + text.equals(decrypted));
        sc.close();
    }

    public static String encrypt(String text, int shift) {
        StringBuilder sb = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (c >= 'A' && c <= 'Z')
                sb.append((char)((c - 'A' + shift) % 26 + 'A'));
            else if (c >= 'a' && c <= 'z')
                sb.append((char)((c - 'a' + shift) % 26 + 'a'));
            else
                sb.append(c);
        }
        return sb.toString();
    }

    public static String decrypt(String text, int shift) {
        return encrypt(text, 26 - (shift % 26));
    }
}
