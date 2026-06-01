package com.mycompany.libraryms;

class DigitalBook extends Book{
    private String fileSize;
    
    DigitalBook(String title, String author, String fileSize) {
        super(title, author);
        this.fileSize = fileSize;
    }
    
    
    public String getFileSize() {
        return fileSize;
    }
    public void setFileSize()  {
        this.fileSize = fileSize;
    }
    
    
    public void displayInfo() {
        super.displayInfo();
        System.out.println("File SIze: " + getFileSize());
    }
}