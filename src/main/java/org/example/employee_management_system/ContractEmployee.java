package org.example.employee_management_system;

import java.sql.Date;

public class ContractEmployee extends Employee {
    private double hourlyRate;
    private double maxHours;

    public ContractEmployee(String name, String position, String type, Date hireDate, double hourlyRate, double maxHours) {
        super(name, position, "Contract", hireDate);
        this.hourlyRate = hourlyRate;
        this.maxHours = maxHours;
        calculateSalary();
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
        calculateSalary();
    }

    public double getMaxHours() {
        return maxHours;
    }

    public void setMaxHours(double maxHours) {
        this.maxHours = maxHours;
        calculateSalary();
    }

    @Override
    public void calculateSalary() {
        setCalculatedSalary(hourlyRate * maxHours);
    }
}