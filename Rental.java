import java.util.Date;

/**
 * Abstract base class representing a rental item (car or apartment).
 * This class demonstrates inheritance and polymorphism principles in Java.
 * 
 * Core functionality includes:
 * - Booking rentals for specified duration
 * - Returning rentals to make them available again
 * - Calculating rental costs based on daily price
 * - Tracking rental status and current renter information
 * 
 * Subclasses must implement:
 * - getRentalType(): Returns the type of rental (Car, Apartment, etc.)
 * - displayDetails(): Displays detailed information about the rental
 */
public abstract class Rental {
    protected String rentalId;
    protected String description;
    protected double dailyPrice;
    protected boolean isAvailable;
    protected String currentRenter;
    protected Date rentalStartDate;
    protected Date rentalEndDate;
    
    public Rental(String rentalId, String description, double dailyPrice) {
        this.rentalId = rentalId;
        this.description = description;
        this.dailyPrice = dailyPrice;
        this.isAvailable = true;
        this.currentRenter = null;
    }
    
    /**
     * Books a rental for the specified number of days.
     * Only available rentals can be booked. Once booked, rental becomes unavailable.
     * Rental period is calculated from current date/time.
     * 
     * @param renterName Name of the person renting the item
     * @param days Number of days for the rental
     * @return true if booking was successful, false if item is already rented
     */
    public boolean book(String renterName, int days) {
        // Check if rental is available before booking
        if (!isAvailable) {
            return false;
        }
        // Record renter information and calculate rental period
        this.currentRenter = renterName;
        this.isAvailable = false;
        this.rentalStartDate = new Date();
        // Calculate end date by adding days worth of milliseconds (days * 24 * 60 * 60 * 1000)
        this.rentalEndDate = new Date(rentalStartDate.getTime() + (days * 24 * 60 * 60 * 1000));
        return true;
    }
    
    /**
     * Returns a rented item back, making it available for new bookings.
     * Clears all rental information including renter name and dates.
     */
    public void returnRental() {
        // Only return if rental is currently booked (not available)
        if (!isAvailable && currentRenter != null) {
            isAvailable = true;
            currentRenter = null;
            rentalStartDate = null;
            rentalEndDate = null;
        }
    }
    
    /**
     * Calculates the total rental cost based on daily price and rental duration.
     * 
     * @param days Number of days for rental
     * @return Total cost = Daily Price × Number of Days
     */
    public double calculateCost(int days) {
        // Simple cost calculation: daily rate multiplied by number of days
        return dailyPrice * days;
    }
    
    public abstract String getRentalType();
    public abstract void displayDetails();
    
    public String getRentalId() {
        return rentalId;
    }
    
    public String getDescription() {
        return description;
    }
    
    public double getDailyPrice() {
        return dailyPrice;
    }
    
    public boolean getIsAvailable() {
        return isAvailable;
    }
    
    public String getCurrentRenter() {
        return currentRenter;
    }
    
    public Date getRentalStartDate() {
        return rentalStartDate;
    }
    
    public Date getRentalEndDate() {
        return rentalEndDate;
    }
    
    @Override
    public String toString() {
        String status = isAvailable ? "Available" : "Rented by " + currentRenter;
        return String.format("[%s] %s (%s) - $%.2f/day - %s", 
            rentalId, description, getRentalType(), dailyPrice, status);
    }
}
