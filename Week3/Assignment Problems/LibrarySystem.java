import java.util.*;

class Book {
    private String bookId, title, author, isbn, category, issueDate, dueDate;
    private boolean isIssued;
    static int totalBooks = 0;
    static String libraryName = "City Central Library";
    static double finePerDay = 5.0;
    static int maxBooksAllowed = 3;

    public Book(String bookId, String title, String author, String isbn, String category) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.category = category;
        this.isIssued = false;
        this.issueDate = "";
        this.dueDate = "";
        totalBooks++;
    }

    public String getBookId() { return bookId; }
    public String getTitle() { return title; }
    public boolean isIssued() { return isIssued; }

    public void issue(String issueDate, String dueDate) {
        isIssued = true;
        this.issueDate = issueDate;
        this.dueDate = dueDate;
    }

    public void returned() {
        isIssued = false;
        this.issueDate = "";
        this.dueDate = "";
    }

    public String getDueDate() { return dueDate; }

    public void displayInfo() {
        System.out.println("Book ID: " + bookId + " | Title: " + title + " | Author: " + author +
                " | Category: " + category + " | Issued: " + (isIssued ? "Yes" : "No"));
    }
}

class Member {
    private String memberId, memberName, memberType, membershipDate;
    private Book[] booksIssued;
    private int bookCount;
    private double totalFines;
    static int totalMembers = 0;

    public Member(String memberId, String memberName, String memberType, String membershipDate) {
        this.memberId = memberId;
        this.memberName = memberName;
        this.memberType = memberType;
        this.membershipDate = membershipDate;
        this.booksIssued = new Book[Book.maxBooksAllowed];
        this.bookCount = 0;
        this.totalFines = 0.0;
        totalMembers++;
    }

    public void issueBook(Book book, String issueDate, String dueDate) {
        if (bookCount >= Book.maxBooksAllowed) {
            System.out.println(memberName + " cannot issue more than " + Book.maxBooksAllowed + " books.");
            return;
        }
        if (book.isIssued()) {
            System.out.println("Book already issued: " + book.getTitle());
            return;
        }
        booksIssued[bookCount++] = book;
        book.issue(issueDate, dueDate);
        System.out.println(memberName + " issued book: " + book.getTitle());
    }

    public void returnBook(String bookId, String returnDate) {
        for (int i = 0; i < bookCount; i++) {
            if (booksIssued[i] != null && booksIssued[i].getBookId().equals(bookId)) {
                Book book = booksIssued[i];
                int overdueDays = calculateOverdueDays(book.getDueDate(), returnDate);
                if (overdueDays > 0) {
                    double fine = overdueDays * Book.finePerDay;
                    totalFines += fine;
                    System.out.println("Fine for overdue: Rs. " + fine);
                }
                book.returned();
                booksIssued[i] = null;
                System.out.println(memberName + " returned book: " + book.getTitle());
                return;
            }
        }
        System.out.println("Book not found in issued list.");
    }

    private int calculateOverdueDays(String dueDate, String returnDate) {
        // Very simple: assume dates are integers "20250101", "20250105"
        try {
            int due = Integer.parseInt(dueDate);
            int ret = Integer.parseInt(returnDate);
            return Math.max(0, ret - due);
        } catch (Exception e) {
            return 0;
        }
    }

    public void displayMemberInfo() {
        System.out.println("Member ID: " + memberId + " | Name: " + memberName +
                " | Type: " + memberType + " | Books Issued: " + bookCount +
                " | Total Fines: Rs. " + totalFines);
    }
}

public class LibrarySystem {
    public static void main(String[] args) {
        Book b1 = new Book("B001", "Java Basics", "James Gosling", "ISBN001", "Programming");
        Book b2 = new Book("B002", "Operating Systems", "Silberschatz", "ISBN002", "CS");
        Book b3 = new Book("B003", "Data Structures", "Mark Allen", "ISBN003", "CS");

        Member m1 = new Member("M001", "Alice", "Student", "20250101");
        Member m2 = new Member("M002", "Bob", "Faculty", "20250102");

        System.out.println("\n--- Book Info ---");
        b1.displayInfo();
        b2.displayInfo();
        b3.displayInfo();

        System.out.println("\n--- Transactions ---");
        m1.issueBook(b1, "20250101", "20250105");
        m1.issueBook(b2, "20250101", "20250105");

        m2.issueBook(b1, "20250102", "20250106"); // Already issued
        m2.issueBook(b3, "20250102", "20250106");

        m1.returnBook("B001", "20250107"); // 2 days late, fine applies

        System.out.println("\n--- Member Info ---");
        m1.displayMemberInfo();
        m2.displayMemberInfo();

        System.out.println("\n--- Library Stats ---");
        System.out.println("Total Books: " + Book.totalBooks);
        System.out.println("Total Members: " + Member.totalMembers);
        System.out.println("Library Name: " + Book.libraryName);
    }
}
