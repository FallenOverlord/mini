# Java

## JDK
https://yun.itheima.com/course/936.html?capid=3

```bash
C:\Users\Mars>javac -version
javac 21.0.1
C:\Users\Mars>java --version
java 21.0.1 2023-10-17 LTS
Java(TM) SE Runtime Environment (build 21.0.1+12-LTS-29)
Java HotSpot(TM) 64-Bit Server VM (build 21.0.1+12-LTS-29, mixed mode, sharing)

cmd cmmand win11
cls -> clear
exit -> close cmd
dir -> ls
```

## Basic Syntax

name of the java file **must** equal to the name of the class

```bash
public class hw{
    public static void main(String[] args) {System.out.println("hello world.\n");
    }
}

public class helloworld {

    public static void main(String[] args){
        System.out.println("helloworld!@");
        System.out.println("sdfkjdjs\n");
        System.out.println("sdfksjdf ksdkfj");
    }
    
}

Mars@Mars-Blade MINGW64 /e/用户/桌面/王中意/图书馆/codes (master)
$  /usr/bin/env C:\\Program\ Files\\Java\\jdk-11.0.15.1\\bin\\java.exe -agentlib:jdwp=transport=dt_socket,server=n,suspend=y,address=localhost:64908 -XX:+ShowCodeDetailsInExceptionMessages -cp C:\\Users\\Mars\\AppData\\Roaming\\Code\\User\\workspaceStorage\\8a96971809f062c8e5b5c26b61ae7110\\redhat.java\\jdt_ws\\codes_d54751c0\\bin helloworld 
helloworld!@
sdfkjdjs

sdfksjdf ksdkfj
```

## Standard Output
VS Code:
install extension pack for java  
debug java

1. println will automatically change line at the end.
2. print function will not create a new line at the end.
3. printf method will allow formatted string

```bash
System.out.printf("hello my anem is %s, age is %d", "dnkg", 34);

```

## Data Types
::::{grid}
:gutter: 2

:::{grid-item-card} Primaritive Type
byte(-138 t0 1w7)
short
int
long

//deciamls
double
float

//bolleans
boolean

//char
char ''
:::

:::{grid-item-card} Reference Type
pointers?
:::

::::
```bash

        int number = 234;
        double newnum = number;
        System.out.println(newnum);

#possible loss of data
#type casting
        double old = 32134.324234234;
        int sdf =(int) old;
        System.out.println(sdf);
```

## Maths operators
```bash
+ - * /

%

public static void main(String[] args){

    int gnum = 123;
    double bnum = 23.324;

    System.out.println(gnum/bnum);
    System.out.println((double)gnum/bnum);
}

5.273537986623221
5.273537986623221

syntactic sugar
-= += *= /= %=
```

## Relational Operators  
returns `true` or `false` as the result of the operation
```bash
==(check if the object is the same)
 != > < >= <=

logical operator
&& and operator
|| or operator

increment/decrement operator
++ --
a++ a--
++a --b
println(--a) vs println(a--)

```
### Ternary Operator
This is the shortcut for doing a `if-else` statement.  
Be aware that if there is an else if statement, the ternary operator cannot be used.

Think of its relationship with `if-else` statement as` number = number + 1` vs `number ++`.

```bash
condition ? expression1 : expression2
```
expression1 is the result if the condition is true.
expression2 is the result if the condition is false.

## Methods
We can define a new method in the class just like we can define a new function in a C or Python file.
```bash
public class test{

    //main method
    public static void main(String[] args){

    }

    //define a new method
    public static void buy(int number){
        number = 1000;
    }
}

```
the method we defined is call by value.  
the variable is only relavent within the method.  

### Q1
定义一个方法，该方法能够找出两个小数中的较小值并返回。在主方法中调用方法进行测试。
```bash
public class helloworld{

    public static void main(String[] args){
        int a = 1000;
        int b = 500;

        int smallerNum = findMin(a, b);

        int small = comparison(a, b);

        System.out.println(smallerNum);
        System.out.println(small);
    }

    //using the if-else statement
    public static int findMin(int a, int b){
        if (a <= b){
            return a;
        }else{
            return b;
        }
    }
    //using the ternary operator
    public static int comparison(int a, int b){
        return (a <= b)?a : b;
    }  

}


```

### Q2
定义一个方法，该方法能够找出三个整数中的最大值并返回。在主方法中调用方法测试执行。
```bash
public class helloworld{
    
    public static void main(String[] args){
        int a = 1000;
        int b = 500;
        int c = 200;

        System.out.println(findMax(a, b, c));

    }

    public static int findMax(int a, int b, int c){
        if (a <= b){
            return (b <= c) ? c : b;
        }else{
            return (a <= c) ? c : a;
        }
    }

}

```

### Q3
在主方法中通过键盘录入三个整数。定义一个方法，方法接收三个整数变量，在方法中从大到小依次打印三个变量。执行效果如下：
请输入第一个整数：10
请输入第二个整数：30
请输入第三个整数：20
从大到小的顺序是： 30 20 10 

```bash
import java.util.Scanner;
import java.util.Arrays;

public class helloworld{
    
    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);

        int[] array = new int[3];

        for (int i = 0; i < 3; i++){
            System.out.print("input num #" + i + ": ");
            int a = scanner.nextInt();
            array[i] = a;
            scanner.nextLine();
        }

        scanner.close();
        Arrays.sort(array);

        System.out.print("decdending order: ");

        for (int i = 2; i >= 0; i--){
            System.out.print(" " + array[i]);
        }
    }

}

```

### Q4
数字是有绝对值的，负数的绝对值是它本身取反，非负数的绝对值是它本身。请定义一个方法，方法能够得到小数类型数字的绝对值并返回。请定义方法并测试。
```bash
public class helloworld{
    
    public static void main(String[] args){
        double a = 23.34;
        double b = -13.24;
        double c = -7.63432;
        
        System.out.println("abs of a: " + abs(a));
        System.out.println("abs of b: " + abs(b));
        System.out.println("abs of c: " + abs(c));

    }



    public static double abs(double a){
        return (a <= 0) ? (-a) : a;
    }
}
```

### Q5
键盘录入一个正整数

定义一个方法,该方法的功能是计算该数字是几位数字,并将位数返回

在main方法中打印该数字是几位数

演示格式如下: (1)演示一: 请输入一个整数:1234 控制台输出:1234是4位数字 (2)演示二: 请输入一个整数:34567 控制台输出:34567是5位数字
```bash
import java.util.Scanner;

public class helloworld{
    
    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);
        System.out.print("input num: ");
        int a = scanner.nextInt();
        scanner.nextLine();

        System.out.println(a + " is a number with " + getDigits(a) + " digits.");
        
        scanner.close();
    }



    public static int getDigits(int a){

        int count = 0;

        do{
            a /= 10;
            count++;

        }while(a > 1);

        return count;
    }
}
```

### Q6
需求：

​ 定义一个方法equals(int[] arr1,int[] arr2).

功能：

​ 比较两个数组是否相等（长度和内容均相等则认为两个数组是相同的）

```bash
public class helloworld{
    
    public static void main(String[] args){

        int arr1[] = {1, 2, 3, 5, 6, 7, 8};
        int arr2[] = {1, 2, 3, 7, 8};

        equals(arr1, arr2);

    }



    public static void equals(int[] arr1,int[] arr2){
        
        boolean is_same = (arr1.length == arr2.length) ? true : false;

        if (is_same){
            for (int i = 0; i < arr1.length; i++){
                is_same = (arr1[i] == arr2[i])? is_same : false;
            }
    }

        System.out.println("equavalence: " + is_same);
    }
}
```

### Q7
需求：

​ 定义一个方法fill(int[] arr,int value)

功能：

​ 将数组arr中的所有元素的值改为value

```bash
import java.util.Arrays;

public class helloworld{
    
    public static void main(String[] args){

        int[] arr = new int[6];

        int arr1[] = {1, 2, 3, 5, 6, 7, 8, 9, 10, 11, 12, 12,34, 34};

        int value = -2;
        
        arr = fill(arr, value);

        arr1 = fill(arr1, 3);

        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(arr1));

    }



    public static int[] fill(int[] arr,int value){
        
        for (int i = 0; i < arr.length; i++){
            arr[i] = value;
        }

        return arr;
    }
}
```

