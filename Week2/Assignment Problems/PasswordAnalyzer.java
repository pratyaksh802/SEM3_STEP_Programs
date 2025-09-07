import java.util.*;

public class PasswordAnalyzer {

    static int[] analyze(String pwd) {
        int upper=0, lower=0, digit=0, special=0;
        for (int i=0;i<pwd.length();i++) {
            char ch = pwd.charAt(i);
            if (ch >= 65 && ch <= 90) upper++;
            else if (ch >= 97 && ch <= 122) lower++;
            else if (ch >= 48 && ch <= 57) digit++;
            else special++;
        }
        return new int[]{upper, lower, digit, special};
    }

    static int score(String pwd) {
        int[] c = analyze(pwd);
        int lenPoints = Math.max(0, (pwd.length() - 8) * 2);
        int variety = 0;
        for (int x : c) if (x > 0) variety += 10;
        int penalty = (pwd.contains("123")||pwd.contains("abc")||pwd.contains("qwerty"))?-10:0;
        return lenPoints + variety + penalty;
    }

    static String level(int s) {
        if (s <= 20) return "Weak";
        else if (s <= 50) return "Medium";
        return "Strong";
    }

    static String generate(int len) {
        String upper="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lower="abcdefghijklmnopqrstuvwxyz";
        String digits="0123456789";
        String special="!@#$%^&*";
        Random r=new Random();
        StringBuilder sb=new StringBuilder();

        sb.append(upper.charAt(r.nextInt(upper.length())));
        sb.append(lower.charAt(r.nextInt(lower.length())));
        sb.append(digits.charAt(r.nextInt(digits.length())));
        sb.append(special.charAt(r.nextInt(special.length())));

        String all=upper+lower+digits+special;
        while(sb.length()<len)
            sb.append(all.charAt(r.nextInt(all.length())));

        List<Character> chars=new ArrayList<>();
        for(char ch:sb.toString().toCharArray()) chars.add(ch);
        Collections.shuffle(chars);
        StringBuilder pwd=new StringBuilder();
        for(char ch:chars) pwd.append(ch);
        return pwd.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter passwords (comma separated): ");
        String[] pwds = sc.nextLine().split(",");

        System.out.printf("%-15s %-6s %-6s %-6s %-6s %-6s %-6s %-10s\n",
                "Password","Len","U","L","D","S","Score","Strength");
        for (String p : pwds) {
            p = p.trim();
            int[] c = analyze(p);
            int s = score(p);
            System.out.printf("%-15s %-6d %-6d %-6d %-6d %-6d %-6d %-10s\n",
                    p, p.length(), c[0], c[1], c[2], c[3], s, level(s));
        }

        System.out.println("\nGenerated Strong Password: " + generate(12));
    }
}
