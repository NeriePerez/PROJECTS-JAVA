package com.mycompany.employeeps;

import java.util.*;

class EmployeePS {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        
        Employee[] emp = new Employee[10];
        int EI = 0;
        boolean empty = true;
        
        
        while(true) {
            System.out.println("\n==========EMPLOYEE PAYROLL SYSTEM===========\n\n");
            
            System.out.print("1. Add Employee\n2. Employee List\nPick an Action: ");
            int action = scan.nextInt();
            scan.nextLine();
            
            if(action == 1) {
                System.out.print("\nNumbers of employees to register: ");
                int number = scan.nextInt();
                scan.nextLine();
                
                System.out.print("\n1. Full Time Employee\n2. Part Time Employee\nPick Employee Type: ");
                int type = scan.nextInt();
                scan.nextLine();
                
                if(type == 1) {
                    for(int i = 0; i < number; i++){
                        System.out.print("\nEnter name " + (EI+1) + ": ");
                        String name = scan.nextLine();
                        
                        System.out.print("Enter employee ID " + (EI+1) + ": ");
                        String employeeID = scan.nextLine();
                        
                        System.out.print("Enter monthy salary: P");
                        double monthlySalary = scan.nextDouble();
                        scan.nextLine();
                        
                        EI++;
                        empty = false;
                        emp[EI] = new FullTimeEmployee(name, employeeID, monthlySalary);
                    }
                } else if(type == 2) {
                    for(int i = 0; i < number; i++){
                        System.out.print("\nEnter name " + (EI+1) + ": ");
                        String name = scan.nextLine();
                        
                        System.out.print("Enter employee ID " + (EI+1) + ": ");
                        String employeeID = scan.nextLine();
                        
                        System.out.print("Enter hourly rate: P");
                        double hourlySalary = scan.nextDouble();
                        scan.nextLine();
                        
                        System.out.print("Enter hours worked: ");
                        int hoursWorked = scan.nextInt();
                        
                        EI++;
                        empty = false;
                        emp[EI] = new PartTimeEmployee(name, employeeID, hourlySalary, hoursWorked);
                    } 
                } else {
                    System.out.println("Incorrect input....\n\n");
                }
            } else if(action == 2) {
                System.out.println("\n=====EMPLOYEE LIST=====");
                int ei = 1;
                for(Employee i : emp) {
                    if(i != null) {
                        System.out.println("Employee no." + ei);
                        i.displayInfo();
                        ei++;
                    }
                } if(empty ) {
                    System.out.println("---none---\n\n");
                }
            }
        }
    }
}