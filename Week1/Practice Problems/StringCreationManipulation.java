public class StringCreationManipulation {
    public static void main(String[] args) {

        // === Different ways to create strings ===
        String s1 = "Hello";                // string literal
        String s2 = new String("World");        // using 'new' keyword
        char[] charArray = {'J', 'a', 'v', 'a'};
        String s3 = new String(charArray);      // from char array
        byte[] byteArray = {65, 66, 67, 68};
        String s4 = new String(byteArray);      // from byte array

        System.out.println("=== String Creation ===");
        System.out.println("s1 (literal): " + s1);
        System.out.println("s2 (new): " + s2);
        System.out.println("s3 (char array): " + s3);
        System.out.println("s4 (byte array): " + s4);

        // === Basic Manipulation ===
        System.out.println("\n=== Manipulation ===");

        // Concatenation
        String concat = s1 + " " + s2;
        System.out.println("Concatenation: " + concat);

        // Substring
        System.out.println("Substring (s3 from index 1 to 3): " + s3.substring(1, 3));

        // Case conversion
        System.out.println("Uppercase: " + concat.toUpperCase());
        System.out.println("Lowercase: " + concat.toLowerCase());

        // Character access
        System.out.println("First char of s1: " + s1.charAt(0));

        // Length
        System.out.println("Length of concat: " + concat.length());

        // Equality check
        System.out.println("s1 equals \"Hello\"? " + s1.equals("Hello"));
        System.out.println("s1 equalsIgnoreCase \"hello\"? " + s1.equalsIgnoreCase("hello"));

        // Replace
        System.out.println("Replace 'World' with 'Java': " + concat.replace("World", "Java"));

        // Splitting
        String[] parts = concat.split(" ");
        System.out.println("Split concat into words:");
        for (String part : parts) {
            System.out.println("- " + part);
        }

        // Trimming
        String s5 = "   extra space   ";
        System.out.println("Before trim: \"" + s5 + "\"");
        System.out.println("After trim: \"" + s5.trim() + "\"");
    }
}
