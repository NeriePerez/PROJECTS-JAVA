package com.mycompany.libraryms;

import java.util.*;

class LibraryMS{
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        
        LibraryItem[] libraryB = new Book[10];
        LibraryItem[] libraryD = new DigitalBook[10];
        int BI = 0;
        int DI = 0;
        boolean emptyB = true;
        boolean emptyD = true;
        
        
        while(true) {
            System.out.println("==========LIBRARY MANAGEMENT SYSTEM==========\n\n");
            
            System.out.print("1. Add Book\n2. Book List\nPick an Action: ");
            int action = scan.nextInt();
            scan.nextLine();
            
            if(action == 1) {
                System.out.print("\nNumber of books you to enter: ");
                int number = scan.nextInt();
                scan.nextLine();
                
                System.out.print("\n1. Book\n2. Digital Book\nPick the type of book: ");
                int type = scan.nextInt();
                scan.nextLine();
                
                if(type == 1) {
                    for(int i = 0; i < number; i++) {
                        System.out.print("\nEnter title " + (BI+1) + ": ");
                        String title = scan.nextLine();
                    
                        System.out.print("Enter author name " + (BI+1) + ": ");
                        String author = scan.nextLine();
                    
                        BI++;
                        emptyB = false;
                        libraryB[BI] = new Book(title, author);
                    }
                } else if(type == 2){
                    for(int i = 0; i < number; i++) {
                        System.out.print("\nEnter title " + (DI+1) + ": ");
                        String title = scan.nextLine();
                    
                        System.out.print("Enter author name " + (DI+1) + ": ");
                        String author = scan.nextLine();
                        
                        System.out.print("Enter file size " + (DI+1) + ": ");
                        String fileSize = scan.nextLine();
                    
                        DI++;
                        emptyD = false;
                        libraryD[DI] = new DigitalBook(title, author, fileSize);
                    } 
                } else{
                    System.out.println("Incorrect input...\n\n");
                }
            } else if(action == 2) {
                System.out.println("\n\n=====BOOK LIST=====");
                
                System.out.println("===BOOK===");
                int bi = 1;
                for(LibraryItem i : libraryB) {
                    if(i != null) {
                        System.out.println("Book no." + bi);
                        i.displayInfo();
                        bi++;
                    } 
                } if(emptyB) {
                    System.out.println("---none---\n");
                }
                
                System.out.println("\n===DIGI BOOK===");
                int di = 1;
                for(LibraryItem i : libraryD) {
                    if(i != null){
                        System.out.println("DigiBook no." + di);
                        i.displayInfo();
                        di++;
                    }
                } if(emptyD) {
                    System.out.println("---none---\n");
                }
            } else{
                System.out.println("Incorrect input...\n\n");
            }
        }
    }
}