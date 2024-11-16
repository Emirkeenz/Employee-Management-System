package org.example.employee_management_system;

public abstract class Employee {
    private String name;
    private String type;
    private double calculatedSalary;

    public Employee(String name, String type, double calculatedSalary) {
        this.name = name;
        this.type = type;
        this.calculatedSalary = calculatedSalary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getCalculatedSalary() {
        return calculatedSalary;
    }

    public void setCalculatedSalary(double calculatedSalary) {
        this.calculatedSalary = calculatedSalary;
    }

    public abstract double calculateSalary();
}

