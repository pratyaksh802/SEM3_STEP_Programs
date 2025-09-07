import java.util.*;

public class Problem3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of iterations: ");
        int n = sc.nextInt();

        long stringTime = testStringConcat(n);
        long builderTime = testStringBuilder(n);
        long bufferTime = testStringBuffer(n);

        System.out.println("\n--- Performance ---");
        System.out.printf("%-15s %-15s\n", "Method", "Time (ms)");
        System.out.printf("%-15s %-15d\n", "String", stringTime);
        System.out.printf("%-15s %-15d\n", "StringBuilder", builderTime);
        System.out.printf("%-15s %-15d\n", "StringBuffer", bufferTime);
        sc.close();
    }

    public static long testStringConcat(int n) {
        long start = System.currentTimeMillis();
        String s = "";
        for (int i = 0; i < n; i++) s += "a";
        return System.currentTimeMillis() - start;
    }

    public static long testStringBuilder(int n) {
        long start = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) sb.append("a");
        return System.currentTimeMillis() - start;
    }

    public static long testStringBuffer(int n) {
        long start = System.currentTimeMillis();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < n; i++) sb.append("a");
        return System.currentTimeMillis() - start;
    }
}
