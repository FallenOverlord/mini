package managers;

public class helloworld{
    
    public static void main(String[] args){

    //create a new object
    Coders mars = new Coders("sram", 10035252, 213.53);
    Managers boss = new Managers("boos", 23423345, 234.51, 3242.2);

    //call the call and sendMessage methods
    mars.work();
    boss.work();

    }

}
