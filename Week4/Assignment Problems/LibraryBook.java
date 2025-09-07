class Book {
    String title;
    String author;
    String isbn;
    boolean isAvailable;

    // Default constructor
    public Book() {
        this("Untitled", "Unknown", "0000000000", true);
    }

    // Constructor with title and author
    public Book(String title, String author) {
        this(title, author, "Not Assigned", true);
    }

    // Full constructor
    public Book(String title, String author, String isbn, boolean isAvailable) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.isAvailable = isAvailable;
    }

    public void borrowBook() {
        if (isAvailable) {
            isAvailable = false;
            System.out.println("\u2713 " + title + " borrowed successfully.");
        } else {
            System.out.println("\u274C " + title + " is not available for borrowing.");
        }
    }

    public void returnBook() {
        if (!isAvailable) {
            isAvailable = true;
            System.out.println("\u2713 " + title + " returned successfully.");
        } else {
            System.out.println("\u26A0 " + title + " was not borrowed.");
        }
    }

    public void displayBookInfo() {
        System.out.println("\n=== Book Information ===");
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("ISBN: " + isbn);
        System.out.println("Status: " + (isAvailable ? "Available" : "Borrowed"));
        System.out.println("======================");
    }

    public static void main(String[] args) {
        // Create books using different constructors
        Book b1 = new Book();
        Book b2 = new Book("1984", "George Orwell");
        Book b3 = new Book("Clean Code", "Robert Martin", "1234567890", true);
        
        // Display initial book information
        b1.displayBookInfo();
        b2.displayBookInfo();
        b3.displayBookInfo();
        
        // Test borrowing and returning
        System.out.println("\n--- Testing Book Operations ---");
        b1.borrowBook();
        b2.borrowBook();
        b2.returnBook();  // Try to return a book that's already available
        b3.returnBook();  // Return a borrowed book
        
        // Display updated book information
        System.out.println("\n--- Updated Book Status ---");
        b1.displayBookInfo();
        b2.displayBookInfo();
        b3.displayBookInfo();
    }
}
