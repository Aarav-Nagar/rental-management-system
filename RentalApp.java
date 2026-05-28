import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import java.util.ArrayList;
import java.util.List;

public class RentalApp extends Application {
    private List<Rental> rentals = new ArrayList<>();
    private Rental selectedRental;
    
    private ListView<Rental> rentalListView;
    private Label detailsLabel;
    private TextArea infoArea;
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Rental Management System");
        
        BorderPane root = new BorderPane();
        
        // Top: Title
        Label titleLabel = new Label("Rental Management System");
        titleLabel.setStyle("-fx-font-size: 20; -fx-font-weight: bold;");
        VBox topBox = new VBox(titleLabel);
        topBox.setPadding(new Insets(10));
        root.setTop(topBox);
        
        // Center: Two columns - rentals list and details
        HBox centerBox = new HBox(10);
        centerBox.setPadding(new Insets(10));
        
        // Left: List of rentals
        VBox leftBox = createRentalListSection();
        
        // Right: Details and operations
        VBox rightBox = createDetailsSection();
        
        centerBox.getChildren().addAll(leftBox, rightBox);
        HBox.setHgrow(leftBox, Priority.ALWAYS);
        HBox.setHgrow(rightBox, Priority.ALWAYS);
        root.setCenter(centerBox);
        
        // Bottom: Add new rental section
        VBox bottomBox = createAddRentalSection();
        root.setBottom(bottomBox);
        
        Scene scene = new Scene(root, 1000, 800);
        primaryStage.setScene(scene);
        primaryStage.show();
        
