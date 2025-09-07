import java.io.*;

public class FileOrganizer {
    public static void main(String[] args) {
        java.util.Scanner sc = new java.util.Scanner(System.in);
        System.out.println("Enter folder path to organize: ");
        String path = sc.nextLine();

        File folder = new File(path);
        if (!folder.isDirectory()) {
            System.out.println("Invalid folder!");
            return;
        }

        File[] files = folder.listFiles();
        if (files == null) {
            System.out.println("No files found.");
            return;
        }

        for (File f : files) {
            if (f.isFile()) {
                String name = f.getName();
                int dot = name.lastIndexOf('.');
                if (dot == -1) continue;

                String ext = name.substring(dot + 1).toLowerCase();
                File dir = new File(path + File.separator + ext);
                if (!dir.exists()) dir.mkdir();

                f.renameTo(new File(dir, f.getName()));
            }
        }

        System.out.println("Files organized successfully!");
    }
}
