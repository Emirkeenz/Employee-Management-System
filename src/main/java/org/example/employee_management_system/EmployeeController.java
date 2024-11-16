package org.example.employee_management_system;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class EmployeeController {

    @FXML
    private TextField nameField;
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
    private TableColumn<Employee, String> nameColumn;
    @FXML
    private TableColumn<Employee, String> typeColumn;
    @FXML
    private TableColumn<Employee, Double> salaryColumn;

    private final ObservableList<Employee> employees = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        employeeTypeChoiceBox.setItems(FXCollections.observableArrayList("Full-Time", "Part-Time", "Contract"));

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        salaryColumn.setCellValueFactory(new PropertyValueFactory<>("calculatedSalary"));

        employeeTable.setItems(employees);
    }

    @FXML
    private void addEmployee() {
        try {
            String name = nameField.getText().trim();
            String type = employeeTypeChoiceBox.getValue();

            if (name.isEmpty() || type == null) {
                showAlert(Alert.AlertType.WARNING, "Ошибка ввода", "Заполните имя и выберите тип сотрудника.");
                return;
            }

            Employee employee = null;

            switch (type) {
                case "Full-Time" -> {
                    double annualSalary = Double.parseDouble(annualSalaryField.getText().trim());
                    if (annualSalary < 0) {
                        showAlert(Alert.AlertType.ERROR, "Ошибка ввода", "Годовая зарплата не может быть отрицательной.");
                        return;
                    }
                    employee = new FullTimeEmployee(name, annualSalary);
                }
                case "Part-Time" -> {
                    double hourlyRate = Double.parseDouble(hourlyRateField.getText().trim());
                    double hoursWorked = Double.parseDouble(hoursWorkedField.getText().trim());
                    if (hourlyRate < 0 || hoursWorked < 0) {
                        showAlert(Alert.AlertType.ERROR, "Ошибка ввода", "Почасовая ставка и отработанные часы не могут быть отрицательными.");
                        return;
                    }
                    employee = new PartTimeEmployee(name, hourlyRate, hoursWorked);
                }
                case "Contract" -> {
                    double hourlyRate = Double.parseDouble(hourlyRateField.getText().trim());
                    double maxHours = Double.parseDouble(maxHoursField.getText().trim());
                    if (hourlyRate < 0 || maxHours < 0) {
                        showAlert(Alert.AlertType.ERROR, "Ошибка ввода", "Почасовая ставка и максимальные часы не могут быть отрицательными.");
                        return;
                    }
                    employee = new ContractEmployee(name, hourlyRate, maxHours);
                }
            }

            if (employee != null) {
                employee.calculateSalary();
                employees.add(employee);
                clearFields();
            }
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Ошибка ввода", "Убедитесь, что числовые значения введены корректно.");
        }
    }

    @FXML
    private void calculateSalaries() {
        if (employees.isEmpty()) {
            showAlert(Alert.AlertType.INFORMATION, "Нет сотрудников", "Добавьте сотрудников перед расчетом зарплат.");
            return;
        }

        for (Employee employee : employees) {
            employee.calculateSalary();
        }

        employeeTable.refresh();
    }

    @FXML
    private void removeSelectedEmployee() {
        Employee selectedEmployee = employeeTable.getSelectionModel().getSelectedItem();
        if (selectedEmployee != null) {
            employees.remove(selectedEmployee);
        } else {
            showAlert(Alert.AlertType.WARNING, "Удаление сотрудника", "Выберите сотрудника для удаления.");
        }
    }

    private void clearFields() {
        nameField.clear();
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
