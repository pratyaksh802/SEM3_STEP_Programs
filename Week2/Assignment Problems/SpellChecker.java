import java.util.*;

public class SpellChecker {

    // Split sentence into words (without using split)
    static String[] splitWords(String sentence) {
        ArrayList<String> words = new ArrayList<>();
        int start = 0;
        for (int i = 0; i < sentence.length(); i++) {
            char ch = sentence.charAt(i);
            if (!Character.isLetter(ch)) {
                if (start < i) {
                    words.add(sentence.substring(start, i));
                }
                start = i + 1;
            }
        }
        if (start < sentence.length()) {
            words.add(sentence.substring(start));
        }
        return words.toArray(new String[0]);
    }

    // Calculate string distance
    static int stringDistance(String a, String b) {
        int m = a.length();
        int n = b.length();
        int[][] dp = new int[m+1][n+1];

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0) dp[i][j] = j;
                else if (j == 0) dp[i][j] = i;
                else if (a.charAt(i-1) == b.charAt(j-1))
                    dp[i][j] = dp[i-1][j-1];
                else
                    dp[i][j] = 1 + Math.min(dp[i-1][j-1], 
                                   Math.min(dp[i-1][j], dp[i][j-1]));
            }
        }
        return dp[m][n];
    }

    // Find closest match from dictionary
    static String findClosest(String word, String[] dictionary) {
        String closest = word;
        int minDist = Integer.MAX_VALUE;
        for (String dict : dictionary) {
            int d = stringDistance(word, dict);
            if (d < minDist) {
                minDist = d;
                closest = dict;
            }
        }
        return (minDist <= 2) ? closest : word;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] dictionary = {"hello", "world", "java", "program", "spell", "checker"};
        
        System.out.println("Enter a sentence: ");
        String sentence = sc.nextLine();

        String[] words = splitWords(sentence);

        System.out.printf("%-15s %-15s %-10s %-10s\n", "Word", "Suggestion", "Distance", "Status");
        for (String w : words) {
            String suggestion = findClosest(w, dictionary);
            int dist = stringDistance(w, suggestion);
            String status = (dist == 0) ? "Correct" : "Misspelled";
            System.out.printf("%-15s %-15s %-10d %-10s\n", w, suggestion, dist, status);
        }
    }
}
