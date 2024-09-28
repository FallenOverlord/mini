package myLib;

public class Book {

    private String name;
    private String author;
    private double price;
    private int id;
    
    public Book() {
        // Initialize fields with default values
        this.name = "Unknown Brand";
        this.author = "Unknown Color";
        this.price = 0;
        this.id = 0;
    }

    Book(int id, String name, String author, double price){
        this.id = id;
        this.name = name;
        this.author = author;
        this.price = price;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setAuthor(String author){
        this.author = author;
    }

    public void setPrice(double price){
        this.price = price;
    }

    public String getName(){
        return name;
    }

    public int getId(){
        return id;
    }

    public double getPrice(){
        return price;
    }

    public String getAuthor(){
        return author;
    }

}