        // Add sample rentals
        addSampleRentals();
    }
    
    private VBox createRentalListSection() {
        VBox box = new VBox(10);
        box.setStyle("-fx-border-color: #cccccc; -fx-border-radius: 5;");
        box.setPadding(new Insets(10));
        
        Label title = new Label("Available & Rented Items");
        title.setStyle("-fx-font-size: 14; -fx-font-weight: bold;");
        
        rentalListView = new ListView<>();
        rentalListView.setPrefHeight(400);
        rentalListView.setOnMouseClicked(e -> selectRentalAction());
        
        box.getChildren().addAll(title, rentalListView);
        return box;
    }
    
    private VBox createDetailsSection() {
        VBox box = new VBox(10);
        box.setStyle("-fx-border-color: #cccccc; -fx-border-radius: 5;");
        box.setPadding(new Insets(10));
        
        Label title = new Label("Details & Operations");
        title.setStyle("-fx-font-size: 14; -fx-font-weight: bold;");
        
        detailsLabel = new Label("Select a rental to see details");
        detailsLabel.setStyle("-fx-font-size: 12; -fx-font-weight: bold;");
        
        infoArea = new TextArea();
        infoArea.setPrefHeight(250);
        infoArea.setWrapText(true);
        infoArea.setEditable(false);
        
        // Operation buttons
        VBox operationBox = createOperationsBox();
        
        box.getChildren().addAll(title, detailsLabel, infoArea, operationBox);
        VBox.setVgrow(infoArea, Priority.ALWAYS);
        return box;
    }
    
    private VBox createOperationsBox() {
        VBox box = new VBox(8);
        box.setStyle("-fx-border-color: #e0e0e0; -fx-border-radius: 3; -fx-padding: 10;");
        
        Label opTitle = new Label("Book / Return");
        opTitle.setStyle("-fx-font-size: 12; -fx-font-weight: bold;");
        
        HBox bookBox = new HBox(10);
        bookBox.getChildren().add(new Label("Renter Name:"));
        TextField renterField = new TextField();
        renterField.setPrefWidth(150);
        bookBox.getChildren().add(renterField);
        
        HBox daysBox = new HBox(10);
        daysBox.getChildren().add(new Label("Days:"));
        TextField daysField = new TextField();
        daysField.setPrefWidth(80);
        daysField.setText("1");
        daysBox.getChildren().add(daysField);
        
        HBox btnBox = new HBox(10);
        Button bookBtn = new Button("Book");
        bookBtn.setPrefWidth(80);
        bookBtn.setOnAction(e -> {
            try {
                if (selectedRental == null) {
                    showAlert("Select a rental first");
                    return;
                }
                String renter = renterField.getText();
                int days = Integer.parseInt(daysField.getText());
                
                if (renter.isEmpty() || days <= 0) {
                    showAlert("Invalid input");
                    return;
                }
                
                if (selectedRental.book(renter, days)) {
                    double cost = selectedRental.calculateCost(days);
                    updateDisplay();
                    rentalListView.refresh();
                    showAlert(String.format("Booked for %d days. Total cost: $%.2f", days, cost));
                    renterField.clear();
                } else {
                    showAlert("This rental is not available");
                }
            } catch (NumberFormatException ex) {
                showAlert("Invalid days");
            }
        });
        
        Button returnBtn = new Button("Return");
        returnBtn.setPrefWidth(80);
        returnBtn.setOnAction(e -> {
            if (selectedRental == null) {
                showAlert("Select a rental first");
                return;
            }
            if (selectedRental.getIsAvailable()) {
                showAlert("This rental is already available");
                return;
            }
            selectedRental.returnRental();
            updateDisplay();
            rentalListView.refresh();
            showAlert("Rental returned successfully");
            renterField.clear();
        });
        
        btnBox.getChildren().addAll(bookBtn, returnBtn);
        
        box.getChildren().addAll(opTitle, bookBox, daysBox, btnBox);
        return box;
    }
    
    private VBox createAddRentalSection() {
        VBox box = new VBox(10);
        box.setStyle("-fx-border-color: #cccccc; -fx-border-radius: 5; -fx-padding: 10;");
        
        Label title = new Label("Add New Rental");
        title.setStyle("-fx-font-size: 14; -fx-font-weight: bold;");
        
        ComboBox<String> typeCombo = new ComboBox<>();
        typeCombo.getItems().addAll("Car", "Apartment");
        typeCombo.setValue("Car");
        
        HBox addBox = new HBox(10);
        addBox.getChildren().add(new Label("Type:"));
        addBox.getChildren().add(typeCombo);
        
        Button addBtn = new Button("Add Item");
        addBtn.setPrefWidth(100);
        addBtn.setOnAction(e -> {
            if (typeCombo.getValue().equals("Car")) {
                showAddCarDialog();
            } else {
                showAddApartmentDialog();
            }
        });
        
        addBox.getChildren().add(addBtn);
        box.getChildren().addAll(title, addBox);
        return box;
    }
    
    private void selectRentalAction() {
        selectedRental = rentalListView.getSelectionModel().getSelectedItem();
        if (selectedRental != null) {
            updateDisplay();
        }
    }
    
    private void updateDisplay() {
        if (selectedRental == null) return;
        
        detailsLabel.setText(selectedRental.toString());
        
        StringBuilder info = new StringBuilder();
        info.append("Type: ").append(selectedRental.getRentalType()).append("\n\n");
        info.append("Description: ").append(selectedRental.getDescription()).append("\n");
        info.append("Daily Price: $").append(String.format("%.2f", selectedRental.getDailyPrice())).append("\n");
        info.append("Status: ").append(selectedRental.getIsAvailable() ? "Available" : "Rented").append("\n");
        
        if (!selectedRental.getIsAvailable()) {
            info.append("Current Renter: ").append(selectedRental.getCurrentRenter()).append("\n");
            if (selectedRental.getRentalStartDate() != null) {
                info.append("Rental Period: ").append(selectedRental.getRentalStartDate())
                    .append(" to ").append(selectedRental.getRentalEndDate()).append("\n");
            }
        }
        
        if (selectedRental instanceof Car) {
            Car car = (Car) selectedRental;
            info.append("\n--- Car Details ---\n");
            info.append("Make: ").append(car.getMake()).append("\n");
            info.append("Model: ").append(car.getModel()).append("\n");
            info.append("Year: ").append(car.getYear()).append("\n");
            info.append("License Plate: ").append(car.getLicensePlate()).append("\n");
        } else if (selectedRental instanceof Apartment) {
            Apartment apt = (Apartment) selectedRental;
            info.append("\n--- Apartment Details ---\n");
            info.append("Address: ").append(apt.getAddress()).append("\n");
            info.append("Bedrooms: ").append(apt.getBedrooms()).append("\n");
            info.append("Bathrooms: ").append(apt.getBathrooms()).append("\n");
            info.append("Square Feet: ").append(String.format("%.0f", apt.getSquareFeet())).append("\n");
        }
        
        infoArea.setText(info.toString());
    }
    
    private void showAddCarDialog() {
        Dialog<Car> dialog = new Dialog<>();
        dialog.setTitle("Add New Car");
        
        VBox content = new VBox(10);
        content.setPadding(new Insets(10));
        
        TextField idField = new TextField();
        idField.setPromptText("Rental ID");
        
        TextField makeField = new TextField();
        makeField.setPromptText("Make (e.g., Toyota)");
        
        TextField modelField = new TextField();
        modelField.setPromptText("Model (e.g., Camry)");
        
        TextField yearField = new TextField();
        yearField.setPromptText("Year");
        
        TextField plateField = new TextField();
        plateField.setPromptText("License Plate");
        
        TextField priceField = new TextField();
        priceField.setPromptText("Daily Price");
        
        content.getChildren().addAll(
            new Label("Rental ID:"), idField,
            new Label("Make:"), makeField,
            new Label("Model:"), modelField,
            new Label("Year:"), yearField,
            new Label("License Plate:"), plateField,
            new Label("Daily Price:"), priceField
        );
        
        dialog.getDialogPane().setContent(content);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        
        if (dialog.showAndWait().isPresent()) {
            try {
                Car car = new Car(
                    idField.getText(),
                    makeField.getText(),
                    modelField.getText(),
                    Integer.parseInt(yearField.getText()),
                    plateField.getText(),
                    Double.parseDouble(priceField.getText())
                );
                rentals.add(car);
                rentalListView.getItems().add(car);
                showAlert("Car added successfully!");
            } catch (Exception ex) {
                showAlert("Invalid input");
            }
        }
    }
    
    private void showAddApartmentDialog() {
        Dialog<Apartment> dialog = new Dialog<>();
        dialog.setTitle("Add New Apartment");
        
        VBox content = new VBox(10);
        content.setPadding(new Insets(10));
        
        TextField idField = new TextField();
        idField.setPromptText("Rental ID");
        
        TextField addressField = new TextField();
        addressField.setPromptText("Address");
        
        TextField bedroomsField = new TextField();
        bedroomsField.setPromptText("Bedrooms");
        
        TextField bathroomsField = new TextField();
        bathroomsField.setPromptText("Bathrooms");
        
        TextField sqftField = new TextField();
        sqftField.setPromptText("Square Feet");
        
        TextField priceField = new TextField();
        priceField.setPromptText("Daily Price");
        
        content.getChildren().addAll(
            new Label("Rental ID:"), idField,
            new Label("Address:"), addressField,
            new Label("Bedrooms:"), bedroomsField,
            new Label("Bathrooms:"), bathroomsField,
            new Label("Square Feet:"), sqftField,
            new Label("Daily Price:"), priceField
        );
        
        dialog.getDialogPane().setContent(content);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        
        if (dialog.showAndWait().isPresent()) {
            try {
                Apartment apt = new Apartment(
                    idField.getText(),
                    addressField.getText(),
                    Integer.parseInt(bedroomsField.getText()),
                    Integer.parseInt(bathroomsField.getText()),
                    Double.parseDouble(sqftField.getText()),
                    Double.parseDouble(priceField.getText())
                );
                rentals.add(apt);
                rentalListView.getItems().add(apt);
                showAlert("Apartment added successfully!");
            } catch (Exception ex) {
                showAlert("Invalid input");
            }
        }
    }
    
    private void addSampleRentals() {
        Car car1 = new Car("CAR001", "Toyota", "Camry", 2023, "ABC123", 50.0);
        Car car2 = new Car("CAR002", "Honda", "Civic", 2022, "XYZ789", 40.0);
        
        Apartment apt1 = new Apartment("APT001", "123 Main St", 2, 1, 1000, 150.0);
        Apartment apt2 = new Apartment("APT002", "456 Oak Ave", 3, 2, 1500, 200.0);
        
        rentals.addAll(java.util.Arrays.asList(car1, car2, apt1, apt2));
        rentalListView.getItems().addAll(rentals);
    }
    
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Rental System");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
