package teachers;
public class Teacher {

    private String name;
    private String major;
    private double wage;
    
    public Teacher() {
        // Initialize fields with default values
        this.name = "Unknown Brand";
        this.major = "Unknown Color";
        this.wage = 0;
    }

    Teacher(String name, String major, double wage){
        this.name = name;
        this.major = major;
        this.wage = wage;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setMajor(String major){
        this.major = major;
    }

    public void setWage(double wage){
        this.wage = wage;
    }

    public String getName(){
        return name;
    }

    public String getMajor(){
        return major;
    }

    public double getWage(){
        return wage;
    }

}