import java.util.*;

class Room {
    private String roomNumber;
    private String roomType;
    private double pricePerNight;
    private boolean isAvailable;
    private int maxOccupancy;

    public Room(String roomNumber, String roomType, double pricePerNight, int maxOccupancy) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.pricePerNight = pricePerNight;
        this.maxOccupancy = maxOccupancy;
        this.isAvailable = true;
    }

    public String getRoomNumber() { return roomNumber; }
    public String getRoomType() { return roomType; }
    public double getPricePerNight() { return pricePerNight; }
    public boolean isAvailable() { return isAvailable; }
    public int getMaxOccupancy() { return maxOccupancy; }

    public void setAvailable(boolean available) {
        this.isAvailable = available;
    }

    public void displayRoomInfo() {
        System.out.println(roomNumber + " | " + roomType + " | " + pricePerNight +
                " per night | Available: " + isAvailable + " | Max: " + maxOccupancy);
    }
}

class Guest {
    private String guestId;
    private String guestName;
    private String phoneNumber;
    private String email;
    private List<String> bookingHistory;

    public Guest(String guestId, String guestName, String phoneNumber, String email) {
        this.guestId = guestId;
        this.guestName = guestName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.bookingHistory = new ArrayList<>();
    }

    public String getGuestId() { return guestId; }
    public String getGuestName() { return guestName; }

    public void addBooking(String bookingId) {
        bookingHistory.add(bookingId);
    }

    public void displayGuestInfo() {
        System.out.println("Guest: " + guestId + " | " + guestName + " | Phone: " + phoneNumber + " | Email: " + email);
        System.out.println("Bookings: " + bookingHistory);
    }
}

class Booking {
    private String bookingId;
    private Guest guest;
    private Room room;
    private String checkInDate;
    private String checkOutDate;
    private double totalAmount;

    private static int totalBookings = 0;
    private static double hotelRevenue = 0;
    private static String hotelName = "Grand Palace Hotel";

    public Booking(String bookingId, Guest guest, Room room, String checkInDate, String checkOutDate, int nights) {
        this.bookingId = bookingId;
        this.guest = guest;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.totalAmount = nights * room.getPricePerNight();

        totalBookings++;
        hotelRevenue += this.totalAmount;
        guest.addBooking(bookingId);
        room.setAvailable(false);
    }

    public static double getTotalRevenue() { return hotelRevenue; }
    public static int getTotalBookings() { return totalBookings; }
    public static String getHotelName() { return hotelName; }

    public double calculateBill() { return totalAmount; }

    public void cancelReservation() {
        room.setAvailable(true);
        hotelRevenue -= totalAmount;
        System.out.println("Booking " + bookingId + " cancelled. Refund issued: " + totalAmount);
    }

    public void displayBookingInfo() {
        System.out.println("Booking: " + bookingId + " | Guest: " + guest.getGuestName() +
                " | Room: " + room.getRoomNumber() + " (" + room.getRoomType() + ")" +
                " | Stay: " + checkInDate + " to " + checkOutDate + " | Amount: " + totalAmount);
    }
}

public class HotelReservationSystem {
    public static void main(String[] args) {
        Room[] rooms = {
            new Room("101", "Single", 2000, 1),
            new Room("102", "Double", 3500, 2),
            new Room("201", "Suite", 8000, 4),
            new Room("202", "Double", 3500, 2),
            new Room("301", "Suite", 10000, 5)
        };

        Guest g1 = new Guest("G1", "Alice", "9876543210", "alice@mail.com");
        Guest g2 = new Guest("G2", "Bob", "9123456789", "bob@mail.com");

        System.out.println("=== " + Booking.getHotelName() + " ===");
        System.out.println("Available Rooms:");
        for (Room r : rooms) r.displayRoomInfo();

        System.out.println("\n--- Making Reservations ---");
        Booking b1 = new Booking("B1", g1, rooms[0], "2025-09-01", "2025-09-05", 4);
        Booking b2 = new Booking("B2", g2, rooms[2], "2025-09-02", "2025-09-04", 2);

        b1.displayBookingInfo();
        b2.displayBookingInfo();

        System.out.println("\n--- Guest Info ---");
        g1.displayGuestInfo();
        g2.displayGuestInfo();

        System.out.println("\n--- Hotel Stats ---");
        System.out.println("Total Bookings: " + Booking.getTotalBookings());
        System.out.println("Total Revenue: " + Booking.getTotalRevenue());

        System.out.println("\n--- Cancelling a Reservation ---");
        b2.cancelReservation();

        System.out.println("Updated Revenue: " + Booking.getTotalRevenue());

        System.out.println("\nFinal Room Availability:");
        for (Room r : rooms) r.displayRoomInfo();
    }
}
