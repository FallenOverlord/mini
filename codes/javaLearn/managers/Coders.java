package managers;

//coder class
public class Coders{
    //set the variables
    private String name;
    private int id;
    private double salary;

    Coders(String name, int id, double salary){
        this.name = name;
        this.id = id;
        this.salary = salary;
    }

    //setters
    public void setName(String name){
        this.name = name;
    }
    public void setId(int id){
        this.id = id;
    }
    public void setSalary(double salary){
        this.salary = salary;
    }

    //getters
    public String getName(){
        return name;
    }
    public int getId(){
        return id;
    }
    public double getSalary(){
        return salary;
    }

    //call method
    public void work(){
        System.out.println("Coder name: " + name + " (" + id + "). Salary: " + salary);
    }

}