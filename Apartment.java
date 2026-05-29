/**
 * Apartment rental implementation extending the Rental base class.
 * 
 * Represents a residential property available for rental.
 * Tracks detailed property information and rental pricing.
 * 
 * Properties tracked:
 * - Address (location)
 * - Bedrooms (number of bedrooms)
 * - Bathrooms (number of bathrooms)
 * - Square Feet (total living area)
 */
public class Apartment extends Rental {
    private int bedrooms;
    private int bathrooms;
    private String address;
    private double squareFeet;
    
    public Apartment(String rentalId, String address, int bedrooms, 
                     int bathrooms, double squareFeet, double dailyPrice) {
        super(rentalId, bedrooms + "BR " + bathrooms + "BA at " + address, dailyPrice);
        this.address = address;
        this.bedrooms = bedrooms;
        this.bathrooms = bathrooms;
        this.squareFeet = squareFeet;
    }
    
    @Override
    public String getRentalType() {
        return "Apartment";
    }
    
    /**
     * Displays detailed information about the apartment rental.
     * Includes address, bedrooms, bathrooms, square footage, pricing, and availability status.
     * Used for console output and detailed views.
     */
    @Override
    public void displayDetails() {
        System.out.println("=== Apartment Details ===");
        System.out.println("Address: " + address);
        System.out.println("Bedrooms: " + bedrooms);
        System.out.println("Bathrooms: " + bathrooms);
        System.out.println("Square Feet: " + String.format("%.0f", squareFeet));
        System.out.println("Daily Price: $" + String.format("%.2f", dailyPrice));
        System.out.println("Status: " + (isAvailable ? "Available" : "Rented"));
    }
    
    public int getBedrooms() {
        return bedrooms;
    }
    
    public int getBathrooms() {
        return bathrooms;
    }
    
    public String getAddress() {
        return address;
    }
    
    public double getSquareFeet() {
        return squareFeet;
    }
}