### Q8
需求：

​ 定义一个方法fill(int[] arr,int fromIndex,int toIndex,int value)

功能：

​ 将数组arr中的元素从索引fromIndex开始到toIndex（不包含toIndex）对应的值改为value

```bash
import java.util.Arrays;

public class helloworld{
    
    public static void main(String[] args){

        int[] arr = new int[6];

        int arr1[] = {1, 2, 3, 5, 6, 7, 8, 9, 10, 11, 12, 12,34, 34};

        int value = -2;

        int fromIndex = 2;
        int toIndex = 5;
        
        arr = fill(arr, value, fromIndex, toIndex);

        arr1 = fill(arr1, 3, fromIndex, toIndex);

        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(arr1));

    }



    public static int[] fill(int[] arr, int value, int fromIndex,int toIndex){
        
        for (int i = 0; i < arr.length; i++){
            if (i >= fromIndex && i < toIndex){
                arr[i] = value;
            }
            
        }

        return arr;
    }
}
```

### Q9
需求：

​ 定义一个方法copyOf(int[] arr, int newLength)

功能：

​ 将数组arr中的newLength个元素拷贝到新数组中，并将新数组返回，从索引为0开始

```bash
import java.util.Arrays;

public class helloworld{
    
    public static void main(String[] args){

        int arr1[] = {1, 2, 3, 5, 6, 7, 8, 9, 10, 11, 12, 12,34, 34};

        System.out.println(Arrays.toString(copyOf(arr1, 3)));

    }

    public static int[] copyOf(int[] arr, int newLength){
        
        int[] newArr = new int[newLength];

        for (int i = 0; i < newLength; i++){
            newArr[i] = arr[i];
        }

        return newArr;
    }
}
```

### Q10
需求：

​ 定义一个方法copyOfRange(int[] arr,int from, int to)

功能：

​ 将数组arr中从索引from（包含from）开始，到索引to结束（不包含to）的元素复制到新数组中，

​ 并将新数组返回。

```bash
import java.util.Arrays;

public class helloworld{
    
    public static void main(String[] args){

        int arr1[] = {1, 2, 3, 5, 6, 7, 8, 9, 10, 11, 12, 12,34, 34};

        System.out.println(Arrays.toString(copyOfRange(arr1, 3, 7)));

    }

    public static int[] copyOfRange(int[] arr,int from, int to){
        
        int newLength = 0;

        for (int i = from; i < to; i++){
            newLength++;
        }

        int[] newArr = new int[newLength];

        for (int i = 0; i < arr.length; i++){
            if (i >= from && i < to){
                newArr[i - from] = arr[i];
            }
        }

        return newArr;
    }
}
```

### Q11
一个大V直播抽奖，奖品是现金红包，分别有{2,588,888,1000,10000}五个奖金。请使用代码模拟抽奖，打印出每个奖项，奖项的出现顺序要随机且不重复。打印效果如下：（随机顺序，不一定是下面的顺序）

888元的奖金被抽出
588元的奖金被抽出
10000元的奖金被抽出
1000元的奖金被抽出
2元的奖金被抽出

```bash
import java.util.Random;

public class helloworld{
    
    public static void main(String[] args){

        int arr[] = {2,588,888,1000,10000};

        printResult(arr);

    }

    public static void printResult(int[] arr){

        int[] randomArr = new int[arr.length];

        Random random = new Random(); 

        for (int i = 0; i < arr.length; i++){
            
            int randomIndex = random.nextInt(arr.length);

            boolean occupied = true;

            while (occupied){
                randomIndex = random.nextInt(arr.length);
                occupied = (randomArr[randomIndex] != 0) ? true: false;
            }

            randomArr[randomIndex] = arr[i];

        }


        for (int i = 0;i < arr.length; i++){
            System.out.println("a prize worth " + randomArr[i] + " has been taken!");
        }

    }
}

```

Console Output

```bash
Please input a string: 
bash
Original String:  b a s h
Randomfied String:  b h s a
```

## Strings

methods to declear a string
```bash
String name = "mars oid gog";

String name = new String("mars oid gog");



   public static void main(String[] args){

        String name = "dfana";
        String name2 = "dfana";

        String objectString = new String("dsfkjs");
        String objectString2 = new String("dsfkjs");

        System.out.println(name == name2);
        System.out.println(objectString == objectString2);
    }

true
false

concatanating print output
a + b + c, just like python

```
### Formatted Strings
just like c
```bash
"My name is %s. I am from %s, i am %d yars old. gpa = %f, win = %b" , name, country_name, age, (double)gpa, (boolean)result


    public static void main(String[] args){

        int age = 5;
        double score = 93.5;
        String name = "dag";
        Boolean male = true;

        String formattedString = String.format("my name is %s, myage is %d, mt score = %f, sex is male: %b", name, age, score, male);
        System.out.println(formattedString);

    }

my name is dag, myage is 5, mt score = 93.500000, sex is male: true
```

### String Functions

<var>.length() returns a int, it counts even the spaces in the string  
<var>.isEmpty() returns a bool  
<var>.toUpperCase() will not modify the var  
<var>.toLowerCase()  
<var1>.equals(<var2>) returns a bool  
<var1>.equalsIgnoreCase(<var2>) will Ignore Cases
<string_var1>.replace("<part of the string>", "<new part of the string>");
it doesn't change the original string. 

#check if the string contains a certain string
strstr() function in c
System.out.println(string.contains("mars"));
it will return a boolean

### String method: indexOf() 
find the position of a character or a substring within a string

```bash
String myString = "Hello, world!";
int index = myString.indexOf('o');  // Returns 4
int strIndex = myString.indexOf("world");  // Returns 7
```

### String method: substring()

extract a substring from a string

```bash
String myString = "Hello, world!";
String sub = myString.substring(7);  // Returns "world!"
String limitedSub = myString.substring(0, 5);  // Returns "Hello"
String sub2 = myString.substring(0); // Returns "Hello, world"

```
### Strings Practise Questions
#### Q1
键盘输入任意字符串，打乱里面的内容

```bash
import java.util.Scanner;
import java.util.StringBuilder;
import java.util.Random;

public class helloworld{
    
    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);

        char[] originalInput = new StringBuilder(scanner.nextLine()).toString().toCharArray();

        printArr(originalInput);

        printArr(randomfy(originalInput));

        
        scanner.close();
    }

    public static void printArr(char[] arr){
        for (int i = 0; i < arr.length; i++) {
            System.out.print(" " + arr[i]);
        }
    }

    public static char[] randomfy(char[] arr){

        Random random = new random();

        for (int i = 0; i < arr.length; i++) {
            char temp = null;

            int randomIndex = random.nextInt(arr.length);
            temp = arr[i];
            arr[i] = arr[randomIndex];
            arr[randomIndex] = temp;
        }

        return arr;
    }
}
```

#### Q2
生成验证码

内容：可以是小写字母，也可以是大写字母，还可以是数字

规则：

长度为5

内容中是四位字母，1位数字。

其中数字只有1位，但是可以出现在任意的位置。

```bash
import java.util.Random;

public class helloworld {

    private static final Random random = new Random(); // Single Random instance

    public static void main(String[] args) {

        int numberIndex = getRandomNum(5);
        int randomNumber = getRandomNum(10);
        char[] array = new char[5];
        char randomChar = (char) ('0' + randomNumber); // Correctly map number to character

        for (int i = 0; i < array.length; i++) {
            if (i == numberIndex) {
                array[numberIndex] = randomChar; // Use the char mapped from randomNumber
                System.out.println("Random Number as Char: " + randomChar);
            } else {
                array[i] = getRandomChar();
            }
        }

        printArr(array);
    }

    public static char getRandomChar() {
        boolean isUppercase = random.nextBoolean();
        return (char) (isUppercase ? 'A' + random.nextInt(26) : 'a' + random.nextInt(26));
    }

    public static int getRandomNum(int size) {
        return random.nextInt(size);
    }

    public static void printArr(char[] arr) {
        for (char c : arr) {
            System.out.print(" " + c);
        }
    }
}
```

