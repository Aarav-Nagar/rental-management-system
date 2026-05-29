/**
 * Car rental implementation extending the Rental base class.
 * 
 * Represents a vehicle available for rental with detailed specifications.
 * Tracks vehicle identification information and rental pricing.
 * 
 * Properties tracked:
 * - Make (manufacturer)
 * - Model (vehicle type)
 * - Year (model year)
 * - License Plate (vehicle registration)
 */
public class Car extends Rental {
    private String make;
    private String model;
    private int year;
    private String licensePlate;
    
    public Car(String rentalId, String make, String model, int year, 
               String licensePlate, double dailyPrice) {
        super(rentalId, make + " " + model + " (" + year + ")", dailyPrice);
        this.make = make;
        this.model = model;
        this.year = year;
        this.licensePlate = licensePlate;
    }
    
    @Override
    public String getRentalType() {
        return "Car";
    }
    
    /**
     * Displays detailed information about the car rental.
     * Includes make, model, year, license plate, pricing, and availability status.
     * Used for console output and detailed views.
     */
    @Override
    public void displayDetails() {
        System.out.println("=== Car Details ===");
        System.out.println("Make: " + make);
        System.out.println("Model: " + model);
        System.out.println("Year: " + year);
        System.out.println("License Plate: " + licensePlate);
        System.out.println("Daily Price: $" + String.format("%.2f", dailyPrice));
        System.out.println("Status: " + (isAvailable ? "Available" : "Rented"));
    }
    
    public String getMake() {
        return make;
    }
    
    public String getModel() {
        return model;
    }
    
    public int getYear() {
        return year;
    }
    
    public String getLicensePlate() {
        return licensePlate;
    }
}
