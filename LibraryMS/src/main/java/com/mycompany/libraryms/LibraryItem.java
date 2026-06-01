package com.mycompany.libraryms;

abstract class LibraryItem{
    protected String title;
    
    LibraryItem(String title) {
        this.title = title;
    }
    
    
    abstract public void displayInfo();
}