Console Output
```bash
Random Number as Char: 4
 S q y y 4
```
#### Q3
请编写程序，由键盘录入一个字符串，统计字符串中英文字母和数字分别有多少个。比如：Hello12345World中字母：10个，数字：5个。

1、键盘录入一个字符串，用 Scanner 实现 2、要统计两种类型的字符个数，需定义两个统计变量，初始值都为0 3、遍历字符串，得到每一个字符 4、判断该字符属于哪种类型，然后对应类型的统计变量+1，判断字母时需要考虑大小写，条件比较复杂，怎样做才能使判断更简单呢？

```bash
import java.lang.StringBuilder;
import java.util.Scanner;

public class helloworld {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        char[] str = new StringBuilder(scanner.nextLine()).toString().toCharArray();

        int charCount = 0;
        int numCount = 0;

        for (int i = 0; i < str.length; i++){

            int num = (int)(str[i] + 'a');
            System.err.println(num);
            if (num >= 145 && num <= 154){
                numCount++;
            }else{
                charCount++;
            }

        }

        System.err.println("char: " + charCount + " int: " + numCount);
        scanner.close();
    }
}
```

Console Output

```bash

AaZz90
162
194
187
219
154
145
char: 4 int: 2
```

#### Q4
字符串的反转功能，判断是否相等功能

训练提示
1、判断是否对称，方法的返回值是什么类型？参数列表是什么？

2、怎样判断对称呢？如果可以将字符串反转，反转后发现跟原来的字符串完全一样，不就可以判断出来了吗，那么哪个类有字符串的反转功能呢？

```bash
import java.util.Scanner;
import java.lang.StringBuilder;

public class helloworld{
    
    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        char[] originalInput = new StringBuilder(input).toString().toCharArray();
        char[] reverseInput = new StringBuilder(input).reverse().toString().toCharArray();

        printArr(originalInput);
        System.out.println("");
        printArr(reverseInput);

        System.out.println("It is " + arrCmp(originalInput, reverseInput) + " that the input is symmetric.");

        
        scanner.close();
    }

    public static void printArr(char[] arr){
        for (int i = 0; i < arr.length; i++) {
            System.out.print(" " + arr[i]);
        }
    }

    public static boolean arrCmp(char[] arr1, char[] arr2){
        boolean is_same = true;
        for (int i = 0; i < arr1.length; i++){
            is_same = (arr1[i] == arr2[i]) ? is_same : false;
        }

        return is_same;
    }
}
```

#### Q5
请定义一个方法用于判断一个字符串是否是对称的字符串，并在主方法中测试方法。例如："abcba"、"上海自来水来自海上"均为对称字符串。

训练目标
字符串的反转功能，判断是否相等功能

训练提示
1、判断是否对称，方法的返回值是什么类型？参数列表是什么？

2、怎样判断对称呢？如果可以将字符串反转，反转后发现跟原来的字符串完全一样，不就可以判断出来了吗，那么哪个类有字符串的反转功能呢？

```bash
import java.util.Scanner;
import java.lang.StringBuilder;

public class helloworld{
    
    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        char[] originalInput = new StringBuilder(input).toString().toCharArray();
        char[] reverseInput = new StringBuilder(input).reverse().toString().toCharArray();

        printArr(originalInput);
        System.out.println("");
        printArr(reverseInput);

        System.out.println("It is " + arrCmp(originalInput, reverseInput) + " that the input is symmetric.");

        
        scanner.close();
    }

    public static void printArr(char[] arr){
        for (int i = 0; i < arr.length; i++) {
            System.out.print(" " + arr[i]);
        }
    }

    public static boolean arrCmp(char[] arr1, char[] arr2){
        boolean is_same = true;
        for (int i = 0; i < arr1.length; i++){
            is_same = (arr1[i] == arr2[i]) ? is_same : false;
        }

        return is_same;
    }
}
```


#### Q6
我国的居民身份证号码，由由十七位数字本体码和一位数字校验码组成。请定义方法判断用户输入的身份证号码是否合法，并在主方法中调用方法测试结果。
规则为：号码为18位，不能以数字0开头，前17位只可以是数字，最后一位可以是数字或者大写字母X。

训练目标
字符串的遍历、判断

训练提示
1、要判断字符串是否符合规则，方法的返回值类型是什么？参数列表是什么？

2、使用String的API，对每种不符合条件的情况作出判断，一旦发现不符合条件就可以结束方法的执行，返回结果了。

3、在主方法中创建键盘录入，调用方法，输入各种数据测试结果。

```bash
import java.util.Scanner;
import java.lang.StringBuilder;

// 0 ~ 9 | 145 ~ 154
public class helloworld{
    
    public static void main(String[] args){

        tInterface();
        
    }

    public static void tInterface() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            //get user input
            System.out.println("pls type in 'quit' or 'exit' to quit the program :)");
            System.out.print("Please input your id number: ");
            String input = scanner.nextLine();

            if (input.equals("quit") || input.equals("exit")) {
                break;  // Exit the loop after quitting
            } else {

                // Program logic
                if (input.isEmpty()) {
                    System.out.println("Input cannot be empty. Please try again.");
                    continue;
                }
                char[] originalInput = new StringBuilder(input).toString().toCharArray();
                printArr(originalInput);
                System.out.println("It is " + checkValidity(originalInput) + " that the input is valid.");

            }
        }

        scanner.close();
    }

    public static void printArr(char[] arr){
        for (int i = 0; i < arr.length; i++) {
            System.out.print(" " + arr[i]);
        }
        System.out.println("");
    }

    public static boolean checkValidity(char[] arr){
        boolean is_valid = true;

        if (arr.length != 18) return false;
        if (((int)arr[0]) == 48) return false;

        for (int i = 0; i < (arr.length - 1); i++){
            is_valid = (((int)arr[i]) >= 48 && ((int)arr[i]) <= 57) ? is_valid : false;
            System.out.print(" " + ((int)arr[i]));
        }

        System.out.println("");

        is_valid = (((int)arr[arr.length - 1]) >= 48 && ((int)arr[arr.length - 1]) <= 57 
        || (int)arr[arr.length - 1] == 88) ? is_valid : false;
        

        return is_valid;
    }
}
```

Console Output

```bash
pls type in 'quit' or 'exit' to quit the program :)
Please input your id number: inwkn34itrg
 i n w k n 3 4 i t r g
It is false that the input is valid.
pls type in 'quit' or 'exit' to quit the program :)
Please input your id number: 324jsafi
 3 2 4 j s a f i
It is false that the input is valid.
pls type in 'quit' or 'exit' to quit the program :)
Please input your id number: ifiaiaibbeqqorwfef
 i f i a i a i b b e q q o r w f e f
 105 102 105 97 105 97 105 98 98 101 113 113 111 114 119 102 101
It is false that the input is valid.
pls type in 'quit' or 'exit' to quit the program :)
Please input your id number: AIFNENVIEFIINISLFZ
 A I F N E N V I E F I I N I S L F Z
 65 73 70 78 69 78 86 73 69 70 73 73 78 73 83 76 70
It is false that the input is valid.
pls type in 'quit' or 'exit' to quit the program :)
Please input your id number: exit
```

#### Q7
定义一个方法，把 int 数组中的数据按照指定的格式拼接成一个字符串返回，调用该方法，并在控制台输出结果。

要求：

​ 1、如果传递的参数为空，返回null

​ 2、如果传递的数组元素个数为0，返回[]

​ 3、如果数组为int[] arr = {1,2,3}; ，执行方法后的输出结果为：[1, 2, 3]

训练目标
字符串的拼接

训练提示
1、定义方法的返回值是什么？参数列表是什么？

2、如果不做判断就对数组进行遍历，那么如果数组对象为空，会报出异常，怎样避免空指针异常呢？

3、拼接字符串必然涉及到频繁改变，该采用可变的字符序列StringBuilder比较好

4、遍历数组，按照题目格式拼接数组中的元素。

