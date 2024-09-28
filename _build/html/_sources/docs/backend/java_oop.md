# Java OOP
## Object Oriented Programming

creating classes.  
make a new file and a new class that have the same name.  
```bash
public class User{

}

User.java
```
now we can put info pertaining to the user in the class, i.e. name and birthday
```bash
public String name;
public LocalDate birthday;
```
to use the LocalDate Class, we must import it first thing into the program  
in the main function
```bash
public static void main(String[] args){

}
```
we will create a new object named youngerUser, declared variable will be initialized as null.
```bash

User youngerUser = new User();

youngerUser.name = "dsf";
youngerUser.birthDay = LocalDate.parse("1008-01-03");

//to access the LocalDate data as String
youngerUser.birthDay.toString();


```
If you write a public method, it will be available to the entire program
```bash
//import Period and LocalDate classes
//in the User class
public int age() {
    //this will calculate the difference between now and birthday
    int age = Period.between(this.birthDay, LocalDate.now());
}

public void borrow(Book book){
    this.books.add(book);

}
```
if the method doesnt return anything, the type is void  
the method borrow is accepting parameters  
initializea an ArrayList Variable
```bash
import java.util.ArrayList;

public ArrayList<Book> books = new ArrayList<book>;
```

accessing the values that is a custom class
```bash
//implement a toString method int the custom class
public String toString{
    return String.format("%s by %s.", this.title, this.author);
}

```

### Constructor
every class in java have a special method called the consttuctor, it is responsible for initializing the values of the class

```bash
User(String name, String birthDay){
    this.name = name;
    this.birthDay = LocalDate.parse(birthDay);
}


```
the constructor method is called where the class is defined  
and when we declare a new variable in that class  
```bash

User user = newUser("dsnfds", "2002-02-03");

```
We can create new methods when defineing the user class to make printing out the names eaiser
```bash

public String getUserName(){
    return this.name;
}

public String getBirthDay(){
    return this.birthDay.toString();
}
```
user.birthDay is still in LocalDate format, so we need to convert it back to String when returning it.  
Using the newly created get method to print the name  
```bash
user.getBirthDay();

user.getUserName();
```
we can make the variables in the class private, implement the get method and use the constructor method.  
This will make the data more secure.
```bash
private String title;
private String author;

public String getTitle().......
public String getAuthor()...

//constructor method
Book(String title, String author){
    this.autor = author;
    this.title = title;
}

//update  the object creation process
Book book = new Book("little princess", "fiarhan neight");
```
Dont forget to update the object creation process

### Inheritance
make a default class that has the most common methods.  
make a child class that inherit all the properties and methods and then add some specific methods and properties. 
We can firt initialize a custom class and declear all the propeties, then we put all the properties into the constructor method.
```bash
public class Book{

    private String title;
    private String author;
    private int pageCount;

    Book (String title, String author, int pageCount){
        this.title = title;
        this.author = author;
        this.pageCount = pageCount;
    }

}
```
This is the parent class, now is the time to make a child class.  
First create a new java file, the name of the new child class must be the same as the name of the java file.  
Then we delcear the child class as an extension of the parent class.
```bash

public class AudioBook extends Book {
    private int time;

    //constructor
    AudoBook(String title, String author, int pageCount, int time){
        super(title, author, pageCount);
        this.AudoBook = AudoBook
    }
}

```
super refoers to the pareent class in the current class

### Practise Questions

#### Q1
题目1（完成）
定义手机类，手机有品牌(brand),价格(price)和颜色(color)三个属性，有打电话call()和sendMessage()两个功能。

请定义出手机类，类中要有空参、有参构造方法，set/get方法。

定义测试类，在主方法中使用空参构造创建对象，使用set方法赋值。

调用对象的两个功能，打印效果如下：

正在使用价格为3998元黑色的小米手机打电话....
正在使用价格为3998元黑色的小米手机发短信....

```bash
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
```

#### Q2

定义一个女朋友类。女朋友的属性包含：姓名，身高，体重。行为包含：洗衣服wash()，做饭cook()。另外定义一个用于展示三个属性值的show()方法。请在测试类中通过有参构造方法创建对象并赋值，然后分别调用展示方法、洗衣服方法和做饭方法。打印效果如下：

我女朋友叫凤姐,身高155.0厘米,体重130.0斤
女朋友帮我洗衣服
女朋友给我做饭

