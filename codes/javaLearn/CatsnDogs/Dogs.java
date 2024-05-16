package CatsnDogs;

//Dogs class
public class Dogs{
    //set the variables
    private String breed;
    private String color;

    Dogs(String breed, String color){
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
    public void eatingBones(){
        System.out.println("a " + color + " " + breed + " dog is eating a bone.");
    }
    public void guarding(){
        System.out.println("a " + color + " " + breed + " dog is guarding my house.");
    }
}
