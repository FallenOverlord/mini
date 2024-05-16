package CatsnDogs;
public class helloworld{
    
    public static void main(String[] args){

    //create a new object
    Cats persianCat = new Cats("Persian", "black");
    Dogs hasky = new Dogs("Hasky", "white");

    //call the call and sendMessage methods
    persianCat.eadFish();
    hasky.eatingBones();
    persianCat.catchMouse();
    hasky.guarding();

    }

}