```bash
package gf;

public class Gf{
    //set the variables
    private String name;
    private double height;
    private double weight;

    Phone(String name, double height, double weight){
        this.name = name;
        this.height = height;
        this.weight = weight;
    }

    //setters
    public void setName(String name){
        this.name = name
    }
    public void setHeight(double height){
        this.height = height;
    }
    public void setWeight(double weight){
        this.weight = weight;
    }

    //getters
    public String getName(){
        return name;
    }
    public String getHeight(){
        return height;
    }
    public double getWeight(){
        return weight;
    }

    //call method
    public void wash(){
        System.out.println("im calling with a " + color + " " + brand + " phone, which is worth " + price + " RMB");
    }

    //sendMessage method
    public void cook(){
        System.out.println("im sending a message with a " + color + " " + brand + " phone, which is worth " + price + " RMB");
    }

    public void show(){
        System.out.println("her name is " + name + ". She weigh " + weight + " and she is "  + height + " cm tall.");
    }

}

package gf;

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

```

#### Q3
定义项目经理类Manager。属性：姓名name，工号id，工资salary，奖金bonus。行为：工作work() 定义程序员类Coder。属性：姓名name，工号id，工资salary。行为：工作work()

要求：

​ 1.按照以上要求定义Manager类和Coder类,属性要私有,生成空参、有参构造，set和get方法​ 2.定义测试类,在main方法中创建该类的对象并给属性赋值(set方法或有参构造方法)​ 3.调用成员方法,打印格式如下:

工号为123基本工资为15000奖金为6000的项目经理张三正在努力的做着管理工作,分配任务,检查员工提交上来的代码.....
工号为135基本工资为10000的程序员李四正在努力的写着代码......

```bash
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
```

console output
```bash
Coder name: sram (10035252). Salary: 213.53
Manager name: boos (23423345). Salary: 234.51 Bonus: 3242.2
```

#### Q4
定义猫类Cat。属性:毛的颜色color，品种breed。行为:吃饭eat()，抓老鼠catchMouse() 定义狗类Dog。属性:毛的颜色color，品种breed。行为:吃饭()，看家lookHome() 要求:​ 1.按照以上要求定义Cat类和Dog类,属性要私有,生成空参、有参构造，set和get方法​ 2.定义测试类,在main方法中创建该类的对象并给属性赋值(set方法或有参构造方法)​ 3.调用成员方法,打印格式如下:

花色的波斯猫正在吃鱼.....
花色的波斯猫正在逮老鼠....
黑色的藏獒正在啃骨头.....
黑色的藏獒正在看家.....

```bash
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

//Dogs class
public class Dogs{
    //set the variables
    private String breed;
    private String color;

    Dogs(String breed, String color){
        this.brand = breed;
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
        return brand;
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

public class helloworld{
    
    public static void main(String[] args){

    //create a new object
    Cats persianCat = new Cats("Persian", "black");
    Dogs hasky = new Dogs("Hasky", "white");

    //call the call and sendMessage methods
    persianCat.eatFish();
    hasky.eatingBones();
    pearsianCat.catchMouse();
    hasky.guarding();

    }

}
```

console output

```bash
a black Persian cat is eating fish.
a white Hasky dog is eating a bone.
a black Persian cat is catching mouse.
a white Hasky dog is guarding my house.
```

#### Q5
​ 定义数组存储3部汽车对象。

​ 汽车的属性：品牌，价格，颜色，车牌号。

​ 创建三个汽车对象，数据通过键盘录入而来，并把数据存入到数组当中。
​ 要求，计算出三部汽车的平均价格
统计价格比平均值低的汽车有几个？并把她们的所有信息打印出来。
​ 要求1：添加的时候需要进行车牌号的唯一性判断。
       a.k.a get a new Cars object, if the car ID already exist, the system will not add the new one into the array, if it is unique, then the system will create a larger array and add the new car info into the database

​ 要求2：添加完毕之后，遍历所有汽车信息。

​ 要求3：通过id删除汽车信息

​ 如果存在，则删除，如果不存在，则提示删除失败。

​ 要求4：删除完毕之后，遍历所有汽车信息。

​ 要求5：车牌号为 GH835 的汽车，价格涨了8000

