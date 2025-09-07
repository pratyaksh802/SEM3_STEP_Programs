class Book {
    private String bookId;
    private String title;
    private String author;
    private boolean isAvailable;
    private static int totalBooks = 0;
    private static int availableBooks = 0;
    private static int counter = 1;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.bookId = generateBookId();
        this.isAvailable = true;
        totalBooks++;
        availableBooks++;
    }

    public boolean issueBook() {
        if (isAvailable) {
            isAvailable = false;
            availableBooks--;
            return true;
        }
        return false;
    }

    public void returnBook() {
        if (!isAvailable) {
            isAvailable = true;
            availableBooks++;
        }
    }

    public String getBookId() {
        return bookId;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void displayBookInfo() {
        System.out.printf("%-8s %-15s %-15s %-10s\n", bookId, title, author, (isAvailable ? "Yes" : "No"));
    }

    private static String generateBookId() {
        String id = String.format("B%03d", counter);
        counter++;
        return id;
    }

    public static int getTotalBooks() {
        return totalBooks;
    }

    public static int getAvailableBooks() {
        return availableBooks;
    }
}

class Member {
    private String memberId;
    private String memberName;
    private String[] booksIssued;
    private int bookCount;
    private static int counter = 1;

    public Member(String name) {
        this.memberName = name;
        this.memberId = generateMemberId();
        this.booksIssued = new String[5];
        this.bookCount = 0;
    }

    public void borrowBook(Book book) {
        if (bookCount < booksIssued.length && book.issueBook()) {
            booksIssued[bookCount] = book.getBookId();
            bookCount++;
            System.out.println(memberName + " borrowed " + book.getBookId());
        } else {
            System.out.println(memberName + " cannot borrow " + book.getBookId());
        }
    }

    public void returnBook(String bookId, Book[] books) {
        for (int i = 0; i < bookCount; i++) {
            if (booksIssued[i].equals(bookId)) {
                for (Book b : books) {
                    if (b.getBookId().equals(bookId)) {
                        b.returnBook();
                        System.out.println(memberName + " returned " + bookId);
                        booksIssued[i] = booksIssued[bookCount - 1];
                        booksIssued[bookCount - 1] = null;
                        bookCount--;
                        return;
                    }
                }
            }
        }
        System.out.println(memberName + " has not borrowed " + bookId);
    }

    private static String generateMemberId() {
        String id = String.format("M%03d", counter);
        counter++;
        return id;
    }

    public void displayMemberInfo() {
        System.out.print(memberId + " " + memberName + " Books: ");
        for (int i = 0; i < bookCount; i++) System.out.print(booksIssued[i] + " ");
        System.out.println();
    }
}

public class LibrarySystem {
    public static void main(String[] args) {
        Book[] books = new Book[3];
        books[0] = new Book("Java Basics", "James");
        books[1] = new Book("C Programming", "Dennis");
        books[2] = new Book("Python Guide", "Guido");

        Member[] members = new Member[2];
        members[0] = new Member("Alice");
        members[1] = new Member("Bob");

        System.out.println("Books in Library:");
        System.out.println("ID       Title            Author          Available");
        for (Book b : books) b.displayBookInfo();

        members[0].borrowBook(books[0]);
        members[1].borrowBook(books[0]);
        members[1].borrowBook(books[1]);

        members[0].displayMemberInfo();
        members[1].displayMemberInfo();

        members[0].returnBook("B001", books);
        members[1].borrowBook(books[0]);

        System.out.println("\nUpdated Books in Library:");
        for (Book b : books) b.displayBookInfo();

        System.out.println("\nTotal Books: " + Book.getTotalBooks());
        System.out.println("Available Books: " + Book.getAvailableBooks());
    }
}
