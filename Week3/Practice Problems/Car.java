public class Car {
    String brand;
    String model;
    int year;
    String color;
    boolean isRunning = false;

    Car (String brand, String model, int year, String color) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.color = color;
        // this.isRunning = isRunning;
    }

    void StartEngine() {
        this.isRunning = true;
        System.out.println(this.model + " has started.");
    }

    void StopEngine() {
        this.isRunning = false;
        System.out.println(this.model + " has stopped.");
    }

    void displayInfo() {
        System.out.println("Car Information:");
        System.out.println("Brand: " + this.brand);
        System.out.println("Model: " + this.model);
        System.out.println("Year: " + this.year);
        System.out.println("Color: " + this.color);
        System.out.println("Is Running: " + this.isRunning);
    }


    int getAge() {
        return 2025 - this.year;
    }

    public static void main(String[] args) {
        Car c1 = new Car("Toyota", "Camry", 2020, "Blue");
        Car c2 = new Car("Honda", "Civic", 2018, "Red");
        Car c3 = new Car("Ford", "Mustang", 2021, "Black");

        c1.displayInfo();
        c1.StartEngine();
        c1.StopEngine();
        c1.getAge();
        System.err.println("--------------------------");
        c2.displayInfo();
        c2.StartEngine();
        c2.StopEngine();
        c2.getAge();
        System.err.println("--------------------------");
        c3.displayInfo();
        c3.StartEngine();
        c3.StopEngine();
        c3.getAge();
    }
}

