import java.util.Date;

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
    
    public boolean book(String renterName, int days) {
        if (!isAvailable) {
            return false;
        }
        this.currentRenter = renterName;
        this.isAvailable = false;
        this.rentalStartDate = new Date();
        this.rentalEndDate = new Date(rentalStartDate.getTime() + (days * 24 * 60 * 60 * 1000));
        return true;
    }
    
    public void returnRental() {
        if (!isAvailable && currentRenter != null) {
            isAvailable = true;
            currentRenter = null;
            rentalStartDate = null;
            rentalEndDate = null;
        }
    }
    
    public double calculateCost(int days) {
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
