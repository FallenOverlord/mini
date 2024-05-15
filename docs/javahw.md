# Java Practice Questions

## Arrays
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