```bash
package garage;

//Cars class
public class Cars{
    //set the variables
    private String brand;
    private String color;
    private double price;

    Cars(String brand, String color, double price){
        this.brand = brand;
        this.color = color;
        this.price = price;
    }

    public Cars() {
        // Initialize fields with default values
        this.brand = "Unknown Brand";
        this.color = "Unknown Color";
        this.price = 0.0;
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

}

package garage;

import java.util.Scanner;

public class helloworld {
    
    public static void main(String[] args) {
    
        Scanner scanner = new Scanner(System.in);

        // Create an array to store Cars objects
        Cars[] myCars = new Cars[3];
        
        // Initialize each Cars object and set the brand
        for (int i = 0; i < myCars.length; i++) {
            myCars[i] = new Cars(); // Initialize the Cars object at index i
            System.out.println("please input brand for car #" + (i + 1));
            myCars[i].setBrand(scanner.nextLine());
        }

        // Set the color for each car
        for (int i = 0; i < myCars.length; i++) {
            System.out.println("please input color for car #" + (i + 1));
            myCars[i].setColor(scanner.nextLine());
        }

        // Set the price for each car
        for (int i = 0; i < myCars.length; i++) {
            System.out.println("please input price for car #" + (i + 1));
            myCars[i].setPrice(scanner.nextDouble());
            scanner.nextLine(); // Consume the remaining newline
        }

        scanner.close();

        printCarDetails(myCars);
    }

    public static void printCarDetails(Cars[] myCars) {

        double totalPrice = 0;

        System.out.println("Car Details:");
        for (Cars car : myCars) {
            System.out.println("Brand: " + car.getBrand() + ", Color: " + car.getColor() + ", Price: " + car.getPrice());
            totalPrice += car.getPrice();
        }

        double avgPrice = (totalPrice / myCars.length);
        System.out.println("Average: " + avgPrice);

        for (Cars car : myCars) {
            if (car.getPrice() < avgPrice) {
                System.out.println(car.getBrand() + "'s price is lower than the average price. (" + car.getPrice() + " < " + avgPrice + ")");
            }
        }

    }
}
```

console output

```bash
please input brand for car #1
audi
please input brand for car #2
BMW
please input brand for car #3
Xpeng
please input color for car #1
black 
please input color for car #2
white
please input color for car #3
green
please input price for car #1
3243
please input price for car #2
325423
please input price for car #3
1242
Car Details:
Brand: audi, Color: black, Price: 3243.0
Brand: BMW, Color: white, Price: 325423.0
Brand: Xpeng, Color: green, Price: 1242.0
Average: 8937.0


Car Details:
Brand: BYD, Color: black, Price: 34235.0
Brand: Toyota, Color: grey, Price: 23511.0
Brand: Landrover, Color: white, Price: 23452.0
Average: 27066.0
Toyota's price is lower than the average price. (23511.0 < 27066.0)
Landrover's price is lower than the average price. (23452.0 < 27066.0)

```

#### Q6
​ 定义数组存储3个学生对象。

​ 学生的属性：学号，姓名，年龄。

​ 要求1：添加的时候需要进行学号的唯一性判断。

​ 要求2：添加完毕之后，遍历所有学生信息。

​ 要求3：通过id删除学生信息

​ 如果存在，则删除，如果不存在，则提示删除失败。

​ 要求4：删除完毕之后，遍历所有学生信息。

​ 要求5：id为2的学生，年龄+1岁

```bash
public class Student {
    private int id;
    private String name;
    private int age;

    public Student() {
    }

    public Student(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}


public class Test {
    public static void main(String[] args) {

        //1.创建一个数组用来存储学生对象
        Student[] arr = new Student[3];
        //2.创建学生对象并添加到数组当中
        Student stu1 = new Student(1, "zhangsan", 23);
        Student stu2 = new Student(2, "lisi", 24);

        //3.把学生对象添加到数组当中
        arr[0] = stu1;
        arr[1] = stu2;


        //要求1：再次添加一个学生对象，并在添加的时候进行学号的唯一性判断。
        Student stu4 = new Student(1, "zhaoliu", 26);

        //唯一性判断
        //已存在 --- 不用添加
        //不存在 --- 就可以把学生对象添加进数组
        for (Student student : arr) {
            if (stu4.getId() == student.getId){

            }else{

            }
        }
    }

    public static void printArr(Student[] arr){
        for (int i = 0; i < arr.length; i++) {
            Student stu = arr[i];
            if(stu != null){
                System.out.println(stu.getId() + ", " + stu.getName() + ", " + stu.getAge());
            }
        }
    }

    public static void addStu(Student[] arr, Student stu4){

        boolean is_space_left = false;
        for (Student student : arr) {
            is_space_left = (student.getId() == null) ? true : is_space_left;
        }

        count = 0;

        if (is_space_left){
            while (arr[count] != null){
                count++;
            }
            Student[count].setName(sut4.getName());
            Student[count].setId(sut4.getId());
            Student[count].setAge(sut4.getAge());

        }else{
            
            
        }
    }
}
```



