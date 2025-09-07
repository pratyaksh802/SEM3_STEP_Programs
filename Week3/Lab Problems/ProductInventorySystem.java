class Product {
    private String productId;
    private String productName;
    private double price;
    private int quantity;
    private String supplierName;
    private String category;
    private static int totalProducts = 0;
    private static double totalInventoryValue = 0;
    private static int lowStockCount = 0;
    private static String[] categories = new String[100];
    private static int categoryCount = 0;

    Product(String productName, double price, int quantity, String supplierName, String category) {
        this.productId = "P" + String.format("%03d", ++totalProducts);
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
        this.supplierName = supplierName;
        this.category = category;
        if (!categoryExists(category)) {
            categories[categoryCount++] = category;
        }
    }

    private boolean categoryExists(String cat) {
        for (int i = 0; i < categoryCount; i++) {
            if (categories[i].equals(cat)) return true;
        }
        return false;
    }

    public void addStock(int quantity) {
        if (quantity > 0) {
            this.quantity += quantity;
        }
    }

    public void reduceStock(int quantity) {
        if (quantity > 0 && this.quantity >= quantity) {
            this.quantity -= quantity;
        }
    }

    public boolean isLowStock() {
        return quantity < 10;
    }

    public double calculateProductValue() {
        return price * quantity;
    }

    public void updatePrice(double newPrice) {
        if (newPrice > 0) {
            this.price = newPrice;
        }
    }

    public void displayProductInfo() {
        System.out.println(productId + " " + productName + " Price:" + price + " Qty:" + quantity + " Supplier:" + supplierName + " Category:" + category + " Value:" + calculateProductValue());
    }

    public static double calculateTotalInventoryValue(Product[] products) {
        totalInventoryValue = 0;
        for (Product p : products) {
            if (p != null) totalInventoryValue += p.calculateProductValue();
        }
        return totalInventoryValue;
    }

    public static void findLowStockProducts(Product[] products) {
        lowStockCount = 0;
        System.out.println("Low Stock Products (<10):");
        for (Product p : products) {
            if (p != null && p.isLowStock()) {
                p.displayProductInfo();
                lowStockCount++;
            }
        }
        if (lowStockCount == 0) System.out.println("None");
    }

    public static void generateInventoryReport(Product[] products) {
        System.out.println("\nInventory Report:");
        for (Product p : products) {
            if (p != null) p.displayProductInfo();
        }
        System.out.println("Total Inventory Value: " + calculateTotalInventoryValue(products));
        findLowStockProducts(products);
        System.out.println("Categories:");
        for (int i = 0; i < categoryCount; i++) {
            System.out.println("- " + categories[i]);
        }
    }

    public static Product searchProduct(Product[] products, String productName) {
        for (Product p : products) {
            if (p != null && p.productName.equalsIgnoreCase(productName)) return p;
        }
        return null;
    }
}

public class ProductInventorySystem {
    public static void main(String[] args) {
        Product[] products = new Product[5];
        products[0] = new Product("Laptop", 50000, 8, "Dell Supplies", "Electronics");
        products[1] = new Product("Mouse", 800, 50, "HP Accessories", "Electronics");
        products[2] = new Product("Desk", 7000, 5, "Ikea", "Furniture");
        products[3] = new Product("Chair", 3500, 15, "Ikea", "Furniture");
        products[4] = new Product("Notebook", 50, 200, "Classmate", "Stationery");

        products[0].addStock(5);
        products[2].reduceStock(2);
        products[1].updatePrice(900);

        Product searched = Product.searchProduct(products, "Desk");
        System.out.println("\nSearch Result:");
        if (searched != null) searched.displayProductInfo();
        else System.out.println("Product not found");

        Product.generateInventoryReport(products);
    }
}
