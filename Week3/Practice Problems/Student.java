import java.util.Scanner;

public class Student {
    private String studentID;
    private String name;
    private double grade;
    private String course;

    public Scanner scanner = new Scanner(System.in);
    Student() {
        System.out.println("Default constructor called");
    }

    Student(String studentID, String name, double grade, String course) {
        this.studentID = studentID;
        this.name = name;
        this.grade = grade;
        this.course = course;
    }

    void getInfo() {
        System.out.println("Get Student Information:");
        System.out.println("1. Student ID");
        System.out.println("2. Name");
        System.out.println("3. Grade");
        System.out.println("4. Course");

        System.out.print("Enter your choice (1-4): ");
        int option = scanner.nextInt();

        switch (option) {
            case 1:
                System.out.println("Student ID: " + studentID);
                break;
            case 2:
                System.out.println("Name: " + name);
                break;
            case 3:
                System.out.println("Grade: " + grade);
                break;
            case 4:
                System.out.println("Course: " + course);
                break;
            default:
                break;
        }

        scanner.close();
    }
    void setInfo() {
        System.out.println("Set Student Information:");
        System.out.println("1. Student ID");
        System.out.println("2. Name");
        System.out.println("3. Grade");
        System.out.println("4. Course");

        System.out.print("Enter your choice (1-4): ");
        int option = scanner.nextInt();

        switch (option) {
            case 1:
                System.out.print("Enter Student ID: ");
                this.studentID = scanner.next();
                break;
            case 2:
                System.out.print("Enter Name: ");
                this.name = scanner.next();
                break;
            case 3:
                System.out.print("Enter Grade: ");
                this.grade = scanner.nextDouble();
                break;
            case 4:
                System.out.print("Enter Course: ");
                this.course = scanner.next();
                break;
            default:
                System.out.println("Invalid option.");
                break;
        }

        scanner.close();
    }

    String calculateLetterGrade() {
        if (this.grade >= 90) {
            return "A (90-100)";
        } else if (this.grade >= 80) {
            return "B (80-89)";
        } else if (this.grade >= 70) {
            return "C (70-79) ";
        } else if (this.grade >= 60) {
            return "D (60-69)";
        } else {
            return "F (0-59)";
        }
    } 

    void displayInfo() {
        System.out.println("Student Information:");
        System.out.println("Student ID: " + this.studentID);
        System.out.println("Name: " + this.name);
        System.out.println("Grade: " + this.grade);
        System.out.println("Course: " + this.course);
    }

    void setFields() {
        
        System.out.print("Enter Student ID: ");
        this.studentID = scanner.next();
        System.out.print("Enter Name: ");
        this.name = scanner.next();
        System.out.print("Enter Grade: ");
        this.grade = scanner.nextDouble();
        System.out.print("Enter Course: ");
        this.course = scanner.next();
        // scanner.close(); // Do not close here to avoid closing System.in
    }

    public static void main(String[] args) {
        Student s1 = new Student();
        System.out.println("Student 1");
        s1.setFields();
        System.out.println("-----------------");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Student 2");
        Student s2 = new Student("123", "sachin", 85.5, "Mathematics");
        s2.getInfo();
        System.out.println("-----------------");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        s1.displayInfo();
        System.out.println("Letter Grade: " + s1.calculateLetterGrade());
        System.out.println("-----------------");
        try {
            Thread.sleep(2*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        s2.displayInfo();
        System.out.println("Letter Grade: " + s2.calculateLetterGrade());


    }
}

