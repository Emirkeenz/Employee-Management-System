package org.example.employee_management_system;

import java.sql.Date;

public class PartTimeEmployee extends Employee {
    private double hoursWorked;
    private double hourlyRate;

    public PartTimeEmployee(String name, String position, String type, Date hireDate, double hourlyRate, double hoursWorked) {
        super(name, position, "PartTime", hireDate);
        this.hourlyRate = hourlyRate;
        this.hoursWorked = hoursWorked;
        calculateSalary();
    }
    public double getHoursWorked() {
        return hoursWorked;
    }

    public void setHoursWorked(double hoursWorked) {
        this.hoursWorked = hoursWorked;
        calculateSalary();
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
        calculateSalary();
    }

    @Override
    public void calculateSalary() {
        setCalculatedSalary(hourlyRate * hoursWorked);
    }
}