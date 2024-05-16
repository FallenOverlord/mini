package garage;

//Cars class
public class Cars{
    //set the variables
    private String brand;
    private String color;
    private double price;

    Cars(String brand, String color, double price){
        this.brand = brand;
        this.color = color;
        this.price = price;
    }

    public Cars() {
        // Initialize fields with default values
        this.brand = "Unknown Brand";
        this.color = "Unknown Color";
        this.price = 0.0;
    }

    //setters
    public void setBrand(String brand){
        this.brand = brand;
    }
    public void setColor(String color){
        this.color = color;
    }
    public void setPrice(double price){
        this.price = price;
    }

    //getters
    public String getBrand(){
        return brand;
    }
    public String getColor(){
        return color;
    }
    public double getPrice(){
        return price;
    }

}
