package com.mycompany.libraryms;

class Book extends LibraryItem{
    private String author;
    
    Book(String title, String author) {
        super(title);
        this.author = author;
    }
    
    
    public String getAuthor() {
        return author;
    }
    public void setAuthor() {
        this.author = author;
    }
    
    
    public void displayInfo() {
        System.out.println("Title: " + title);
        System.out.println("Author: " + getAuthor());
    }
}