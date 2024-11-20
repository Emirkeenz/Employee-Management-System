package org.example.employee_management_system;

import java.sql.Date;

public class FullTimeEmployee extends Employee {
    private double monthlySalary;

    public FullTimeEmployee(String name, String position, String type, Date hireDate, double monthlySalary) {
        super(name, position, type, hireDate);
        this.monthlySalary = monthlySalary;
        calculateSalary();
    }

    public double getMonthlySalary() {
        return monthlySalary;
    }

    public void setMonthlySalary(double monthlySalary) {
        this.monthlySalary = monthlySalary;
        calculateSalary();
    }

    @Override
    public void calculateSalary() {
        setCalculatedSalary(monthlySalary);
    }

    @Override
    public String toString() {
        return super.toString() + ", FullTimeEmployee{" +
                "monthlySalary=" + monthlySalary +
                '}';
    }
}