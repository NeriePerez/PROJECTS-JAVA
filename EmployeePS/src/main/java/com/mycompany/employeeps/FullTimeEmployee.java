package com.mycompany.employeeps;

class FullTimeEmployee extends Employee{
    private double monthlySalary;
    
    FullTimeEmployee(String name, String employeeID, double monthlySalary) {
        super(name, employeeID);
        this.monthlySalary = monthlySalary;
    }
    
    
    public void setMonthlySalary() {
        this.monthlySalary = monthlySalary;
    }
    
    
    
    public double calculateSalary() {
        return monthlySalary;
    }
   
    @Override
    public void displayInfo() {
        System.out.println("---Full Time Employee---");
        super.displayInfo();
        System.out.println("Monthly Salary: P" + calculateSalary());
    }
}