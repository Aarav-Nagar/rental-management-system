# Rental Management System

A comprehensive JavaFX-based rental management system for cars and apartments, demonstrating core Object-Oriented Programming principles including inheritance, polymorphism, and abstract classes.

## Project Overview

This application provides a complete rental management solution supporting multiple rental types (cars and apartments). Features a clean JavaFX graphical interface for browsing available rentals, booking items, and managing the rental lifecycle.

## Features

- **Multi-Rental Type Support**: Manage cars and apartments in a unified system
- **Real-Time Inventory**: View available and rented items at a glance
- **Booking System**: Reserve rentals for specified duration with renter information
- **Return Processing**: Mark rentals as available when returned
- **Cost Calculation**: Automatic total cost calculation based on duration
- **Detailed Information**: Comprehensive details for each rental item
- **Dynamic Management**: Add new car or apartment rentals on-the-fly
- **Status Tracking**: Real-time status updates showing renter and availability

## Rental Types

### Cars
**Tracks comprehensive vehicle information:**
- **Make**: Manufacturer (e.g., Toyota, Honda, BMW)
- **Model**: Vehicle type/name
- **Year**: Model year for age reference
- **License Plate**: Vehicle registration identifier
- **Daily Rate**: Cost per day of rental
- **Status**: Available or currently rented

**Example**: 2023 Tesla Model 3 (ABC1234) - $75/day

### Apartments
**Tracks detailed property information:**
- **Address**: Full residential address
- **Bedrooms**: Number of sleeping areas
- **Bathrooms**: Number of bathrooms
- **Square Footage**: Total living area in square feet
- **Daily Rate**: Nightly rental cost
- **Status**: Available or currently booked

**Example**: 2BR/2BA at 123 Main St - $150/night

## Requirements

- Java 11 or higher
- JavaFX SDK 11+ (runtime library)
- Modern operating system (Windows, macOS, Linux)

## Compile and Run

```bash
# Compile all Java files
javac *.java

# Run the application
java RentalApp
```

## How to Use

### Viewing Available Rentals
1. Application launches with sample rentals (2 cars + 2 apartments)
2. Browse the rental list on the left side
3. Click on any rental to view detailed information

### Booking a Rental
1. Select a rental from the list (must be "Available")
2. Enter the renter's name in the input field
3. Enter the number of rental days
4. Click "Book" to reserve the rental
5. Rental becomes unavailable to other renters
6. Total cost is automatically calculated

### Returning a Rental
1. Select the currently rented item from the list
2. Click "Return" to mark it as available again
3. Renter information is cleared
4. Item becomes available for new bookings

### Adding New Rentals

**Add a Car:**
1. Fill in car details: Make, Model, Year, License Plate, Daily Price
2. Click "Add Car"
3. New car appears in the rental list

**Add an Apartment:**
1. Fill in property details: Address, Bedrooms, Bathrooms, Square Feet, Daily Rate
2. Click "Add Apartment"
3. New apartment appears in the rental list

### Viewing Details
- Click a rental to view full information
- Details section shows all relevant specifications
- Current renter and rental dates displayed when booked
- Cost calculation shown for booked items

## Rental Operations

### Book
- Reserve a rental for specified number of days
- Records renter name and rental period
- Automatically calculates rental end date
- Updates item status to unavailable

### Return
- Mark a booked rental as available again
- Clears all renter information
- Resets rental dates
- Makes item available for new bookings

### Calculate Cost
- **Formula**: Daily Rate × Number of Days
- **Example**: $75/day car rented for 3 days = $225
- Automatically displayed in the UI during booking

## Java Concepts Demonstrated

- **Abstract Classes**: Rental base class defines contract for subclasses
- **Inheritance**: Car and Apartment extend Rental
- **Polymorphism**: Each rental type implements getRentalType() and displayDetails() uniquely
- **Encapsulation**: Private fields with public getters for data protection
- **Collections**: ArrayList and ListView for managing multiple rentals
- **Date/Time Handling**: Java Date for tracking rental periods
- **JavaFX GUI Components**:
  - BorderPane for main layout
  - ListView for dynamic rental display
  - TextArea for detailed information
  - Button controls for operations
  - Real-time UI updates

## Architecture

### Class Hierarchy
```
Rental (abstract)
├── Car
└── Apartment
```

### Key Methods
- `book(String renterName, int days)`: Reserve a rental
- `returnRental()`: Mark rental as available
- `calculateCost(int days)`: Compute total rental cost
- `getRentalType()`: Return type identifier
- `displayDetails()`: Show detailed information

## Sample Data

The application includes default sample rentals:

**Cars:**
- 2023 Tesla Model 3 (ABC1234) - $75/day
- 2022 Honda CR-V (XYZ9876) - $60/day

**Apartments:**
- 2BR/2BA at Downtown Plaza - $150/night
- 3BR/2BA at Riverside Apartments - $200/night

## Testing the Application

### Test Scenarios
1. **View Rentals**: Browse all available rentals
2. **Book Items**: Book cars and apartments for various durations
3. **Cost Calculation**: Verify correct cost calculations
4. **Return Items**: Return booked rentals and verify availability
5. **Add Rentals**: Dynamically add new items to the system
6. **Edge Cases**: Attempt to book unavailable items

## Future Enhancements

- Database integration for persistent storage
- Reservation history and past bookings
- Customer profiles and preferences
- Advanced search and filtering
- Payment processing integration
- Email confirmation notifications
- Damage reporting system
- Multi-user support with authentication
- Reporting and analytics

## Performance Considerations

- Efficient ArrayList for rental management
- Real-time UI updates without performance degradation
- Scalable to hundreds of rental items
- Quick search and filter operations

## License

MIT License - Free to use, modify, and distribute

## Author

Aarav Nagar

## Version

1.0 - Initial Release (May 2026)
