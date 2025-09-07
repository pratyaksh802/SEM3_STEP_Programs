import java.util.Scanner;

class Employee {
    private static String companyName = "Tech Solutions Inc.";
    private static int totalEmployees = 0;
    
    private int empId;
    private String name;
    private String department;
    private double salary;
    
    public Employee() {
        this(0, "", "", 0.0);
    }
    
    public Employee(int empId, String name, String department, double salary) {
        this.empId = empId;
        this.name = name;
        this.department = department;
        this.salary = salary;
        totalEmployees++;
    }
    
    public static void setCompanyName(String name) {
        companyName = name;
    }
    
    public static int getTotalEmployees() {
        return totalEmployees;
    }
    
    public double calculateAnnualSalary() {
        return salary * 12;
    }
    
    public void displayEmployee() {
        System.out.println("\n--- Employee Details ---");
        System.out.println("Employee ID: " + empId);
        System.out.println("Name: " + name);
        System.out.println("Department: " + department);
        System.out.printf("Monthly Salary: $%.2f\n", salary);
        System.out.printf("Annual Salary: $%.2f\n", calculateAnnualSalary());
    }
    
    public void updateSalary(double newSalary) {
        if (newSalary >= 0) {
            this.salary = newSalary;
            System.out.println("Salary updated successfully!");
        } else {
            System.out.println("Invalid salary amount!");
        }
    }
    
    public int getEmpId() { return empId; }
    public String getName() { return name; }
    public String getDepartment() { return department; }
    public double getSalary() { return salary; }
    
    public void setEmpId(int empId) { this.empId = empId; }
    public void setName(String name) { this.name = name; }
    public void setDepartment(String department) { this.department = department; }
    public void setSalary(double salary) { this.salary = salary; }
    
    public static String getCompanyName() { return companyName; }
}

class Department {
    private String deptName;
    private Employee[] employees;
    private int employeeCount;
    private static final int MAX_EMPLOYEES = 50;
    
    public Department(String deptName) {
        this.deptName = deptName;
        this.employees = new Employee[MAX_EMPLOYEES];
        this.employeeCount = 0;
    }
    
    public boolean addEmployee(Employee emp) {
        if (employeeCount < MAX_EMPLOYEES) {
            employees[employeeCount++] = emp;
            return true;
        }
        return false;
    }
    
    public Employee findHighestPaid() {
        if (employeeCount == 0) return null;
        
        Employee highestPaid = employees[0];
        for (int i = 1; i < employeeCount; i++) {
            if (employees[i].getSalary() > highestPaid.getSalary()) {
                highestPaid = employees[i];
            }
        }
        return highestPaid;
    }
    
    public double calculatePayroll() {
        double total = 0;
        for (int i = 0; i < employeeCount; i++) {
            total += employees[i].getSalary();
        }
        return total;
    }
    
    public void displayDepartmentInfo() {
        System.out.println("\n=== " + deptName + " Department ===");
        System.out.println("Number of Employees: " + employeeCount);
        System.out.printf("Total Monthly Payroll: $%.2f\n", calculatePayroll());
        
        if (employeeCount > 0) {
            System.out.println("\nEmployees:");
            System.out.println("-".repeat(70));
            System.out.printf("%-10s %-20s %-15s %15s\n", 
                "ID", "Name", "Department", "Monthly Salary");
            System.out.println("-".repeat(70));
            
            for (int i = 0; i < employeeCount; i++) {
                Employee emp = employees[i];
                System.out.printf("%-10d %-20s %-15s $%14.2f\n", 
                    emp.getEmpId(), 
                    emp.getName(), 
                    emp.getDepartment(),
                    emp.getSalary());
            }
        } else {
            System.out.println("No employees in this department.");
        }
    }
    
    public String getDeptName() { return deptName; }
    public int getEmployeeCount() { return employeeCount; }
    public Employee[] getEmployees() { return employees; }
}

public class EmployeeManagementSystem {
    private static Scanner scanner = new Scanner(System.in);
    private static Department[] departments = new Department[10];
    private static int deptCount = 0;
    
