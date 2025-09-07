import java.util.*;

// Base Vehicle class
class Vehicle {
    protected String vehicleId;
    protected String brand;
    protected String model;
    protected int year;
    protected double mileage;
    protected String fuelType;
    protected String currentStatus;
    protected Driver assignedDriver;

    // Static variables
    static int totalVehicles = 0;
    static double fleetValue = 0;
    static String companyName = "TranspoCo";
    static double totalFuelConsumption = 0;

    // Constructor
    public Vehicle(String vehicleId, String brand, String model, int year, double mileage, String fuelType) {
        this.vehicleId = vehicleId;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.mileage = mileage;
        this.fuelType = fuelType;
        this.currentStatus = "Available";
        totalVehicles++;
    }

    public void assignDriver(Driver driver) {
        this.assignedDriver = driver;
        driver.setAssignedVehicle(this);
        this.currentStatus = "Assigned";
        System.out.println("Driver " + driver.driverName + " assigned to vehicle " + vehicleId);
    }

    public void scheduleMaintenance() {
        this.currentStatus = "Under Maintenance";
        System.out.println(vehicleId + " scheduled for maintenance.");
    }

    public void updateMileage(double additionalMileage) {
        this.mileage += additionalMileage;
        System.out.println(vehicleId + " mileage updated to " + mileage + " km.");
    }

    public void checkServiceDue() {
        if (mileage > 50000) {
            System.out.println(vehicleId + " requires servicing soon.");
        } else {
            System.out.println(vehicleId + " is in good condition.");
        }
    }

    public double calculateRunningCost(double fuelConsumed, double fuelPrice) {
        double cost = fuelConsumed * fuelPrice;
        totalFuelConsumption += fuelConsumed;
        return cost;
    }

    public void displayInfo() {
        System.out.println("[" + vehicleId + "] " + brand + " " + model + " (" + year + "), Fuel: " + fuelType + 
                           ", Mileage: " + mileage + ", Status: " + currentStatus);
    }
}

// Car class
class Car extends Vehicle {
    private int seatingCapacity;

    public Car(String vehicleId, String brand, String model, int year, double mileage, String fuelType, int seatingCapacity) {
        super(vehicleId, brand, model, year, mileage, fuelType);
        this.seatingCapacity = seatingCapacity;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("  Seating Capacity: " + seatingCapacity);
    }
}

// Bus class
class Bus extends Vehicle {
    private int seatingCapacity;

    public Bus(String vehicleId, String brand, String model, int year, double mileage, String fuelType, int seatingCapacity) {
        super(vehicleId, brand, model, year, mileage, fuelType);
        this.seatingCapacity = seatingCapacity;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("  Bus Capacity: " + seatingCapacity + " passengers");
    }
}

// Truck class
class Truck extends Vehicle {
    private double loadCapacity;

    public Truck(String vehicleId, String brand, String model, int year, double mileage, String fuelType, double loadCapacity) {
        super(vehicleId, brand, model, year, mileage, fuelType);
        this.loadCapacity = loadCapacity;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("  Load Capacity: " + loadCapacity + " tons");
    }
}

// Driver class
class Driver {
    String driverId;
    String driverName;
    String licenseType;
    Vehicle assignedVehicle;
    int totalTrips;

    public Driver(String driverId, String driverName, String licenseType) {
        this.driverId = driverId;
        this.driverName = driverName;
        this.licenseType = licenseType;
        this.totalTrips = 0;
    }

    public void setAssignedVehicle(Vehicle vehicle) {
        this.assignedVehicle = vehicle;
    }

    public void recordTrip(double distance, double fuelConsumed, double fuelPrice) {
        if (assignedVehicle == null) {
            System.out.println(driverName + " is not assigned to any vehicle.");
            return;
        }
        assignedVehicle.updateMileage(distance);
        double cost = assignedVehicle.calculateRunningCost(fuelConsumed, fuelPrice);
        totalTrips++;
        System.out.println("Trip recorded for driver " + driverName + ". Distance: " + distance + " km, Cost: $" + cost);
    }
}

// Fleet management utility
class FleetManagement {
    public static void getFleetUtilization(Vehicle[] vehicles) {
        int assigned = 0;
        for (Vehicle v : vehicles) {
            if (v.currentStatus.equals("Assigned")) assigned++;
        }
        System.out.println("Fleet Utilization: " + (assigned * 100.0 / vehicles.length) + "%");
    }

    public static void calculateTotalMaintenanceCost(Vehicle[] vehicles) {
        double maintenanceCost = vehicles.length * 500; // assume flat rate
        System.out.println("Total Maintenance Cost: $" + maintenanceCost);
    }

    public static void getVehiclesByType(Vehicle[] vehicles, String type) {
        System.out.println("Vehicles of type: " + type);
        for (Vehicle v : vehicles) {
            if (type.equalsIgnoreCase("Car") && v instanceof Car) v.displayInfo();
            if (type.equalsIgnoreCase("Bus") && v instanceof Bus) v.displayInfo();
            if (type.equalsIgnoreCase("Truck") && v instanceof Truck) v.displayInfo();
        }
    }
}

// Main class
public class FleetSystem {
    public static void main(String[] args) {
        Vehicle v1 = new Car("C101", "Toyota", "Corolla", 2018, 30000, "Petrol", 5);
        Vehicle v2 = new Bus("B201", "Volvo", "B9R", 2020, 45000, "Diesel", 50);
        Vehicle v3 = new Truck("T301", "Tata", "LPT", 2019, 60000, "Diesel", 20);

        Driver d1 = new Driver("D01", "John Doe", "LMV");
        Driver d2 = new Driver("D02", "Alice Smith", "HMV");

        Vehicle[] fleet = {v1, v2, v3};

        v1.displayInfo();
        v2.displayInfo();
        v3.displayInfo();

        v1.assignDriver(d1);
        v3.assignDriver(d2);

        d1.recordTrip(150, 10, 90);
        d2.recordTrip(300, 40, 90);

        v2.checkServiceDue();
        v3.checkServiceDue();

        FleetManagement.getFleetUtilization(fleet);
        FleetManagement.calculateTotalMaintenanceCost(fleet);
        FleetManagement.getVehiclesByType(fleet, "Truck");

        System.out.println("Total Vehicles: " + Vehicle.totalVehicles);
        System.out.println("Total Fuel Consumption: " + Vehicle.totalFuelConsumption + " liters");
        System.out.println("Company Name: " + Vehicle.companyName);
    }
}
