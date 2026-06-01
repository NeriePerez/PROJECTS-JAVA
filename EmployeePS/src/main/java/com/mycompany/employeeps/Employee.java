package com.mycompany.employeeps;

abstract class Employee{
    private String name;
    private String employeeID;
    
    Employee(String name, String employeeID) {
        this.name = name;
        this.employeeID = employeeID;
    }
    
    
    public String getName() {
        return name;
    }
    public void setName() {
        this.name = name;
    }
    
    public String getEmployeeID() {
        return employeeID;
    }
    public void setemployeeID() {
        this.employeeID = employeeID;
    }
    
    
    abstract public double calculateSalary();
    
    public void displayInfo(){
        System.out.println("Employee Name: " + name);
        System.out.println("Company ID: "+ getEmployeeID());
    }
}