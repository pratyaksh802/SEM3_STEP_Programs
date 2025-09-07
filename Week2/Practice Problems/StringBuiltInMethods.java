public class StringBuiltInMethods {
    public static void main(String[] args) {
        String sampleText = " Java Programming is Fun and Challenging! ";
        // TODO: Use built-in methods to perform the following operations:
        // 1. Display original string length including spaces
        System.out.println("Original length : " + sampleText.length());
        // 2. Remove leading and trailing spaces, show new length
        String[] words = sampleText.trim().split(" ");
        String newText = String.join("", words);
        // System.out.println("new string " + newText);
        System.out.println("Trimmed length : " + newText.length());
        // 3. Find and display the character at index 5
        System.out.println("Character at index 5: " + newText.charAt(5));
        // 4. Extract substring "Programming" from the text
        System.out.println("Substring 'Programming': " + newText.substring(4, 15));
        // 5. Find the index of the word "Fun"
        System.out.println("Index of 'Fun': " + newText.indexOf("Fun"));
        // 6. Check if the string contains "Java" (case-sensitive)
        System.out.println("Contains 'Java': " + newText.contains("Java"));
        // 7. Check if the string starts with "Java" (after trimming)
        System.out.println("Starts with 'Java': " + newText.startsWith("Java"));
        // 8. Check if the string ends with an exclamation mark
        System.out.println("Ends with '!': " + newText.endsWith("!"));
        // 9. Convert the entire string to uppercase
        System.out.println("Uppercase: " + newText.toUpperCase());
        // 10. Convert the entire string to lowercase
        System.out.println("Lowercase: " + newText.toLowerCase());
        // TODO: Create a method that counts vowels using charAt()
        System.out.println("Number of vowels: " + countVowels(newText));
        // TODO: Create a method that finds all occurrences of a character
        char targetChar = 'a';
        findAllOccurrences(newText, targetChar);
        // TODO: Display all results in a formatted manner
    }

    
    // TODO: Method to count vowels in a string
    public static int countVowels(String text) { 
        int count = 0;
        char c;
        for(int i = 0;i < text.length();i++){
            c = text.charAt(i);
            if(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' ||
               c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U') {
                count++;
            }
        }
        return count;
    }

    // TODO: Method to find all positions of a character
    public static void findAllOccurrences(String text, char target) {
        int occurs = 0;
        for(int i = 0;i < text.length();i++){
            if(text.charAt(i) == target){
                occurs++;
            }
        }
        System.out.println("Occurrences of '" + target + "': " + occurs);
    }
}
