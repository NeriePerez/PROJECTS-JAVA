package com.mycompany.employeeps;

class PartTimeEmployee extends Employee{
    private double hourlyRate;
    private int hoursWorked;
    
    PartTimeEmployee(String name, String employeeID, double hourlyRate, int hoursWorked) {
        super(name, employeeID);
        this.hourlyRate = hourlyRate;
        this.hoursWorked = hoursWorked;
    }
    
    
    public void setHourlyRate() {
        this.hourlyRate = hourlyRate;
    }
    
    public int getHoursWorked() {
        return hoursWorked;
    }
    public void setHoursWorked() {
        this.hoursWorked = hoursWorked;
    }
    
    
    public double calculateSalary() {
        return (hourlyRate * hoursWorked);
    }
    
    @Override
    public void displayInfo() {
        System.out.println("---Part Time Employee---");
        super.displayInfo();
        System.out.println("Hours Worked: " + getHoursWorked());
        System.out.println("Monthly Salary: P" + calculateSalary());
    }
}