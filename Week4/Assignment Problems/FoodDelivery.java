class FoodOrder {
    String customerName;
    String foodItem;
    int quantity;
    double price;

    // Default constructor
    public FoodOrder() {
        this("Unknown", "Unknown Item", 0, 0.0);
    }

    // Constructor with food item
    public FoodOrder(String foodItem) {
        this("Customer", foodItem, 1, 100.0);
    }

    // Constructor with food item and quantity
    public FoodOrder(String foodItem, int quantity) {
        this("Customer", foodItem, quantity, quantity * 120.0);
    }

    // Full constructor
    public FoodOrder(String customerName, String foodItem, int quantity, double price) {
        this.customerName = customerName;
        this.foodItem = foodItem;
        this.quantity = quantity;
        this.price = price;
    }

    public void printBill() {
        System.out.println("\n=== Food Order Bill ===");
        System.out.println("Customer: " + customerName);
        System.out.println("Item: " + foodItem);
        System.out.println("Quantity: " + quantity);
        System.out.println("Total Price: Rs." + price);
        System.out.println("=======================");
    }

    public void applyDiscount(double discountPercent) {
        if (discountPercent > 0 && discountPercent <= 50) {
            double discount = (price * discountPercent) / 100;
            price -= discount;
            System.out.println("Applied " + discountPercent + "% discount. New price: Rs." + price);
        } else {
            System.out.println("Invalid discount percentage. Must be between 1-50%");
        }
    }

    public static void main(String[] args) {
        // Create orders using different constructors
        FoodOrder o1 = new FoodOrder();
        FoodOrder o2 = new FoodOrder("Burger");
        FoodOrder o3 = new FoodOrder("Pizza", 3);
        FoodOrder o4 = new FoodOrder("John", "Sandwich", 2, 250.0);
        
        // Print all orders
        o1.printBill();
        o2.printBill();
        o3.printBill();
        o4.printBill();
        
        // Test discount functionality
        System.out.println("\n--- Applying Discounts ---");
        o3.applyDiscount(10);  // Apply 10% discount
        o3.printBill();
        
        o4.applyDiscount(60);  // Invalid discount (too high)
        o4.applyDiscount(20);  // Valid 20% discount
        o4.printBill();
    }
}
