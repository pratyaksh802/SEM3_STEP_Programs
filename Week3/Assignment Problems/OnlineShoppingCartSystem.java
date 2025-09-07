import java.util.*;

class Product {
    String productId;
    String productName;
    double price;
    String category;
    int stockQuantity;
    static int totalProducts = 0;
    static String[] categories = {"Electronics", "Clothing", "Books", "Home", "Sports"};

    Product(String productId, String productName, double price, String category, int stockQuantity) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.category = category;
        this.stockQuantity = stockQuantity;
        totalProducts++;
    }

    public static Product findProductById(Product[] products, String productId) {
        for (Product p : products) {
            if (p != null && p.productId.equals(productId)) {
                return p;
            }
        }
        return null;
    }

    public static List<Product> getProductsByCategory(Product[] products, String category) {
        List<Product> result = new ArrayList<>();
        for (Product p : products) {
            if (p != null && p.category.equalsIgnoreCase(category)) {
                result.add(p);
            }
        }
        return result;
    }
}

class ShoppingCart {
    String cartId;
    String customerName;
    Product[] products;
    int[] quantities;
    double cartTotal;
    int itemCount;

    ShoppingCart(String cartId, String customerName, int size) {
        this.cartId = cartId;
        this.customerName = customerName;
        this.products = new Product[size];
        this.quantities = new int[size];
        this.cartTotal = 0;
        this.itemCount = 0;
    }

    public void addProduct(Product product, int quantity) {
        if (product.stockQuantity >= quantity) {
            products[itemCount] = product;
            quantities[itemCount] = quantity;
            product.stockQuantity -= quantity;
            itemCount++;
            calculateTotal();
            System.out.println(quantity + " " + product.productName + " added to cart.");
        } else {
            System.out.println("Insufficient stock for " + product.productName);
        }
    }

    public void removeProduct(String productId) {
        for (int i = 0; i < itemCount; i++) {
            if (products[i].productId.equals(productId)) {
                products[i].stockQuantity += quantities[i];
                System.out.println(products[i].productName + " removed from cart.");
                for (int j = i; j < itemCount - 1; j++) {
                    products[j] = products[j + 1];
                    quantities[j] = quantities[j + 1];
                }
                products[itemCount - 1] = null;
                quantities[itemCount - 1] = 0;
                itemCount--;
                calculateTotal();
                return;
            }
        }
        System.out.println("Product not found in cart.");
    }

    public void calculateTotal() {
        cartTotal = 0;
        for (int i = 0; i < itemCount; i++) {
            cartTotal += products[i].price * quantities[i];
        }
    }

    public void displayCart() {
        System.out.println("\n--- Cart for " + customerName + " ---");
        if (itemCount == 0) {
            System.out.println("Cart is empty.");
            return;
        }
        for (int i = 0; i < itemCount; i++) {
            System.out.println(products[i].productName + " | Qty: " + quantities[i] + " | Price: " + products[i].price);
        }
        System.out.println("Cart Total: " + cartTotal);
    }

    public void checkout() {
        if (itemCount == 0) {
            System.out.println("Cart is empty. Cannot checkout.");
            return;
        }
        displayCart();
        System.out.println("Checkout successful. Total amount paid: " + cartTotal);
        itemCount = 0;
        products = new Product[products.length];
        quantities = new int[quantities.length];
        cartTotal = 0;
    }
}

public class OnlineShoppingCartSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Product[] products = {
            new Product("P1", "Laptop", 60000, "Electronics", 5),
            new Product("P2", "Smartphone", 25000, "Electronics", 10),
            new Product("P3", "T-Shirt", 800, "Clothing", 20),
            new Product("P4", "Jeans", 1500, "Clothing", 15),
            new Product("P5", "Novel", 500, "Books", 30),
            new Product("P6", "Cookbook", 700, "Books", 25),
            new Product("P7", "Vacuum Cleaner", 3500, "Home", 8),
            new Product("P8", "Mixer", 2200, "Home", 12),
            new Product("P9", "Football", 1200, "Sports", 10),
            new Product("P10", "Tennis Racket", 3000, "Sports", 7)
        };

        ShoppingCart cart = new ShoppingCart("CART1", "Customer1", 20);

        while (true) {
            System.out.println("\n--- Online Shopping Menu ---");
            System.out.println("1. Browse All Products");
            System.out.println("2. Browse by Category");
            System.out.println("3. Add Product to Cart");
            System.out.println("4. Remove Product from Cart");
            System.out.println("5. View Cart");
            System.out.println("6. Checkout");
            System.out.println("7. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    for (Product p : products) {
                        System.out.println(p.productId + " | " + p.productName + " | " + p.price + " | " + p.category + " | Stock: " + p.stockQuantity);
                    }
                    break;
                case 2:
                    System.out.print("Enter category: ");
                    sc.nextLine();
                    String cat = sc.nextLine();
                    List<Product> catProducts = Product.getProductsByCategory(products, cat);
                    if (catProducts.isEmpty()) {
                        System.out.println("No products in this category.");
                    } else {
                        for (Product p : catProducts) {
                            System.out.println(p.productId + " | " + p.productName + " | " + p.price + " | Stock: " + p.stockQuantity);
                        }
                    }
                    break;
                case 3:
                    System.out.print("Enter Product ID: ");
                    sc.nextLine();
                    String pid = sc.nextLine();
                    Product prod = Product.findProductById(products, pid);
                    if (prod != null) {
                        System.out.print("Enter Quantity: ");
                        int qty = sc.nextInt();
                        cart.addProduct(prod, qty);
                    } else {
                        System.out.println("Product not found.");
                    }
                    break;
                case 4:
                    System.out.print("Enter Product ID to remove: ");
                    sc.nextLine();
                    String rid = sc.nextLine();
                    cart.removeProduct(rid);
                    break;
                case 5:
                    cart.displayCart();
                    break;
                case 6:
                    cart.checkout();
                    break;
                case 7:
                    System.out.println("Exiting system. Thank you for shopping!");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
