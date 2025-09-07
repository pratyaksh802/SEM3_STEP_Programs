public class Vehicle {
    protected String make;
    protected String model;
    protected int year;
    protected double fuelLevel;
    
    public Vehicle(String make, String model, int year) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.fuelLevel = 0.0;
    }
    
    public void startVehicle() {
        if (fuelLevel > 0) {
            System.out.println("Starting the " + year + " " + make + " " + model);
        } else {
            System.out.println("Cannot start - No fuel!");
        }
    }
    
    public void stopVehicle() {
        System.out.println("Stopping the " + year + " " + make + " " + model);
    }
    
    public void refuel(double amount) {
        if (amount > 0) {
            fuelLevel += amount;
            System.out.println("Added " + amount + " liters of fuel. Current fuel: " + fuelLevel + "L");
        } else {
            System.out.println("Invalid fuel amount");
        }
    }
    
    public void displayVehicleInfo() {
        System.out.println("\n--- Vehicle Information ---");
        System.out.println("Make: " + make);
        System.out.println("Model: " + model);
        System.out.println("Year: " + year);
        System.out.println("Fuel Level: " + fuelLevel + "L");
    }
    
    public String getMake() { return make; }
    public String getModel() { return model; }
    public int getYear() { return year; }
    public double getFuelLevel() { return fuelLevel; }
    
    public static void main(String[] args) {
        Vehicle car = new Car("Toyota", "Camry", 2022, 4, 5);
        Vehicle truck = new Truck("Ford", "F-150", 2021, 2000.0, 4);
        Vehicle motorcycle = new Motorcycle("Harley-Davidson", "Sportster", 2023, false);
        
        Vehicle[] garage = {car, truck, motorcycle};
        
        System.out.println("=== Starting All Vehicles ===");
        for (Vehicle vehicle : garage) {
            vehicle.refuel(10.0);
            vehicle.startVehicle();
            vehicle.displayVehicleInfo();
            System.out.println();
        }
        
        System.out.println("\n=== Vehicle-Specific Behaviors ===");
        ((Car)car).openSunroof();
        ((Truck)truck).loadCargo(1500.0);
        ((Motorcycle)motorcycle).doWheelie();
    }
}

class Car extends Vehicle {
    private int numberOfDoors;
    private int passengerCapacity;
    
    public Car(String make, String model, int year, int numberOfDoors, int passengerCapacity) {
        super(make, model, year);
        this.numberOfDoors = numberOfDoors;
        this.passengerCapacity = passengerCapacity;
    }
    
    public void openSunroof() {
        System.out.println("Opening the sunroof of " + make + " " + model);
    }
    
    @Override
    public void displayVehicleInfo() {
        super.displayVehicleInfo();
        System.out.println("Type: Car");
        System.out.println("Doors: " + numberOfDoors);
        System.out.println("Passenger Capacity: " + passengerCapacity);
    }
}

class Truck extends Vehicle {
    private double cargoCapacity;
    private int numberOfAxles;
    
    public Truck(String make, String model, int year, double cargoCapacity, int numberOfAxles) {
        super(make, model, year);
        this.cargoCapacity = cargoCapacity;
        this.numberOfAxles = numberOfAxles;
    }
    
    public void loadCargo(double weight) {
        if (weight <= cargoCapacity) {
            System.out.println("Loaded " + weight + " kg of cargo into " + make + " " + model);
        } else {
            System.out.println("Cargo exceeds capacity!");
        }
    }
    
    @Override
    public void displayVehicleInfo() {
        super.displayVehicleInfo();
        System.out.println("Type: Truck");
        System.out.println("Cargo Capacity: " + cargoCapacity + " kg");
        System.out.println("Number of Axles: " + numberOfAxles);
    }
}

class Motorcycle extends Vehicle {
    private boolean hasSideCar;
    
    public Motorcycle(String make, String model, int year, boolean hasSideCar) {
        super(make, model, year);
        this.hasSideCar = hasSideCar;
    }
    
    public void doWheelie() {
        System.out.println("Performing a wheelie on the " + make + " " + model + "!");
    }
    
    @Override
    public void displayVehicleInfo() {
        super.displayVehicleInfo();
        System.out.println("Type: Motorcycle");
        System.out.println("Has Sidecar: " + (hasSideCar ? "Yes" : "No"));
    }
}
