package org.example.employee_management_system;

public class ContractEmployee extends Employee {
    private double hourlyRate;
    private double maxHours;

    public ContractEmployee(String name, double hourlyRate, double maxHours) {
        super(name, "Contract", 0);
        this.hourlyRate = hourlyRate;
        this.maxHours = maxHours;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public double getMaxHours() {
        return maxHours;
    }

    public void setMaxHours(double maxHours) {
        this.maxHours = maxHours;
    }

    @Override
    public double calculateSalary() {
        double salary = hourlyRate * maxHours;
        setCalculatedSalary(salary);
        return salary;
    }
}
