package managers;

//manager class
public class Managers{
    //set the variables
    private String name;
    private int id;
    private double salary;
    private double bonus;

    Managers(String name, int id, double salary, double bonus){
        this.name = name;
        this.id = id;
        this.salary = salary;
        this.bonus = bonus;
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
    public void setBonus(double bonus){
        this.bonus = bonus;
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
    public double getBonus(){
        return bonus;
    }

    //call method
    public void work(){
        System.out.println("Manager name: " + name + " (" + id + "). Salary: " + salary + " Bonus: " + bonus);
    }

}