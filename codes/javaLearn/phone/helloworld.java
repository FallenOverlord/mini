package phone;
public class helloworld{
    
    public static void main(String[] args){

    //create a new object
    Phone honorPhone = new Phone("honor", "black", 1236);

    //use the setters to populate the object
    honorPhone.setPrice(1234);
    honorPhone.setColor("black");
    honorPhone.setBrand("honor");

    //call the call and sendMessage methods
    honorPhone.call();
    honorPhone.sendMessage();

    }

}








