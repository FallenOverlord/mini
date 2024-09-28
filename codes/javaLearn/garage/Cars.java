package garage;

//Cars class
public class Cars{
    //set the variables
    private String brand;
    private String color;
    private double price;
    private String id;

    Cars(String brand, String color, double price, String id){
        this.brand = brand;
        this.color = color;
        this.price = price;
        this.id = id;
    }

    public Cars() {
        // Initialize fields with default values
        this.brand = "Unknown Brand";
        this.color = "Unknown Color";
        this.price = 0.0;
        this.id = "unknown id";
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
    public void setId(String id){
        this.id = id;
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
    public String getId(){
        return id;
    }

}