5、将字符串返回

6、在主方法中定义数组，并调用方法，打印结果

```bash
import java.util.Arrays;
public class helloworld{
    
    public static void main(String[] args){

        int arr1[] = {1, 2, 3, 5, 6, 7, 8, 9, 10, 11, 12, 12,34, 34};
        System.out.println(Arrays.toString(arr1));
    }
}
```

Console Output

```bash

[1, 2, 3, 5, 6, 7, 8, 9, 10, 11, 12, 12, 34, 34]
```

#### Q8
在String类的API中，有如下两个方法：

// 查找参数字符串str在调用方法的字符串中第一次出现的索引，如果不存在，返回-1
public int indexOf(String str)

// 截取字符串，从索引beginIndex（包含）开始到字符串的结尾
public String substring(int beginIndex)
请仔细阅读API中这两个方法的解释，完成如下需求。

现有如下文本：
"Java语言是面向对象的，Java语言是健壮的，Java语言是安全的，Java是高性能的，Java语言是跨平台的"。
请编写程序，统计该文本中"Java"一词出现的次数。

训练目标
String类API的灵活使用

训练提示
1、要找的子串是否存在，如果存在获取其出现的位置。这个可以使用indexOf完成。 2、如果找到了，那么就记录出现的位置并在剩余的字符串中继续查找该子串，而剩余字符串的起始位是出现位置再加上子串的长度。

3、以此类推，通过循环完成查找，如果找不到就是-1，每次找到用计数器记录次数。

```bash
public class helloworld{
    
    public static void main(String[] args){

        String str = "Java语言是面向对象的，Java语言是健壮的，Java语言是安全的，Java是高性能的，Java语言是跨平台的"。;
        System.out.println("There is a total of"  +  findOccurance(str) + " occurance of the str 'Java' in str " + str);
        
    }

    public static findOccurance(String str){

        int count = 0;

        while (myString.indexOf("Java") != -1){
            count++;
            str = str.substring(myString.indexOf("Java"););
        }

        return count;
    }
}

```

Console Output

```bash
语言是面向对象的，Java语言是健壮的，Java语言是安全的，Java是高性能的，Java语言是跨平台的。9
语言是健壮的，Java语言是安全的，Java是高性能的，Java语言是跨平台的。7
语言是安全的，Java是高性能的，Java语言是跨平台的。7
是高性能的，Java语言是跨平台的。6
语言是跨平台的。-1
There is a total of5 occurance of the str 'Java' in str Java语言是面向对象的，Java语言是健壮的，Java语言是安全的，Java是高性能的，Java语言是跨平台的。
```

## Input
```bash
#if the class is not automatically imported, it may need to be imported
#before declearing the class, import the scanner
import java.util.Scanner


Scanner scanner = new Scanner(System.in);
System.out.println("What is your name");
String name = scanner.nextLine();
scanner.close();
System.out.println(name);


#ask for a specific data type in the scanner
System.out.print("age: ");
int age = scanner.nextInt();
scanner.close();
System.out.printf("age is %d\n", age);

age: 3242
age is 3242

scanner.nextFloat();
scanner.nextBoolean();

#input buffer consumption
#everytime the nextLine methord is used, the whole line is read and the <enter> 
input by the user is consumed, however, if the use use other scanner methods, such as nextInt() method, the <enter> character will not be consumed. so it may interfere with the next input.
System.out.println("how old are your");
int age = scanner.nextInt();
scanner.nextLine();
System.out.println("what is your name");
String name = scanner.nextLine();
System.out.println(name + " is " + age + "years old.");
scanner.close();

#alternative of using nextInt() method
int age = Integer.parseInt(scanner.nextLine());

```

## Project 1: mini calculator
```bash
import java.util.Scanner;

public class helloworld {

        public static void main(String[] args){

    Scanner scanner = new Scanner(System.in);

    System.out.print("Number1: ");
    int num1 = scanner.nextInt();
    scanner.nextLine();

    System.out.print("Operation: ");
    String operation = scanner.nextLine();
    
    System.out.print("Number2: ");
    int num2 = scanner.nextInt();
    scanner.nextLine();


    double result;
    if(operation.equals("+")){
        result = num1 + num2;
        System.out.println("Result: " + result);
    }else if (operation.equals("-")){
        result = num1 - num2;
        System.out.println("Result: " + result);
    }else if (operation.equals("*")){
        result = num1 * num2;
        System.out.println("Result: " + result);
    }else if (operation.equals("/")){
        result = num1/num2;
        System.out.println("Result: " + result);
    }else{
        System.out.println("the operation type isnt supported");
    }

    scanner.close();
    }
}

#output
Number1: 34
Operation: /
Number2: 7
Result: 4.0
```

## Switch Cases Statement
The param. in the switch method is the name of the variable, and in the curly brackets are the cases the variable may present.
```bash
    switch(operation){
        case "+":
            result = num1 + num2;
            System.out.println("Result: " + result);
            break;
        case "-":
            result = num1 - num2;
            System.out.println("Result: " + result);
            break;
        case "*":
            result = num1 * num2;
            System.out.println("Result: " + result);
            break;
        case "/":
            result = num1 / num2;
            System.out.println("Result: " + result);
            break;
        default:
            System.out.println("Operation not supported");
    }
    scanner.close();
    }
```

## Arrays
the first index in the array starts at 0  

to initialize an array 
```bash
char vowls[] = new char[5]; 

int[] array = new int[3];

``` 
to populate an array
```bash
vowls[0] = 'a';
vowls[1] = 'e';
...

```
```{warning}

for an array of size 5 the max idex will be 4,
or you will get an out of bounds error
```

to declear and initialize an array at the same time
```bash
char vowels[] = {'a', 'e', 'i', 'o', 'u'};
```
to access elements in the array
```bash


System.out.println(vowls[2]);

```
to print the whole array out as a String
```bash
#first import the array mehode
import java.util.Arrays;

System.out.println(Arrays.toString(vowls));
```
you can also print out the entire array using loops

### Array Properties
the `.length` in arrays are a property  
to find the length of an array
```bash
int length_array = array.length;

int length_string = string.length();
```

### Sorting Method
a sorting mehod in arrays
```bash
#remeber to import the Array methods at the very beginning
import java.utils.Arrays;

#an out-or-order array
char vowels[] = {'i', 'e', 'a', 'u', 'o'};

Arrays.sort(vowels);

System.out.println(Arrays.toString(vowels));
```
This will sort the vowels array in `alphabetical` order  
Sorting an array within a certain range

```bash
Arrays.sort(array, StartingIndex, EndingIndex);
Arrays.sort(vowels, 0, 4); is equavalent to Array.sort(vowels);

sorting within a certain index
Arrays.sort(vowels, 1, 3);
```

### Searching Method
searcing methods oonly work on thoroughly sorted arrays
it will also require the Array Methods to be imported  
```bash
#remeber to import the Array methods at the very beginning
import java.utils.Arrays;

#an out-or-order array
char vowels[] = {'i', 'e', 'a', 'u', 'o'};

Arrays.sort(vowels);

char key = 'i';

int foundItemIndex = Arrays.binarySearch(vowels, key);

System.out.println(Array.toString(vowels));
System.out.println(foundItemIndex);
```
the foundItemIndex is the the index of the key in an 0-indexing array  

Simiar as sorting, we can search for a certain value within a certain index.
```bash
#remeber to import the Array methods at the very beginning
import java.utils.Arrays;

#an out-or-order array
char vowels[] = {'i', 'e', 'a', 'u', 'o'};

Arrays.sort(vowels);

char key = 'i';

int foundItemIndex = Arrays.binarySearch(vowels, 0, 3, key);

System.out.println(Array.toString(vowels));
System.out.println(foundItemIndex);

```
If the searching function failed to locate the keyValue, the output will be a negative number.
depending on what the key value is, the negative output will varies.
```{warning}

remember that the range at which the function will search the array is 0-indexed.
the param in the binarySearch function is between the arrays and the keyValue.
```
### Populating an Array
in order to populate an array, you can use the fill method, which also comes in the imported array method.
```bash
Array.fill(vowels, 'x');

```
this will fill the whole array with the character.  
However, you can fillup the array within a certain range.
```bash
Array.fill(vowels, 0, 2, 'b');
```

