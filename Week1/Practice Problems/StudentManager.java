import java.util.Scanner;

public class StudentManager {
    
    // Method to display all student names
    public static void displayStudents(String[] students, int count) {
        System.out.println("\n=== Student List ===");
        if (count == 0) {
            System.out.println("No students available.");
        } else {
            for (int i = 0; i < count; i++) {
                System.out.println((i + 1) + ". " + students[i]);
            }
        }
    }

    // Method to search a student
    public static void searchStudent(String[] students, int count, String name) {
        boolean found = false;
        for (int i = 0; i < count; i++) {
            if (students[i].equalsIgnoreCase(name)) {
                System.out.println("Student '" + name + "' found at position " + (i + 1));
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Student '" + name + "' not found.");
        }
    }

    // Method to add a student
    public static int addStudent(String[] students, int count, String name) {
        if (count < students.length) {
            students[count] = name;
            System.out.println("Student '" + name + "' added successfully.");
            return count + 1;
        } else {
            System.out.println("List is full! Cannot add more students.");
            return count;
        }
    }

    // Method to remove a student
    public static int removeStudent(String[] students, int count, String name) {
        for (int i = 0; i < count; i++) {
            if (students[i].equalsIgnoreCase(name)) {
                // shift elements left
                for (int j = i; j < count - 1; j++) {
                    students[j] = students[j + 1];
                }
                System.out.println("Student '" + name + "' removed successfully.");
                return count - 1;
            }
        }
        System.out.println("Student '" + name + "' not found.");
        return count;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        String[] students = new String[50]; // array of 50 students
        int count = 0;  // current number of students

        int choice;
        do {
            System.out.println("\n=== Student Manager ===");
            System.out.println("1. Add Student");
            System.out.println("2. Display Students");
            System.out.println("3. Search Student");
            System.out.println("4. Remove Student");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter student name to add: ");
                    String nameToAdd = sc.nextLine();
                    count = addStudent(students, count, nameToAdd);
                    break;

                case 2:
                    displayStudents(students, count);
                    break;

                case 3:
                    System.out.print("Enter student name to search: ");
                    String nameToSearch = sc.nextLine();
                    searchStudent(students, count, nameToSearch);
                    break;

                case 4:
                    System.out.print("Enter student name to remove: ");
                    String nameToRemove = sc.nextLine();
                    count = removeStudent(students, count, nameToRemove);
                    break;

                case 5:
                    System.out.println("Exiting... Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice! Try again.");
            }
        } while (choice != 5);

        sc.close();
    }
}
