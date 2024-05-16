package CatsnDogs;

//Cats class
public class Cats{
    //set the variables
    private String breed;
    private String color;

    Cats(String breed, String color){
        this.breed = breed;
        this.color = color;
    }

    //setters
    public void setBreed(String breed){
        this.breed = breed;
    }
    public void setColor(String color){
        this.color = color;
    }

    //getters
    public String getBreed(){
        return breed;
    }
    public String getColor(){
        return color;
    }

    //call method
    public void eadFish(){
        System.out.println("a " + color + " " + breed + " cat is eating fish.");
    }
    public void catchMouse(){
        System.out.println("a " + color + " " + breed + " cat is catching mouse.");
    }

}