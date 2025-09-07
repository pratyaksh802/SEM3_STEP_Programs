class Vehicle {
    private String vehicleId;
    private String brand;
    private String model;
    private double rentPerDay;
    private boolean isAvailable;
    private int rentedDays;
    private static int totalVehicles = 0;
    private static double totalRevenue = 0;
    private static String companyName;
    private static int rentalDays = 0;

    Vehicle(String brand, String model, double rentPerDay) {
        this.vehicleId = "V" + String.format("%03d", ++totalVehicles);
        this.brand = brand;
        this.model = model;
        this.rentPerDay = rentPerDay;
        this.isAvailable = true;
        this.rentedDays = 0;
    }

    public boolean rentVehicle(int days) {
        if (isAvailable) {
            double rent = calculateRent(days);
            isAvailable = false;
            rentedDays += days;
            System.out.println(vehicleId + " rented for " + days + " days. Rent: " + rent);
            return true;
        } else {
            System.out.println(vehicleId + " is not available");
            return false;
        }
    }

    public void returnVehicle() {
        if (!isAvailable) {
            isAvailable = true;
            System.out.println(vehicleId + " has been returned");
        }
    }

    public double calculateRent(int days) {
        double amount = days * rentPerDay;
        totalRevenue += amount;
        rentalDays += days;
        return amount;
    }

    public void displayVehicleInfo() {
        System.out.println(vehicleId + " " + brand + " " + model + " Rent/Day:" + rentPerDay + " Available:" + (isAvailable ? "Yes" : "No") + " RentedDays:" + rentedDays);
    }

    public static void setCompanyName(String name) {
        companyName = name;
    }

    public static double getTotalRevenue() {
        return totalRevenue;
    }

    public static double getAverageRentPerDay() {
        return rentalDays == 0 ? 0 : totalRevenue / rentalDays;
    }

    public static void displayCompanyStats() {
        System.out.println("Company: " + companyName);
        System.out.println("Total Vehicles: " + totalVehicles);
        System.out.println("Total Revenue: " + totalRevenue);
        System.out.println("Total Rental Days: " + rentalDays);
        System.out.println("Average Rent per Day: " + getAverageRentPerDay());
    }
}

public class VehicleRentalSystem {
    public static void main(String[] args) {
        Vehicle.setCompanyName("FastRide Rentals");

        Vehicle v1 = new Vehicle("Toyota", "Corolla", 1500);
        Vehicle v2 = new Vehicle("Honda", "Civic", 1800);
        Vehicle v3 = new Vehicle("Ford", "EcoSport", 2000);

        v1.displayVehicleInfo();
        v2.displayVehicleInfo();
        v3.displayVehicleInfo();

        v1.rentVehicle(3);
        v2.rentVehicle(2);
        v2.rentVehicle(1);
        v1.returnVehicle();
        v2.returnVehicle();
        v3.rentVehicle(5);

        System.out.println("\nFinal Vehicle Info:");
        v1.displayVehicleInfo();
        v2.displayVehicleInfo();
        v3.displayVehicleInfo();

        System.out.println("\nCompany Stats:");
        Vehicle.displayCompanyStats();
    }
}