### Making a copy of an Array
arrays in Java are in reference types, so if you assign an array to another array, you are essentially pointing a pointer to a variable, if the original array is modified, the pointed array will also be modified.  
It is like an alis of the original arrayo.  
```bash

int num[] = {1, 2, 3, 5, 6};

int copy_of_num[] = Array.copyOf(num, num.length);

```
The default value for integers are 0.  
When copying an array, if you set the size of the new array larger than the original array, it will fill everything with 0.
```bash

int num[] = {1, 2, 3, 5, 6};

int copy_of_num[] = Array.copyOf(num, num.length + 2);


{1, 2, 3, 5, 6, 0, 0}
```

You can also only copy a certain range of the array
```bash
int num[] = {1, 2, 3, 5, 6, 7, 8};

int copy_of_num[] = Array.copyOfRange(num, 3, 5);

```
If the ending idex is larger than the original array, it will extent the size of the copied array.  

### Comparing the Arrays
if u just use the == operator, it will return faluse no matter what.
```bash
System.out.println(Arrays.equals(num, copy_of_num));

```

### Q1
请创建一个长度为6的整数数组，并为数组中的元素赋值。遍历数组，打印所有元素，元素之间用空格隔开。比如：

数组为：{1,2,3,4,5}
打印结果：1 2 3 4 5 

```bash

public class helloworld {

    public static void main(String[] args){

    int array[] = {1, 2, 3, 4, 5};

    for (int number = 0; number < 5; number++){
        System.out.print(array[number] + " ");
    }
    }
}
```
### Q2
现有一个小数数组{12.9, 53.54, 75.0, 99.1, 3.14}。请编写代码，找出数组中的最小值并打印。
```bash
import java.util.Arrays;

public class helloworld {

    public static void main(String[] args){

    double array[] = {12.9, 53.54, 75.0, 99.1, 3.14};

    Arrays.sort(array);

    System.out.println("the smallest item is: " + array[0]);

    }
}
```
A sorted array is in ascending order.

## Loops
same syntax as c
```bash
for (int i = 1; i < 10; i++){}

```
exactly the same  

loop specifically for arrays
```bash
for (int num : num){}
```
the while loop has the exact same syntax as c


## Arraylist


### Limitations of Arrays
Arrays cannot chagne its size after it is decleared.
The only way is to change its length is through copying and making a new string.

### Usage of Arraylists
To use an arraylist, you have to first import the arraylist class.  
```bash
import java.util.ArrayList;
```
To declare and initialize an empty ArrayList
```bash
ArrayList<Integer> numbers = new ArrayList<Integer>();  
ArrayList<String> list = new ArrayList<>();
```
because int, double and float are all primative types, you can make them reference types by using someing called an "wrapper" class.  

Integer is the reference type for int.  
Initializing an ArrayList with values  
```bash
    ArrayList<String> list = 
    new ArrayList<>(Arrays.asList("aaa", "bbb", "aaa", "aaa", "ccc", "bbb"));
```

### Adding elements to an ArrayList
```bash
numbers.add(1);
numbers.add(2);
list.add("aaa");
list.add(1, "bbb");
...
```
`add(int index, E element)`: Inserts the specified element at the specified position in the list.
To print out an ArrayList

### Accessing and Modifying values
`get(int index)`: Returns the element at the specified position in the list.  
`set(int index, E element)`: Replaces the element at the specified position in the list with the specified element.
```bash
String value = list.get(0);
list.set(0, "ccc");
numbers.set(2, Integer.valueOf(30));

numbers.set(index, the new value_in wrapper class)
```
One advantage of using the wapper class is that with the primative class and the wrapper class, you can distinguish between the same number

```bash
System.out.println(numbers.toString());

#it looks like an array
[1, 2, 3, 4, 5, 6]
```

To print an element in the arraylist, use the get() method.
```bash
System.out.println(numbers.get(3));
```
the agument in the numbers in the arraylist is the index of an 0-indexed array

#### remove elemnts
you can either remove by index, or you cna remove by value.  
`remove(int index)`: Removes the element at the specified position in the list.  
`remove(Object o)`: Removes the first occurrence of the specified element from the list.
```bash
numbers.remove(2);

list.remove(0);
list.remove("bbb");

numbers.remove(Integer.valueOf(4));

numbers.clear();
```

#### Sorting an ArrayList
```bash

import java.util.Comparator

#take one agument
numbers.sort(Comparator.naturalOrder());

numbers.sort(Comparator.reverseOrder());


```
#### Size Method
Continuing our example with the arraylist numbers

```bash
numbers.size();



```
#### Contains Method
`contains(Object o)`: Returns true if the list contains the specified element.  
`indexOf(Object o)`: Returns the index of the first occurrence of the specified element in the list, or -1 if the list does not contain the element.  
`lastIndexOf(Object o)`: Returns the index of the last occurrence of the specified element in the list, or -1 if the list does not contain the element.  
```bash
#returns a boolean expression
numbers.contains(Integer.valueOf(10));
int firstIndex = list.indexOf("aaa");
int lastIndex = list.lastIndexOf("aaa");
```

#### Checking if it is empty
```bash

#this also returns a bool
numbers.isEmpty();

```

### For Each Loop
For each element in the arrayList, preform a certain set of instructions.
```bash

numbers.forEach(number -> {
    System.out.println(number + "is the current element")
});


for (String s : list) {
    System.out.println(s);
}

```

In order to modify the element in the arrayList through a loop.
```bash

numbers.forEach(number -> {
    numbers.set(numbers.indexOf(number), number * 2);
});

```
This program will multiply each element by 2.  

### Converting an ArrayList to an array
`toArray()`: Returns an array containing all of the elements in the list in proper sequence.  
`toArray(T[] a)`: Returns an array containing all of the elements in the list in proper sequence; the runtime type of the returned array is that of the specified array.  

```bash
Object[] array = list.toArray();
String[] stringArray = list.toArray(new String[0]);

```

### ArrayList Practise Question
#### Q1
现有如下字符串元素：["aaa", "bbb", "aaa", "aaa", "ccc", "bbb"]，请将所有的元素按顺序存入ArrayList集合中，并遍历集合查看存储结果。

训练目标
ArrayList集合存储字符串元素，并遍历。

训练提示
1、创建ArrayList集合，泛型如何定义？

2、怎样将数据添加到集合中？

3、怎样遍历集合？

```bash
import java.util.ArrayList;
import java.util.Arrays;

public class helloworld {

    public static void main(String[] args){

    ArrayList<String> list = 
    new ArrayList<>(Arrays.asList("aaa", "bbb", "aaa", "aaa", "ccc", "bbb"));

    for (String s : list) {
        System.out.println(s);
    }

    }
}
```
Console Output

```bash
aaa
bbb
aaa
aaa
ccc
bbb

```

#### Q2
请定义教师（Teacher）类，属性包含姓名和专业名称。将若干教师对象存入ArrayList集合中，并遍历集合打印教师信息，格式如下：

姓名：赵老师, 专业：javase
姓名：钱老师, 专业：javaee
姓名：孙老师, 专业：php
姓名：李老师, 专业：python

```bash
package teachers;

import java.util.ArrayList;

public class helloworld {

    public static void main(String[] args){

    ArrayList<Teacher> list = new ArrayList<>();

    Teacher teacher1 = new Teacher();
    teacher1.setName("Blinda Wang");
    teacher1.setMajor("ECE");
    list.add(teacher1);

    Teacher teacher2 = new Teacher();
    teacher2.setName("Pirrer Sullivan");
    teacher2.setMajor("CIV");
    list.add(teacher2);

    Teacher teacher3 = new Teacher();
    teacher3.setName("Salma Elma");
    teacher3.setMajor("ECE");
    list.add(teacher3);

    for (Teacher s : list) {
        System.out.println("the name of teacher is " + s.getName()
        + " their major is " + s.getMajor());
    }

    }
}


package teachers;
public class Teacher {

    private String name;
    private String major;
    
    public Teacher() {
        // Initialize fields with default values
        this.name = "Unknown Brand";
        this.major = "Unknown Color";
    }

    Teacher(String name, String major){
        this.name = name;
        this.major = major;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setMajor(String major){
        this.major = major;
    }

    public String getName(){
        return name;
    }

    public String getMajor(){
        return major;
    }
}
```

