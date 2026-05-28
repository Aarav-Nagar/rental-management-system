# Rental Management System

A JavaFX-based rental management system for cars and apartments, demonstrating inheritance, polymorphism, and abstract classes in Java.

## Features

- Manage car and apartment rentals
- Book rentals with customer name and duration
- Return rentals to make them available again
- Calculate rental costs based on daily price
- View detailed information for each rental
- Track current renter and rental dates
- Add new rental items dynamically
- Real-time inventory updates

## Rental Types

- **Cars**: Track make, model, year, license plate
- **Apartments**: Track address, bedrooms, bathrooms, square footage

## Requirements

- Java 11 or higher
- JavaFX SDK 11+

## Compile and Run

```
javac *.java
java RentalApp
```

## How to Use

1. The application starts with sample rentals (2 cars and 2 apartments)
2. Click on any rental in the list to view details
3. Enter renter name and number of days
4. Click "Book" to rent the item
5. Click "Return" when the rental period ends
6. Use the "Add New Rental" section to add cars or apartments
7. View real-time cost calculations

## Rental Operations

- **Book**: Reserve a rental for specified days
- **Return**: Mark rental as available again
- **Calculate Cost**: Automatic calculation of total rental cost

## Java Concepts Demonstrated

- Abstract classes and methods
- Inheritance (Car and Apartment extend Rental)
- Polymorphism
- Collections (ArrayList, ListView)
- Encapsulation
- JavaFX GUI with multiple panes
- Dialog windows
- Date/Time handling

## License

MIT