    public static void main(String[] args) {
        System.out.println("=== " + Employee.getCompanyName() + " - EMPLOYEE MANAGEMENT SYSTEM ===");
        
        createSampleData();
        
        boolean running = true;
        
        while (running) {
            displayMainMenu();
            System.out.print("\nEnter your choice (1-6): ");
            int choice = getIntInput(1, 6);
            
            switch (choice) {
                case 1:
                    addEmployee();
                    break;
                case 2:
                    displayAllEmployees();
                    break;
                case 3:
                    searchEmployee();
                    break;
                case 4:
                    departmentStatistics();
                    break;
                case 5:
                    companyStatistics();
                    break;
                case 6:
                    System.out.println("\nThank you for using the Employee Management System. Goodbye!");
                    running = false;
                    break;
            }
            
            if (running) {
                System.out.println("\nPress Enter to continue...");
                scanner.nextLine();
                scanner.nextLine();
            }
        }
        
        scanner.close();
    }
    
    private static void displayMainMenu() {
        System.out.println("\n=== MAIN MENU ===");
        System.out.println("1. Add Employee");
        System.out.println("2. Display All Employees");
        System.out.println("3. Search Employee");
        System.out.println("4. Department Statistics");
        System.out.println("5. Company Statistics");
        System.out.println("6. Exit");
    }
    
    private static void addEmployee() {
        if (deptCount == 0) {
            System.out.println("No departments available. Please create a department first.");
            return;
        }
        
        System.out.println("\n=== Add New Employee ===");
        
        System.out.print("Enter Employee ID: ");
        int empId = getIntInput(1, Integer.MAX_VALUE);
        
        scanner.nextLine();
        
        System.out.print("Enter Employee Name: ");
        String name = scanner.nextLine();
        
        System.out.println("\nAvailable Departments:");
        for (int i = 0; i < deptCount; i++) {
            System.out.println((i+1) + ". " + departments[i].getDeptName());
        }
        
        System.out.print("Select Department (1-" + deptCount + "): ");
        int deptChoice = getIntInput(1, deptCount) - 1;
        
        System.out.print("Enter Monthly Salary: $");
        double salary = getDoubleInput(0, Double.MAX_VALUE);
        
        Employee emp = new Employee(empId, name, departments[deptChoice].getDeptName(), salary);
        
        if (departments[deptChoice].addEmployee(emp)) {
            System.out.println("\nEmployee added successfully to " + departments[deptChoice].getDeptName() + " department!");
        } else {
            System.out.println("\nFailed to add employee. Department might be full.");
        }
    }
    
    private static void displayAllEmployees() {
        System.out.println("\n=== All Employees ===");
        
        boolean hasEmployees = false;
        
        for (int i = 0; i < deptCount; i++) {
            if (departments[i].getEmployeeCount() > 0) {
                departments[i].displayDepartmentInfo();
                hasEmployees = true;
            }
        }
        
        if (!hasEmployees) {
            System.out.println("No employees found in any department.");
        }
    }
    
    private static void searchEmployee() {
        System.out.println("\n=== Search Employee ===");
        System.out.println("1. Search by Employee ID");
        System.out.println("2. Search by Name");
        System.out.print("Choose search option (1-2): ");
        
        int choice = getIntInput(1, 2);
        scanner.nextLine();
        
        if (choice == 1) {
            System.out.print("Enter Employee ID: ");
            int empId = getIntInput(1, Integer.MAX_VALUE);
            searchEmployeeById(empId);
        } else {
            System.out.print("Enter Employee Name: ");
            String name = scanner.nextLine();
            searchEmployeeByName(name);
        }
    }
    
    private static void searchEmployeeById(int empId) {
        boolean found = false;
        
        for (int i = 0; i < deptCount; i++) {
            Employee[] employees = departments[i].getEmployees();
            for (int j = 0; j < departments[i].getEmployeeCount(); j++) {
                if (employees[j].getEmpId() == empId) {
                    employees[j].displayEmployee();
                    found = true;
                    return;
                }
            }
        }
        
        if (!found) {
            System.out.println("\nNo employee found with ID: " + empId);
        }
    }
    
    private static void searchEmployeeByName(String name) {
        boolean found = false;
        
        System.out.println("\nSearch Results for \"" + name + "\":");
        System.out.println("-".repeat(70));
        
        for (int i = 0; i < deptCount; i++) {
            Employee[] employees = departments[i].getEmployees();
            for (int j = 0; j < departments[i].getEmployeeCount(); j++) {
                if (employees[j].getName().toLowerCase().contains(name.toLowerCase())) {
                    System.out.printf("ID: %-5d | Name: %-20s | Department: %-15s | Salary: $%.2f\n",
                            employees[j].getEmpId(),
                            employees[j].getName(),
                            employees[j].getDepartment(),
                            employees[j].getSalary());
                    found = true;
                }
            }
        }
        
        if (!found) {
            System.out.println("No employees found with name containing: " + name);
        }
    }
    