Console Output

```bash
the name of teacher is Blinda Wang their major is ECE
the name of teacher is Pirrer Sullivan their major is CIV
the name of teacher is Salma Elma their major is ECE
```

#### Q3
有如下员工信息：

姓名：张三，工资：3000
姓名：李四，工资：3500
姓名：王五，工资：4000
姓名：赵六，工资：4500
姓名：田七，工资：5000
先需要将所有的员工信息都存入ArrayList集合中，并完成如下操作：

1、判断是否有姓名为“王五”的员工，如果有，改名为“王小五”

2、判断是否有姓名为“赵六”的员工，如果有，将其删除

3、给姓名为“田七”的员工，涨500工资

训练目标
ArrayList集合的修改元素和删除元素API

训练提示
1、需要定义员工类，将员工信息进行封装

2、创建ArrayList集合，创建员工对象，将所有员工对象存入集合

3、需要判断集合中元素的信息，那么肯定需要遍历集合获取到所有元素

4、ArrayList集合中，修改元素和删除元素的方法是什么？在遍历集合时，删除了集合中的元素，会不会对遍历产生影响呢？如果会，该怎么解决？

```bash
package teachers;

import java.util.ArrayList;
import java.util.Iterator;

public class helloworld {

    public static void main(String[] args){

    ArrayList<Teacher> list = new ArrayList<>();

    Teacher teacher1 = new Teacher();
    teacher1.setName("Blinda Wang");
    teacher1.setMajor("ECE");
    teacher1.setWage(22315);
    list.add(teacher1);

    Teacher teacher2 = new Teacher();
    teacher2.setName("Pirrer Sullivan");
    teacher2.setMajor("CIV");
    teacher2.setWage(235324);
    list.add(teacher2);

    Teacher teacher3 = new Teacher();
    teacher3.setName("Salma Elma");
    teacher3.setMajor("ECE");
    teacher2.setWage(324255);
    list.add(teacher3);

    Teacher teacher4 = new Teacher("Seica", "CIV" , 23425);
    list.add(teacher4);

    Teacher teacher5 = new Teacher("Scott Ramsay", "ChemEng", 3256343);
    list.add(teacher5);
    
    printTeacherInfo(list);

    checkSeica(list);
    printTeacherInfo(list);
    System.out.println("");

    delElma(list);
    printTeacherInfo(list);
    System.out.println("");

    RamsayWageRaise(list);
    printTeacherInfo(list);


    }

    public static void printTeacherInfo(ArrayList<Teacher> list){
        for (Teacher s : list) {
            System.out.println("the name of teacher is " + s.getName()
            + " their major is " + s.getMajor() + " their wage is " + s.getWage());
        }
    }

    public static void checkSeica(ArrayList<Teacher> list) {
        Iterator<Teacher> iterator = list.iterator();
        while (iterator.hasNext()) {
            Teacher s = iterator.next();
            if (s.getName().equals("Seica")) {
                s.setName("Big Seica");
            }
        }
    }

    public static void delElma(ArrayList<Teacher> list) {
        Iterator<Teacher> iterator = list.iterator();
        while (iterator.hasNext()) {
            Teacher s = iterator.next();
            if (s.getName().equals("Salma Elma")) {
                iterator.remove();
            }
        }
        
    }

    public static void RamsayWageRaise(ArrayList<Teacher> list) {
        Iterator<Teacher> iterator = list.iterator();
        while (iterator.hasNext()) {
            Teacher s = iterator.next();
            if (s.getName().equals("Scott Ramsay")) {
                s.setWage(s.getWage() + 500000);
            }
        }
    }
}

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
```

Console Output

```bash
the name of teacher is Blinda Wang their major is ECE their wage is 22315.0
the name of teacher is Pirrer Sullivan their major is CIV their wage is 324255.0
the name of teacher is Salma Elma their major is ECE their wage is 0.0
the name of teacher is Seica their major is CIV their wage is 23425.0
the name of teacher is Scott Ramsay their major is ChemEng their wage is 3256343.0
the name of teacher is Blinda Wang their major is ECE their wage is 22315.0
the name of teacher is Pirrer Sullivan their major is CIV their wage is 324255.0        
the name of teacher is Salma Elma their major is ECE their wage is 0.0
the name of teacher is Big Seica their major is CIV their wage is 23425.0
the name of teacher is Scott Ramsay their major is ChemEng their wage is 3256343.0      

the name of teacher is Blinda Wang their major is ECE their wage is 22315.0
the name of teacher is Pirrer Sullivan their major is CIV their wage is 324255.0        
the name of teacher is Big Seica their major is CIV their wage is 23425.0
the name of teacher is Scott Ramsay their major is ChemEng their wage is 3256343.0      

the name of teacher is Blinda Wang their major is ECE their wage is 22315.0
the name of teacher is Pirrer Sullivan their major is CIV their wage is 324255.0        
the name of teacher is Big Seica their major is CIV their wage is 23425.0
```
#### Q4
利用面向对象的思想设计一个图书管理系统。图书的属性有：编号，书名，作者，价格。要求提供如下功能：

1、提供操作菜单，可以选择要进行的操作。

2、可以添加图书，添加图书时，编号需要唯一，添加成功，返回到菜单。

3、可以查询图书，显示所有图书信息，然后返回到菜单。

4、可以根据书名，查询单本图书信息，显示信息后，返回到菜单。

5、可以删除图书，通过编号删除，删除成功后，返回到菜单。

6、可以修改图书的信息，但编号不可以修改，修改成功后，返回到菜单。

7、可以退出系统，结束程序运行。

提示：

1、所有图书信息由键盘录入

2、图书的价格可以定义为字符串类型，因为在键盘录入时，不可以先使用nextInt()方法获取整数然后再使用next()方法获取字符串，这样会导致next()方法获取不到数据。

训练目标
ArrayList集合API的综合运用、编程逻辑的锻炼

训练提示
1、首先需要创建一个图书类，封装图书信息。

2、提供操作菜单，可以通过键盘录入不同的数字来表示不同的操作，选择结构语句可以实现该需求。

3、管理图书，需要将图书存放起来，首先需要创建集合容器。

4、添加图书，即将图书存入集合中，但存入之前需要判定编号的唯一性，如果编号存在，需要重新录入。

5、查询所有图书，即为遍历集合显示信息。

6、查询单本图书，需要录入图书名称，遍历集合进行查询。

7、修改图书信息，需要根据编号先找到该图书，修改其除了编号之外的信息，存入集合覆盖原来的信息。

8、删除图书信息，需要根据编号先找到该图书，根据索引直接删除即可。

9、退出系统功能，借助结束程序的API实现即可。

