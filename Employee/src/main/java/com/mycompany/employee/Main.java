package com.mycompany.employee;
import java.util.*;

class Main {
    public static void main(String[] args) {
        ArrayList<Employee> employees = new ArrayList<>();
        
        employees.add(new Employee("Alice", "Developer", 50000, "Engineering"));
        employees.add(new Employee("Bob", "Manager", 70000, "HR"));
        employees.add(new Employee("Charlie", "Sales Executive", 40000, "Sales"));

        System.out.println("Initial Employee Details:");
        for (Employee emp : employees) {
            emp.displayInfo();
        }

        // Update salary and department of Employee 1 (Alice)
        employees.get(0).updateSalary(55000);
        employees.get(0).changeDepartment("HR");

        // Calculate total salary expense
        double totalSalaryExpense = 0;
        for (Employee emp : employees) {
            totalSalaryExpense += emp.getSalary();
        }

        System.out.println("Total Salary Expense: " + totalSalaryExpense);
    }
}


