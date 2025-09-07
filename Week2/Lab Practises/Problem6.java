import java.util.*;

public class Problem6 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter text: ");
        String text = sc.nextLine();
        System.out.print("Enter line width: ");
        int width = sc.nextInt();

        System.out.println("\n--- Justified Text ---");
        formatJustify(text, width);

        System.out.println("\n--- Centered Text ---");
        formatCenter(text, width);

        sc.close();
    }

    public static void formatJustify(String text, int width) {
        String[] words = text.split(" ");
        List<String> line = new ArrayList<>();
        int len = 0;
        for (String w : words) {
            if (len + w.length() + line.size() > width) {
                printJustifiedLine(line, width, false);
                line.clear();
                len = 0;
            }
            line.add(w);
            len += w.length();
        }
        if (!line.isEmpty()) printJustifiedLine(line, width, true);
    }

    private static void printJustifiedLine(List<String> words, int width, boolean lastLine) {
        if (words.size() == 1 || lastLine) {
            System.out.println(String.join(" ", words));
            return;
        }
        int totalChars = words.stream().mapToInt(String::length).sum();
        int spaces = width - totalChars;
        int gaps = words.size() - 1;
        int evenSpace = spaces / gaps;
        int extra = spaces % gaps;

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < words.size(); i++) {
            sb.append(words.get(i));
            if (i < gaps) {
                for (int s = 0; s < evenSpace; s++) sb.append(" ");
                if (extra-- > 0) sb.append(" ");
            }
        }
        System.out.println(sb.toString());
    }

    public static void formatCenter(String text, int width) {
        String[] words = text.split(" ");
        StringBuilder line = new StringBuilder();
        for (String w : words) {
            if (line.length() + w.length() + 1 > width) {
                printCenteredLine(line.toString(), width);
                line.setLength(0);
            }
            if (line.length() > 0) line.append(" ");
            line.append(w);
        }
        if (line.length() > 0) printCenteredLine(line.toString(), width);
    }

    private static void printCenteredLine(String line, int width) {
        int padding = (width - line.length()) / 2;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < padding; i++) sb.append(" ");
        sb.append(line);
        System.out.println(sb.toString());
    }
}
