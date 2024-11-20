package org.example.employee_management_system;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.sql.Date;
import java.sql.*;
import java.time.format.DateTimeParseException;

public class EmployeeController {
    @FXML
    private TextField nameField;
    @FXML
    private TextField positionField;
    @FXML
    private DatePicker hireDatePicker;
    @FXML
    private TextField hourlyRateField;
    @FXML
    private TextField hoursWorkedField;
    @FXML
    private TextField maxHoursField;
    @FXML
    private TextField annualSalaryField;
    @FXML
    private ChoiceBox<String> employeeTypeChoiceBox;

    @FXML
    private TableView<Employee> employeeTable;
    @FXML
    private TableColumn<Employee, Long> idColumn;
    @FXML
    private TableColumn<Employee, String> nameColumn;
    @FXML
    private TableColumn<Employee, String> positionColumn;
    @FXML
    private TableColumn<Employee, LocalDate> hireDateColumn;
    @FXML
    private TableColumn<Employee, String> typeColumn;
    @FXML
    private TableColumn<Employee, Double> salaryColumn;

    private final ObservableList<Employee> employees = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        employeeTypeChoiceBox.setItems(FXCollections.observableArrayList("Full-Time", "Part-Time", "Contract"));

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        positionColumn.setCellValueFactory(new PropertyValueFactory<>("position"));
        hireDateColumn.setCellValueFactory(new PropertyValueFactory<>("hireDate"));
        typeColumn.setCellValueFactory(data -> {
            if (data.getValue() instanceof FullTimeEmployee) {
                return new SimpleStringProperty("Full-Time");
            } else if (data.getValue() instanceof PartTimeEmployee) {
                return new SimpleStringProperty("Part-Time");
            } else if (data.getValue() instanceof ContractEmployee) {
                return new SimpleStringProperty("Contract");
            }
            return null;
        });
        salaryColumn.setCellValueFactory(new PropertyValueFactory<>("calculatedSalary"));

        employeeTable.setItems(employees);
    }

    @FXML
    private void addEmployee() {
        try {
            String name = nameField.getText().trim();
            String position = positionField.getText().trim();
            Date hireDate = Date.valueOf(hireDatePicker.getValue());
            String type = employeeTypeChoiceBox.getValue();

            if (name.isEmpty() || position.isEmpty() || hireDate == null || type == null) {
                showAlert(Alert.AlertType.WARNING, "Ошибка ввода", "Заполните все поля.");
                return;
            }

            Employee employee = null;
            switch (type) {
                case "Full-Time" -> {
                    // Инициализация для Full-Time сотрудника
                    double monthlySalary = Double.parseDouble(annualSalaryField.getText().trim()); // Вероятно, здесь предполагается годовая зарплата
                    employee = new FullTimeEmployee(name, position, "Full-Time", hireDate, monthlySalary);
                }
                case "Part-Time" -> {
                    // Инициализация для Part-Time сотрудника
                    double hourlyRate = Double.parseDouble(hourlyRateField.getText().trim());
                    double hoursWorked = Double.parseDouble(hoursWorkedField.getText().trim());
                    employee = new PartTimeEmployee(name, position, "Part-Time", hireDate, hourlyRate, hoursWorked);
                }
                case "Contract" -> {
                    // Инициализация для Contract сотрудника
                    double hourlyRate = Double.parseDouble(hourlyRateField.getText().trim());
                    double maxHours = Double.parseDouble(maxHoursField.getText().trim());
                    employee = new ContractEmployee(name, position, "Contract", hireDate, hourlyRate, maxHours);
                }
            }

            employees.add(employee);
            clearFields();
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Ошибка ввода", "Введите числовые значения для зарплаты и часов.");
        }
    }


    private void clearFields() {
        nameField.clear();
        positionField.clear();
        hireDatePicker.setValue(null);
        hourlyRateField.clear();
        hoursWorkedField.clear();
        maxHoursField.clear();
        annualSalaryField.clear();
        employeeTypeChoiceBox.getSelectionModel().clearSelection();
    }

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