    private static void departmentStatistics() {
        if (deptCount == 0) {
            System.out.println("No departments available.");
            return;
        }
        
        System.out.println("\n=== Department Statistics ===");
        for (int i = 0; i < deptCount; i++) {
            System.out.println((i+1) + ". " + departments[i].getDeptName() + 
                             " (" + departments[i].getEmployeeCount() + " employees)");
        }
        
        System.out.print("\nSelect a department (1-" + deptCount + ") or 0 for all departments: ");
        int choice = getIntInput(0, deptCount);
        
        if (choice == 0) {
            for (int i = 0; i < deptCount; i++) {
                departments[i].displayDepartmentInfo();
                Employee highestPaid = departments[i].findHighestPaid();
                if (highestPaid != null) {
                    System.out.println("\nHighest Paid Employee in " + departments[i].getDeptName() + ":");
                    System.out.println("-".repeat(50));
                    System.out.printf("Name: %s | Salary: $%.2f\n", 
                            highestPaid.getName(), highestPaid.getSalary());
                }
                System.out.println();
            }
        } else {
            int deptIndex = choice - 1;
            departments[deptIndex].displayDepartmentInfo();
            
            Employee highestPaid = departments[deptIndex].findHighestPaid();
            if (highestPaid != null) {
                System.out.println("\nHighest Paid Employee in " + departments[deptIndex].getDeptName() + ":");
                System.out.println("-".repeat(50));
                highestPaid.displayEmployee();
            }
        }
    }
    
    private static void companyStatistics() {
        System.out.println("\n=== Company Statistics ===");
        System.out.println("Company Name: " + Employee.getCompanyName());
        System.out.println("Total Departments: " + deptCount);
        System.out.println("Total Employees: " + Employee.getTotalEmployees());
        
        double totalPayroll = 0;
        int totalEmployees = 0;
        Employee highestPaid = null;
        
        for (int i = 0; i < deptCount; i++) {
            totalPayroll += departments[i].calculatePayroll();
            totalEmployees += departments[i].getEmployeeCount();
            
            Employee deptHighest = departments[i].findHighestPaid();
            if (deptHighest != null && (highestPaid == null || 
                deptHighest.getSalary() > highestPaid.getSalary())) {
                highestPaid = deptHighest;
            }
        }
        
        System.out.printf("Total Monthly Payroll: $%.2f\n", totalPayroll);
        System.out.printf("Average Employee Salary: $%.2f\n", 
                totalEmployees > 0 ? totalPayroll / totalEmployees : 0);
        
        if (highestPaid != null) {
            System.out.println("\nHighest Paid Employee in Company:");
            System.out.println("-".repeat(50));
            highestPaid.displayEmployee();
        }
    }
    
    private static void createSampleData() {
        departments[deptCount++] = new Department("Engineering");
        departments[deptCount++] = new Department("Marketing");
        departments[deptCount++] = new Department("Human Resources");
        
        departments[0].addEmployee(new Employee(101, "John Smith", "Engineering", 85000.0 / 12));
        departments[0].addEmployee(new Employee(102, "Sarah Johnson", "Engineering", 95000.0 / 12));
        departments[1].addEmployee(new Employee(201, "Michael Brown", "Marketing", 75000.0 / 12));
        departments[1].addEmployee(new Employee(202, "Emily Davis", "Marketing", 78000.0 / 12));
        departments[2].addEmployee(new Employee(301, "Robert Wilson", "Human Resources", 70000.0 / 12));
    }
    
    private static int getIntInput(int min, int max) {
        while (true) {
            try {
                int input = Integer.parseInt(scanner.nextLine());
                if (input >= min && input <= max) {
                    return input;
                }
                System.out.printf("Please enter a number between %d and %d: ", min, max);
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a number: ");
            }
        }
    }
    
    private static double getDoubleInput(double min, double max) {
        while (true) {
            try {
                double input = Double.parseDouble(scanner.nextLine());
                if (input >= min && input <= max) {
                    return input;
                }
                System.out.printf("Please enter a number between %.2f and %.2f: ", min, max);
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a number: ");
            }
        }
    }
}
