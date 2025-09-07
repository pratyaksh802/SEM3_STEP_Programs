import java.util.Scanner;

class Book {
    private String title;
    private String author;
    private String isbn;
    private double price;
    private int quantity;
    
    public Book(String title, String author, String isbn, double price, int quantity) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.price = price;
        this.quantity = quantity;
    }
    
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getIsbn() { return isbn; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }
    
    public void setQuantity(int quantity) { this.quantity = quantity; }
    
    public double getTotalValue() {
        return price * quantity;
    }
    
    @Override
    public String toString() {
        return String.format("Title: %-20s | Author: %-15s | ISBN: %-13s | Price: $%-6.2f | Qty: %d | Total: $%.2f",
                title, author, isbn, price, quantity, getTotalValue());
    }
}

class Library {
    private String libraryName;
    private Book[] books;
    private int totalBooks;
    private static final int MAX_BOOKS = 100;
    
    public Library(String libraryName) {
        this.libraryName = libraryName;
        this.books = new Book[MAX_BOOKS];
        this.totalBooks = 0;
    }
    
    public boolean addBook(Book book) {
        if (totalBooks < MAX_BOOKS) {
            for (int i = 0; i < totalBooks; i++) {
                if (books[i].getIsbn().equals(book.getIsbn())) {
                    books[i].setQuantity(books[i].getQuantity() + book.getQuantity());
                    return true;
                }
            }
            books[totalBooks++] = book;
            return true;
        }
        return false;
    }
    
    public Book[] searchByTitle(String title) {
        Book[] results = new Book[totalBooks];
        int count = 0;
        
        for (int i = 0; i < totalBooks; i++) {
            if (books[i].getTitle().toLowerCase().contains(title.toLowerCase())) {
                results[count++] = books[i];
            }
        }
        
        Book[] finalResults = new Book[count];
        System.arraycopy(results, 0, finalResults, 0, count);
        return finalResults;
    }
    
    public Book[] searchByAuthor(String author) {
        Book[] results = new Book[totalBooks];
        int count = 0;
        
        for (int i = 0; i < totalBooks; i++) {
            if (books[i].getAuthor().toLowerCase().contains(author.toLowerCase())) {
                results[count++] = books[i];
            }
        }
        
        Book[] finalResults = new Book[count];
        System.arraycopy(results, 0, finalResults, 0, count);
        return finalResults;
    }
    
    public void displayInventory() {
        System.out.println("\n=== " + libraryName + " - Inventory ===");
        if (totalBooks == 0) {
            System.out.println("No books in the library.");
            return;
        }
        
        System.out.println(String.format("%-25s %-20s %-15s %-10s %-6s %-10s", 
                "Title", "Author", "ISBN", "Price", "Qty", "Total Value"));
        System.out.println("-".repeat(90));
        
        for (int i = 0; i < totalBooks; i++) {
            Book book = books[i];
            System.out.println(String.format("%-25s %-20s %-15s $%-9.2f %-6d $%.2f",
                    book.getTitle().substring(0, Math.min(20, book.getTitle().length())),
                    book.getAuthor().substring(0, Math.min(15, book.getAuthor().length())),
                    book.getIsbn(),
                    book.getPrice(),
                    book.getQuantity(),
                    book.getTotalValue()));
        }
        
        System.out.println("\nTotal Books: " + totalBooks);
        System.out.println("Total Inventory Value: $" + String.format("%.2f", calculateTotalValue()));
    }
    
    public double calculateTotalValue() {
        double total = 0;
        for (int i = 0; i < totalBooks; i++) {
            total += books[i].getTotalValue();
        }
        return total;
    }
    
    public int getTotalBooks() {
        return totalBooks;
    }
}

public class LibrarySystem {
    private static Scanner scanner = new Scanner(System.in);
    private static Library library = new Library("City Central Library");
    
    public static void main(String[] args) {
        addSampleBooks();
        
        boolean running = true;
        
        while (running) {
            displayMenu();
            System.out.print("\nEnter your choice (1-5): ");
            int choice = getIntInput(1, 5);
            
            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    searchBooks();
                    break;
                case 3:
                    library.displayInventory();
                    break;
                case 4:
                    System.out.printf("\nTotal value of all books: $%.2f\n", library.calculateTotalValue());
                    break;
                case 5:
                    System.out.println("\nThank you for using the Library System. Goodbye!");
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
    
    private static void displayMenu() {
        System.out.println("\n=== Library Management System ===");
        System.out.println("1. Add Book");
        System.out.println("2. Search Books");
        System.out.println("3. Display All Books");
        System.out.println("4. Calculate Total Value");
        System.out.println("5. Exit");
    }
    
    private static void addBook() {
        System.out.println("\n=== Add New Book ===");
        
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        
        System.out.print("Enter author: ");
        String author = scanner.nextLine();
        
        System.out.print("Enter ISBN: ");
        String isbn = scanner.nextLine();
        
        System.out.print("Enter price: $");
        double price = getDoubleInput(0, Double.MAX_VALUE);
        
        System.out.print("Enter quantity: ");
        int quantity = getIntInput(1, Integer.MAX_VALUE);
        
        Book newBook = new Book(title, author, isbn, price, quantity);
        if (library.addBook(newBook)) {
            System.out.println("\nBook added successfully!");
        } else {
            System.out.println("\nFailed to add book. Library might be full.");
        }
    }
    
    private static void searchBooks() {
        System.out.println("\n=== Search Books ===");
        System.out.println("1. Search by Title");
        System.out.println("2. Search by Author");
        System.out.print("Choose search option (1-2): ");
        
        int choice = getIntInput(1, 2);
        scanner.nextLine();
        
        Book[] results;
        
        if (choice == 1) {
            System.out.print("Enter title to search: ");
            String title = scanner.nextLine();
            results = library.searchByTitle(title);
        } else {
            System.out.print("Enter author to search: ");
            String author = scanner.nextLine();
            results = library.searchByAuthor(author);
        }
        
        if (results.length > 0) {
            System.out.println("\nSearch Results (" + results.length + " found):");
            System.out.println("-".repeat(100));
            for (Book book : results) {
                System.out.println(book);
            }
        } else {
            System.out.println("\nNo books found matching your search.");
        }
    }
    
    private static void addSampleBooks() {
        library.addBook(new Book("The Great Gatsby", "F. Scott Fitzgerald", "9780743273565", 12.99, 5));
        library.addBook(new Book("To Kill a Mockingbird", "Harper Lee", "9780061120084", 10.50, 3));
        library.addBook(new Book("1984", "George Orwell", "9780451524935", 9.99, 7));
        library.addBook(new Book("Pride and Prejudice", "Jane Austen", "9780141439518", 8.75, 4));
        library.addBook(new Book("The Hobbit", "J.R.R. Tolkien", "9780547928227", 14.95, 6));
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
