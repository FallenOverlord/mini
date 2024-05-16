package phone;

public class Phone{
    //set the variables
    private String brand;
    private double price;
    private String color;

    Phone(String brand, String color, double price){
        this.brand = brand;
        this.color = color;
        this.price = price;
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

    //call method
    public void call(){
        System.out.println("im calling with a " + color + " " + brand + " phone, which is worth " + price + " RMB");
    }

    //sendMessage method
    public void sendMessage(){
        System.out.println("im sending a message with a " + color + " " + brand + " phone, which is worth " + price + " RMB");
    }

}
