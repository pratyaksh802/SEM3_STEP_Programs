class MovieTicket {
    String movieName;
    String theatreName;
    int seatNumber;
    double price;

    // Default constructor
    public MovieTicket() {
        this("Unknown", "Not Assigned", 0, 0.0);
    }

    // Constructor with movie name
    public MovieTicket(String movieName) {
        this(movieName, "Generic Theatre", 1, 200.0);
    }

    // Constructor with movie name and seat number
    public MovieTicket(String movieName, int seatNumber) {
        this(movieName, "PVR", seatNumber, 250.0);
    }

    // Full constructor
    public MovieTicket(String movieName, String theatreName, int seatNumber, double price) {
        this.movieName = movieName;
        this.theatreName = theatreName;
        this.seatNumber = seatNumber;
        this.price = price;
    }

    public void printTicket() {
        System.out.println("Movie: " + movieName + 
                         " | Theatre: " + theatreName + 
                         " | Seat: " + seatNumber + 
                         " | Price: Rs." + price);
    }

    public static void main(String[] args) {
        // Create tickets using different constructors
        MovieTicket t1 = new MovieTicket();
        MovieTicket t2 = new MovieTicket("Inception");
        MovieTicket t3 = new MovieTicket("Avatar", 12);
        MovieTicket t4 = new MovieTicket("Interstellar", "IMAX", 25, 500.0);
        
        // Print all tickets
        t1.printTicket();
        t2.printTicket();
        t3.printTicket();
        t4.printTicket();
    }
}