```bash
package myLib;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class helloworld {

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        ArrayList<Book> list = new ArrayList<>();

        tInterface(list, scanner);
        scanner.close();
    }

    public static void tInterface(ArrayList<Book> list, Scanner scanner) {
        printNotice();
        while (true) {
            System.out.println("");
            System.out.print(">");
            String input = scanner.nextLine();

            if (input.equals("quit") || input.equals("exit")) {
                break;
            } else if (input.equals("add")){
                add(list, scanner);

            } else if (input.equals("show")){
                showAll(list);
            } else if (input.equals("search")){
                search(list, scanner);
            } else if (input.equals("delete")){
                delete(list, scanner);
            } else if (input.equals("modify")){
                modify(list, scanner);
            } else {
                printNotice();
            }

        }
    }

    public static void add(ArrayList<Book> list, Scanner scanner){
        System.out.print(">New Book ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        if ( checkDuplicate(list, id) ){
            System.out.println("ID duplicate, quitting!");
            return;
        }
        System.out.print(">New Book Name: ");
        String name = scanner.nextLine();
        System.out.print(">New Book Author: ");
        String author = scanner.nextLine();
        System.out.print(">New Book Price: ");
        double price = scanner.nextDouble();
        Book newBook = new Book(id, name, author, price);
        list.add(newBook);
    }

    public static boolean checkDuplicate(ArrayList<Book> list, int key){
        Iterator<Book> iterator = list.iterator();
        while (iterator.hasNext()) {
            Book s = iterator.next();
            if (s.getId() == key){
                return true;
            }
        }

        return false;
    }

    public static void showAll(ArrayList<Book> list){
        Iterator<Book> iterator = list.iterator();
        while (iterator.hasNext()) {
            Book s = iterator.next();
            System.out.println("Name: " + s.getName()
            + " ID: " + s.getId() + " Author:" + s.getAuthor()
            + " Price: " + s.getPrice());
        }
    }

    public static void search(ArrayList<Book> list, Scanner scanner){
        boolean avail = false;
        Iterator<Book> iterator = list.iterator();
        System.out.print(">Book Name: ");
        String input = scanner.nextLine();
        while (iterator.hasNext()) {
            Book s = iterator.next();
                if (s.getName().equals(input)){
                    System.out.println("Name: " + s.getName()
                    + " ID: " + s.getId() + " Author:" + s.getAuthor()
                    + " Price: " + s.getPrice());
                    avail = true;
                }
        }
        if(avail == false) System.out.println("No such entry with name " + input);
    }

    public static void delete(ArrayList<Book> list, Scanner scanner){
        Iterator<Book> iterator = list.iterator();
        System.out.print(">ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        if (checkDuplicate(list, id)){
            while (iterator.hasNext()) {
                Book s = iterator.next();
                    if (s.getId() == id){
                        iterator.remove();
                        System.out.println(">Sucess");
                    }
            }
        } else {
            System.out.println(">ID# Does Not Exist, Quitting!");
        }
    }

    public static void modify(ArrayList<Book> list, Scanner scanner){
        Iterator<Book> iterator = list.iterator();
        //get ID
        System.out.print(">ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        //check ID validity
        if (checkDuplicate(list, id)){
            while (iterator.hasNext()) {
                Book s = iterator.next();
                    if (s.getId() == id){
                        System.out.print(">New Book Name: ");
                        String name = scanner.nextLine();
                        s.setName(name);
                        System.out.print(">New Book Author: ");
                        String author = scanner.nextLine();
                        s.setAuthor(author);
                        System.out.print(">New Book Price: ");
                        double price = scanner.nextDouble();
                        s.setPrice(price);
                        System.out.println(">Sucess");
                    }
            }
        }else{
            System.out.println(">ID# Does Not Exist, Quitting!");
        }
    }

    public static void printNotice(){
        System.out.println("pls type in 'quit' or 'exit' to quit the program," + 
        "'add' to add to lib, 'show' to show all book info, 'search' to check book info after providing a bookname, 'delete' to delete an entry accorind to its id, 'modify' to modify an entry after providing a valid id :)");
    }
}

package myLib;

public class Book {

    private String name;
    private String author;
    private double price;
    private int id;
    
    public Book() {
        // Initialize fields with default values
        this.name = "Unknown Brand";
        this.author = "Unknown Color";
        this.price = 0;
        this.id = 0;
    }

    Book(int id, String name, String author, double price){
        this.id = id;
        this.name = name;
        this.author = author;
        this.price = price;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setAuthor(String author){
        this.author = author;
    }

    public void setPrice(double price){
        this.price = price;
    }

    public String getName(){
        return name;
    }

    public int getId(){
        return id;
    }

    public double getPrice(){
        return price;
    }

    public String getAuthor(){
        return author;
    }

}
```

Console Output

```bash
pls type in 'quit' or 'exit' to quit the program,'add' to add to lib, 'show' to show all book info, 'search' to check book info after providing a bookname, 'delete' to delete an entry accorind to its id, 'modify' to modify an entry after providing a valid id :)

>add
>New Book ID: 1
>New Book Name: Livewired
>New Book Author: David Eagleman
>New Book Price: 324

>pls type in 'quit' or 'exit' to quit the program,'add' to add to lib, 'show' to show all book info, 'search' to check book info after providing a bookname, 'delete' to delete an entry accorind to its id, 'modify' to modify an entry after providing a valid id :)  

>add
>New Book ID: 1
ID duplicate, quitting!

>add
>New Book ID: 2
>New Book Name: Verity
>New Book Author: Coleen Hoover
>New Book Price: 342

>pls type in 'quit' or 'exit' to quit the program,'add' to add to lib, 'show' to show all book info, 'search' to check book info after providing a bookname, 'delete' to delete an entry accorind to its id, 'modify' to modify an entry after providing a valid id :)  

>add
>New Book ID: 3                        
>New Book Name: Cybersecurity QuickStart
>New Book Author: Miles Wang
>New Book Price: 324

>pls type in 'quit' or 'exit' to quit the program,'add' to add to lib, 'show' to show all book info, 'search' to check book info after providing a bookname, 'delete' to delete an entry accorind to its id, 'modify' to modify an entry after providing a valid id :)  

>show
Name: Livewired ID: 1 Author:David Eagleman Price: 324.0
Name: Verity ID: 2 Author:Coleen Hoover Price: 342.0
Name: Cybersecurity QuickStart ID: 3 Author:Miles Wang Price: 324.0

>search
>Book Name: Verity
Name: Verity ID: 2 Author:Coleen Hoover Price: 342.0

>delete
>ID: 1
>Sucess

>show 
Name: Verity ID: 2 Author:Coleen Hoover Price: 342.0
Name: Cybersecurity QuickStart ID: 3 Author:Miles Wang Price: 324.0

>modify
>ID: 3
>New Book Name: Cybersecurity QuickStart
>New Book Author: Zhongyi Wang
>New Book Price: 3000
>Sucess

>pls type in 'quit' or 'exit' to quit the program,'add' to add to lib, 'show' to show all book info, 'search' to check book info after providing a bookname, 'delete' to delete an entry accorind to its id, 'modify' to modify an entry after providing a valid id :)  

>show
Name: Verity ID: 2 Author:Coleen Hoover Price: 342.0
Name: Cybersecurity QuickStart ID: 3 Author:Zhongyi Wang Price: 3000.0

>quit
```
#### Q5
```bash
package hackerrank;

import java.io.*;
import java.util.*;

public class list {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int length = Integer.parseInt(scanner.nextLine());  // Get the number of initial elements
        ArrayList<Integer> arr = new ArrayList<>();

        // Read initial array elements
        String[] elements = scanner.nextLine().split(" ");
        for (int i = 0; i < length; i++) {
            arr.add(Integer.parseInt(elements[i]));
        }

        int numQueries = Integer.parseInt(scanner.nextLine());  // Number of queries
        for (int i = 0; i < numQueries; i++) {
            String command = scanner.nextLine();
            if (command.equals("Insert")) {
                String[] parts = scanner.nextLine().split(" ");
                int index = Integer.parseInt(parts[0]);
                int value = Integer.parseInt(parts[1]);
                arr.add(index, value);
            } else if (command.equals("Delete")) {
                int index = Integer.parseInt(scanner.nextLine());
                arr.remove(index);
            }
        }

        for (int num : arr) {
            System.out.print(num + " ");
        }
        scanner.close();
    }
}

```
## Hash maps
Key - Value pairs, just like Python Dictionary.  
To use hashmap, you nned to first import the hashmap class.  
`import java.utils.HashMap;`  

```bash
HashMap<String, Integer>examScores = new HashMap<String, Integer>();

examScores.put("math", 75);
examScores.put("sshf", 85);

System.out.println(examScores.toString());
```

Elements in the hash map are not sorted.  
They appear in random order.  

To access the value in the key-value pair.  
```bash

System.out.println(examScores.get("English"));
```
If we want to have a default result for entries that are not present.
```bash
examScores.putIfAbsent("math", 70);
```
check if the key exist, if no, it will create an entry and associate it with a value.  
If we want to replace a value for a key that already exist.
```bash
examScores.replace("math", 100);
```
If the entry accessed does not exist, it will return 'null'.  
So to ensure that at least we get something from the query we can set a default value for searches.
```bash

examScores.getOrDefault(")

```

