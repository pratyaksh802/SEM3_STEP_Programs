import java.util.*;

public class Problem5 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<String> emails = new ArrayList<>();
        System.out.println("Enter emails (type 'done' to finish):");
        while (true) {
            String email = sc.nextLine();
            if (email.equalsIgnoreCase("done")) break;
            emails.add(email);
        }

        System.out.printf("%-25s %-15s %-20s %-15s %-10s %-10s\n",
                "Email", "Username", "Domain", "Domain Name", "Ext", "Valid");

        for (String email : emails) {
            boolean valid = validate(email);
            if (valid) {
                String username = email.substring(0, email.indexOf('@'));
                String domain = email.substring(email.indexOf('@') + 1);
                String domainName = domain.contains(".") ? domain.substring(0, domain.indexOf('.')) : domain;
                String ext = domain.contains(".") ? domain.substring(domain.indexOf('.') + 1) : "";
                System.out.printf("%-25s %-15s %-20s %-15s %-10s %-10s\n",
                        email, username, domain, domainName, ext, "Valid");
            } else {
                System.out.printf("%-25s %-15s %-20s %-15s %-10s %-10s\n",
                        email, "-", "-", "-", "-", "Invalid");
            }
        }
        sc.close();
    }

    public static boolean validate(String email) {
        int at = email.indexOf('@');
        int lastAt = email.lastIndexOf('@');
        if (at == -1 || at != lastAt) return false;
        int dot = email.indexOf('.', at);
        if (dot == -1) return false;
        if (at == 0 || at == email.length() - 1) return false;
        return true;
    }
}
