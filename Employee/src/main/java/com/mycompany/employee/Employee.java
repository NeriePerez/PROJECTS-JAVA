package com.mycompany.employee;

class Employee {
    String name;
    String position;
    double salary;
    String department;
    
    public Employee(String name, String position, double salary, String department) {
        this.name = name;
        this.position = position;
        this.salary = salary;
        this.department = department;
    }
    
    public void displayInfo() {
        System.out.println("Name: " + name);
        System.out.println("Position: " + position);
        System.out.println("Salary: " + salary);
        System.out.println("Department: " + department);
        System.out.println("-----------------------------");
    }

    public void updateSalary(double newSalary) {
        if (newSalary > 0) {
            this.salary = newSalary;
            System.out.println(name + "'s salary updated to: " + newSalary);
        } else {
            System.out.println("Invalid salary amount. Must be greater than 0.");
        }
    }

    public void changeDepartment(String newDepartment) {
        String[] validDepartments = {"HR", "Engineering", "Sales"};
        for (String dept : validDepartments) {
            if (dept.equals(newDepartment)) {
                this.department = newDepartment;
                System.out.println(name + " moved to " + newDepartment + " department.");
                return;
            }
        }
        System.out.println("Invalid department change.");
    }

    public double getSalary() {
        return salary;
    }
}