### Clear a hashmap

clear out the entire hashmap
```bash

examxScores.clear();

```

### size method
to check the number of elements in a hashmap
```bash
examScores.size();
```

### print out a hashmap
```bash
examScores.toString();
```

### Remove an elemtn

provide the key as the agument of the method

```bash
examScores.remove("Sociology");
```

### Check for a value
check if the hashmap contains a certain value.  
the value that you are looking for must be in the hashmap

```bash
examScores.containsValue(Integer.valueOf(100));
```

### Checking if a hashmap is empty

returns a boolean expression
```bash
examScores.isEmpty();
```

### Using forEach method
`subject` is the key, `score` is the value.  
forEach method is using a lamda method
```bash

examScores.forEach(action)


examScores.forEach((subject, score) -> {
    System.out.println(subject + ": " + score);
});
```
because there is multiple variables provided into the lambda function, we need a pair of parentheises.  

We can also loop throught the hashmap and update the values associated with each element.
```bash

examScores.forEach((subject, score) -> {
    examScores.replace(subject, score * 2);
});

```


## Stack
In computer science, a stack or LIFO (last in, first out) is an abstract data type that serves as a collection of elements, with two principal operations: push, which adds an element to the collection, and pop, which removes the last element that was added.

### Basic Operations of a Stack
Push: Add an element to the top of the stack.
Pop: Remove and return the top element of the stack. If the stack is empty, this operation might throw an exception.
Peek: Return the top element without removing it from the stack.
IsEmpty: Check if the stack is empty.

#### Basic Examples of using a Stack

```bash
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        // Creating a stack
        Stack<Integer> stack = new Stack<>();

        // Pushing elements onto the stack
        stack.push(10);
        stack.push(20);
        stack.push(30);

        // Displaying the top element
        System.out.println("Top element: " + stack.peek());

        // Popping elements from the stack
        while (!stack.isEmpty()) {
            System.out.println("Popped element: " + stack.pop());
        }
    }
}


```

A more mordern approach would be using deque as an alternative.

```bash
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
    public static void main(String[] args) {
        // Creating a stack using Deque
        Deque<Integer> stack = new ArrayDeque<>();

        // Pushing elements onto the stack
        stack.push(10);
        stack.push(20);
        stack.push(30);

        // Displaying the top element
        System.out.println("Top element: " + stack.peek());

        // Popping elements from the stack
        while (!stack.isEmpty()) {
            System.out.println("Popped element: " + stack.pop());
        }
    }
}

```

#### Balanced String Example
```bash
package hackerrank;

import java.util.*;

public class Stack {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Deque<String> stack = new ArrayDeque<>();

        while (scanner.hasNext()) {
            char[] originalInput = scanner.nextLine().toCharArray();
            boolean is_balanced = true;
            for (char c : originalInput) {
                if (c == '(' || c == '[' || c == '{') {
                    stack.push(String.valueOf(c));
                } else if (c == ')' || c == ']' || c == '}') {
                    String topEle = String.valueOf(stack.peek());
                    if (topEle.equals(String.valueOf('(')) || topEle.equals(String.valueOf('[')) || topEle.equals(String.valueOf('{'))) {
                        stack.pop();
                    } else {
                        is_balanced = false;
                        break;
                    }
                }
            }

            if (is_balanced && stack.isEmpty()) {
                System.out.println("true");
            } else {
                System.out.println("false");
                stack.clear();
            }
        }
    }
}
```

## Set
In Java, a Set is a collection that does not allow duplicate elements. This means it can be very useful for storing unique items. Java provides several implementations of the Set interface, each with different characteristics:

HashSet: Offers the best performance for the operations of adding, removing, and checking if an item exists. It does not maintain the order of elements.
LinkedHashSet: Similar to HashSet but also maintains the insertion order of the elements.
TreeSet: Stores elements in a sorted (ascending) order. It is useful when sorted order retrieval is needed.

### Basic set operations


### HashSet

```bash

import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Set<String> fruits = new HashSet<>();

        // Adding elements
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Cherry");

        // Adding a duplicate element (which will not be added)
        fruits.add("Apple");

        // Check if an element exists
        if (fruits.contains("Banana")) {
            System.out.println("Banana is in the set");
        }

        // Iterate over the set
        for (String fruit : fruits) {
            System.out.println(fruit);
        }

        // Size of the set
        System.out.println("There are " + fruits.size() + " elements in the set.");
    }
}

```

## Generic Types

### Generic Methods
Generic methods are methods that introduce their own type parameters. This is similar to declaring a generic type, but the type parameter's scope is limited to the method where it is declared. Using generic methods, you can write a single method declaration that can be called with arguments of different types, based on the type of arguments passed to the method. The compiler will ensure that the parameters are correctly typed.

```bash
public class GenericMethodExample {

    // A generic method to print an array of any type
    public static <T> void printArray(T[] array) {
        for (T element : array) {
            System.out.print(element + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Integer[] intArray = {1, 2, 3, 4, 5};
        Double[] doubleArray = {1.1, 2.2, 3.3, 4.4};
        String[] stringArray = {"hello", "world"};

        printArray(intArray);
        printArray(doubleArray);
        printArray(stringArray);
    }
}
```

## Queue

### Priority Queue
```bash
Class Priorities

    // Define a priority queue to store students with a comparator
    Define priorityQueue as PriorityQueue of Student

    Method getStudents(events as List of String) returns List of Student
        // Loop through each event in the events list
        For each event in events
            // Split the event string into components
            Split event into parts

            // Check the first part of the event to determine the action
            If parts[0] is "ENTER"
                // Create a new student using the remaining parts of the event
                student = new Student(parts[3] as Integer, parts[1] as String, parts[2] as Double)
                // Add the student to the priority queue
                priorityQueue.add(student)
            Else if parts[0] is "SERVED"
                // Remove the student with the highest priority from the queue
                priorityQueue.poll()

        // Create a list to hold the remaining students
        remainingStudents = new List of Student

        // Empty the priority queue into remainingStudents in priority order
        While priorityQueue is not empty
            remainingStudents.add(priorityQueue.poll())

        // Return the list of remaining students
        Return remainingStudents
End Class

```

```bash
package hackerrank;
import java.util.*;

public class PriorityQueuePractise {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int entries = scanner.nextInt();
        scanner.nextLine();

        // Read the next line of input
        String inputLine = scanner.nextLine();

        // Split the input line into parts based on spaces
        String[] parts = inputLine.split(" ");




}

class Student{
    private int id;
    private String name;
    private double cgpa;

    public Student(int id, String name, double cgpa) {
        this.id = id;
        this.name = name;
        this.cgpa = cgpa;
    }
    // Getter for the student ID
    public int getID() {
        return id;
    }

    // Getter for the student name
    public String getName() {
        return name;
    }

    // Getter for the student CGPA
    public double getCGPA() {
        return cgpa;
    }
}

class Priority{
    public List<Student> getStudents(List<String> events){
        PriorityQueue<Student> Q = new PriorityQueue<>();

        for (String event: events){
            // Split the input line into parts based on spaces
            String[] parts = event.split(" ");

            // Assign and parse parts to variables
            String op = parts[0];
            String name = parts[1];
            double cgpa = Double.parseDouble(parts[2]);
            int id = Integer.parseInt(parts[3]);

            switch (op){
                case "SERVED":{
                    //search and deque
                    Q.poll();
                    break;
                }
                case "ENTER":{
                    //queue in
                    Student student = new Student(id, name, cgpa);
                    Q.offer(student);
                    break;
                }
            }
            //order Q into a proiority queue


        }

        //print remaining list

    }

    @Override
    public int compare(Student s1, Student s2) {
        if (Double.compare(s1.getCGPA(), s2.getCGPA()) == 0) {
            return s1.getName().compareTo(s2.getName());
        }
        return Double.compare(s2.getCGPA(), s1.getCGPA());
    }

    }

}
```








