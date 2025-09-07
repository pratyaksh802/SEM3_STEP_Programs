import java.util.*;

abstract class Employee {
    String empId, empName, department, designation, joinDate;
    double baseSalary;
    boolean[] attendanceRecord;
    double bonus;
    static int totalEmployees = 0;
    static String companyName = "Tech Solutions Pvt Ltd";
    static double totalSalaryExpense = 0;
    static int workingDaysPerMonth = 30;

    public Employee(String empId, String empName, String department, String designation, double baseSalary, String joinDate) {
        this.empId = empId;
        this.empName = empName;
        this.department = department;
        this.designation = designation;
        this.baseSalary = baseSalary;
        this.joinDate = joinDate;
        this.attendanceRecord = new boolean[workingDaysPerMonth];
        this.bonus = 0;
        totalEmployees++;
    }

    public void markAttendance(int day, boolean present) {
        if (day > 0 && day <= workingDaysPerMonth) {
            attendanceRecord[day - 1] = present;
        }
    }

    public int getPresentDays() {
        int count = 0;
        for (boolean present : attendanceRecord) if (present) count++;
        return count;
    }

    public abstract double calculateSalary();

    public void calculateBonus() {
        int presentDays = getPresentDays();
        double performance = (double) presentDays / workingDaysPerMonth;
        bonus = baseSalary * performance * 0.1; // 10% of base based on performance
    }

    public void generatePaySlip() {
        double salary = calculateSalary();
        calculateBonus();
        System.out.println("Pay Slip for " + empName);
        System.out.println("ID: " + empId + " | Department: " + department + " | Designation: " + designation);
        System.out.println("Base Salary: " + baseSalary);
        System.out.println("Attendance Days: " + getPresentDays());
        System.out.println("Bonus: " + bonus);
        System.out.println("Net Salary: " + (salary + bonus));
        System.out.println("----------------------------");
    }

    public void requestLeave(int day) {
        if (day > 0 && day <= workingDaysPerMonth) {
            attendanceRecord[day - 1] = false;
            System.out.println(empName + " requested leave on day " + day);
        }
    }
}

class FullTimeEmployee extends Employee {
    public FullTimeEmployee(String empId, String empName, String department, String designation, double baseSalary, String joinDate) {
        super(empId, empName, department, designation, baseSalary, joinDate);
    }

    public double calculateSalary() {
        int presentDays = getPresentDays();
        double salary = (baseSalary / workingDaysPerMonth) * presentDays;
        totalSalaryExpense += salary;
        return salary;
    }
}

class PartTimeEmployee extends Employee {
    public PartTimeEmployee(String empId, String empName, String department, String designation, double baseSalary, String joinDate) {
        super(empId, empName, department, designation, baseSalary, joinDate);
    }

    public double calculateSalary() {
        int presentDays = getPresentDays();
        double salary = (baseSalary / (workingDaysPerMonth / 2)) * presentDays;
        totalSalaryExpense += salary;
        return salary;
    }
}

class ContractEmployee extends Employee {
    public ContractEmployee(String empId, String empName, String department, String designation, double baseSalary, String joinDate) {
        super(empId, empName, department, designation, baseSalary, joinDate);
    }

    public double calculateSalary() {
        double salary = baseSalary; // fixed contract salary
        totalSalaryExpense += salary;
        return salary;
    }
}

class Department {
    String deptId, deptName;
    Employee manager;
    Employee[] employees;
    double budget;

    public Department(String deptId, String deptName, Employee manager, Employee[] employees, double budget) {
        this.deptId = deptId;
        this.deptName = deptName;
        this.manager = manager;
        this.employees = employees;
        this.budget = budget;
    }

    public double calculateDepartmentExpenses() {
        double total = 0;
        for (Employee e : employees) {
            if (e != null) {
                total += e.calculateSalary();
            }
        }
        return total;
    }
}

public class PayrollSystem {
    public static void calculateCompanyPayroll(Employee[] employees) {
        double total = 0;
        for (Employee e : employees) {
            if (e != null) total += e.calculateSalary() + e.bonus;
        }
        System.out.println("Total Company Payroll: " + total);
    }

    public static void getDepartmentWiseExpenses(Department[] departments) {
        for (Department d : departments) {
            System.out.println("Department: " + d.deptName + " | Expenses: " + d.calculateDepartmentExpenses());
        }
    }

    public static void getAttendanceReport(Employee[] employees) {
        System.out.println("\n--- Attendance Report ---");
        for (Employee e : employees) {
            if (e != null) {
                System.out.println(e.empName + " | Present Days: " + e.getPresentDays());
            }
        }
    }

    public static void main(String[] args) {
        Employee e1 = new FullTimeEmployee("E001", "Alice", "IT", "Developer", 30000, "2025-01-01");
        Employee e2 = new PartTimeEmployee("E002", "Bob", "HR", "Assistant", 15000, "2025-01-05");
        Employee e3 = new ContractEmployee("E003", "Charlie", "Finance", "Consultant", 25000, "2025-01-10");

        for (int i = 1; i <= 20; i++) e1.markAttendance(i, true);
        for (int i = 1; i <= 15; i++) e2.markAttendance(i, true);
        for (int i = 1; i <= 30; i++) e3.markAttendance(i, true);

        e2.requestLeave(16);

        e1.generatePaySlip();
        e2.generatePaySlip();
        e3.generatePaySlip();

        Department d1 = new Department("D001", "IT", e1, new Employee[]{e1}, 100000);
        Department d2 = new Department("D002", "HR", e2, new Employee[]{e2}, 50000);
        Department d3 = new Department("D003", "Finance", e3, new Employee[]{e3}, 80000);

        getDepartmentWiseExpenses(new Department[]{d1, d2, d3});
        getAttendanceReport(new Employee[]{e1, e2, e3});
        calculateCompanyPayroll(new Employee[]{e1, e2, e3});

        System.out.println("\nTotal Employees: " + Employee.totalEmployees);
        System.out.println("Company: " + Employee.companyName);
    }
}
