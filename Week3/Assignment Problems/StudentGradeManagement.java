import java.util.*;

class Subject {
    String subjectCode;
    String subjectName;
    int credits;
    String instructor;

    Subject(String subjectCode, String subjectName, int credits, String instructor) {
        this.subjectCode = subjectCode;
        this.subjectName = subjectName;
        this.credits = credits;
        this.instructor = instructor;
    }
}

class Student {
    private String studentId;
    private String studentName;
    private String className;

    public String getStudentName() {
        return studentName;
    }
    private String[] subjects;
    private double[][] marks;
    private double gpa;

    static int totalStudents = 0;
    static String schoolName = "Global International School";
    static String[] gradingScale = {"A: 90-100", "B: 75-89", "C: 60-74", "D: 40-59", "F: Below 40"};
    static double passPercentage = 40.0;

    Student(String studentName, String className, String[] subjects) {
        this.studentId = "STU" + String.format("%03d", ++totalStudents);
        this.studentName = studentName;
        this.className = className;
        this.subjects = subjects;
        this.marks = new double[subjects.length][];
        for (int i = 0; i < subjects.length; i++) {
            this.marks[i] = new double[1];
        }
    }

    void addMarks(String subject, double score) {
        for (int i = 0; i < subjects.length; i++) {
            if (subjects[i].equalsIgnoreCase(subject)) {
                marks[i][0] = score;
                return;
            }
        }
    }

    void calculateGPA() {
        double total = 0;
        for (int i = 0; i < subjects.length; i++) {
            total += marks[i][0];
        }
        double percentage = total / subjects.length;
        if (percentage >= 90) gpa = 4.0;
        else if (percentage >= 75) gpa = 3.5;
        else if (percentage >= 60) gpa = 3.0;
        else if (percentage >= 40) gpa = 2.0;
        else gpa = 0.0;
    }

    boolean checkPromotionEligibility() {
        double total = 0;
        for (int i = 0; i < subjects.length; i++) {
            total += marks[i][0];
        }
        double percentage = total / subjects.length;
        return percentage >= passPercentage;
    }

    void generateReportCard() {
        System.out.println("------------------------------------------------");
        System.out.println("Report Card - " + schoolName);
        System.out.println("Student ID: " + studentId);
        System.out.println("Name: " + studentName);
        System.out.println("Class: " + className);
        System.out.println("Subjects & Marks:");
        double total = 0;
        for (int i = 0; i < subjects.length; i++) {
            System.out.println(subjects[i] + ": " + marks[i][0]);
            total += marks[i][0];
        }
        double percentage = total / subjects.length;
        calculateGPA();
        System.out.println("Percentage: " + percentage);
        System.out.println("GPA: " + gpa);
        System.out.println("Grade: " + getGrade(percentage));
        System.out.println("Promotion Status: " + (checkPromotionEligibility() ? "Promoted" : "Not Promoted"));
        System.out.println("------------------------------------------------");
    }

    String getGrade(double percentage) {
        if (percentage >= 90) return "A";
        else if (percentage >= 75) return "B";
        else if (percentage >= 60) return "C";
        else if (percentage >= 40) return "D";
        else return "F";
    }

    static void setGradingScale(String[] newScale) {
        gradingScale = newScale;
    }

    static double calculateClassAverage(Student[] students) {
        double total = 0;
        int count = 0;
        for (Student s : students) {
            double sum = 0;
            for (int i = 0; i < s.subjects.length; i++) {
                sum += s.marks[i][0];
            }
            total += sum / s.subjects.length;
            count++;
        }
        return total / count;
    }

    static Student[] getTopPerformers(Student[] students, int count) {
        Arrays.sort(students, (a, b) -> {
            double totalA = 0, totalB = 0;
            for (int i = 0; i < a.subjects.length; i++) totalA += a.marks[i][0];
            for (int i = 0; i < b.subjects.length; i++) totalB += b.marks[i][0];
            double avgA = totalA / a.subjects.length;
            double avgB = totalB / b.subjects.length;
            return Double.compare(avgB, avgA);
        });
        return Arrays.copyOfRange(students, 0, count);
    }

    static void generateSchoolReport(Student[] students) {
        System.out.println("========== SCHOOL REPORT ==========");
        System.out.println("School Name: " + schoolName);
        System.out.println("Total Students: " + totalStudents);
        System.out.println("Class Average: " + calculateClassAverage(students));
        System.out.println("Grading Scale:");
        for (String grade : gradingScale) System.out.println(grade);
        System.out.println("===================================");
    }
}

public class StudentGradeManagement {
    public static void main(String[] args) {
        String[] subjects = {"Math", "Science", "English"};

        Student s1 = new Student("Alice", "10A", subjects);
        Student s2 = new Student("Bob", "10A", subjects);
        Student s3 = new Student("Charlie", "10A", subjects);

        s1.addMarks("Math", 95);
        s1.addMarks("Science", 88);
        s1.addMarks("English", 92);

        s2.addMarks("Math", 70);
        s2.addMarks("Science", 65);
        s2.addMarks("English", 75);

        s3.addMarks("Math", 40);
        s3.addMarks("Science", 35);
        s3.addMarks("English", 45);

        s1.generateReportCard();
        s2.generateReportCard();
        s3.generateReportCard();

        Student[] students = {s1, s2, s3};
        Student.generateSchoolReport(students);

        Student[] toppers = Student.getTopPerformers(students, 2);
        System.out.println("Top Performers:");
        for (Student s : toppers) {
            System.out.println(s.getStudentName());
        }
    }
}
