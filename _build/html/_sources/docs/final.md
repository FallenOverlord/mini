# APS 105 Final Review

## Index
1. Pointers and Arrrays
2. Strings
3. Data Structures and Linked List
4. Searching Algorithms

## Pointers and Arrays

### How to use Random in C

Example
```bash
int a = rand()%51;
```
This will generate a random int between 0 and 50, where there are a total of 51 integers.  
```bash
int b = rand()%51 - 50;
```
This is how you generate negative numbers using the random library, this will generate a random int between 0 and -50.  
```bash
int c = (rand()%51) * 2;
```
This is how you generate only even numbers using the random library, c will b a even number between 0 and 100.  



### How to dynamically allocate an variable

Example
```bash
int* myArray = (int*) malloc (5 * sizeof(int));
```
`int* myArray` : the variable is a int pointer type  
`(int*)` : type-casting, i.e. `int result =  (int)(a * b);` where a and b are double-typed variable
```bash
char *newString = (char *) malloc((n + 1) * sizeof(char));
```
When dynamically allocating a string, remember to increase the size by one becuause of the '\0' character at the end.



## Strings

### Syntax and Symbols
1. ``\0'` is equavalent to int 0.  
2. you can add numbers to 'a' and make it another alphabet. i.e. `'a' + 3` is equavalent to 'd'.
3. if arr is defined using char *arr = "hello, world."; then `*(arr + i)` is equavalent to `arr[i]`.
4. In in order to check if a string has reached its end, use `if(*str){ <actions if the string has not reached the null terminator>}`.  
similarly if a string has reached its end, `(!*str)` will return true.
5. If you only need to compare one character in a string at a time
```bash
if {*strOne == *strTwo}{
    //the two characters are the same
}
```

### How to Copy part of a String
1. Using the String Function
Firstly you need to import the string library` #include <string.h>  `
Next dynamically allocate memory for the new, copied version of the string char `*newString = (char *)malloc((length + 1) * sizeof(char));`
Finally use the string function `strcpy(newString, oldString, length);`
```{warning}
The order of newString and oldString are important in the agument in the function.
```
2. Without using the String Function
The first two steps are absolutely necessaary and are the exact same.
We use a for loop to copy each item manually until the length is covered or a '\0' is encountered.
```bash
for (int i = 0; oldStr[i] != '\0' && i < length; i ++){
    newStr[i] = oldStr[i];
}

newString[i] = '\0';
```

### String Function
You need to be familiar with these string functions.
```bash
strlen, strcpy, strncpy, strcat, strncat, strcmp, strncmp, strchr, strstr
```

### How to distinguish between alphabets and special characters
Alphabets, i.e. a, b, c, ... , z, all in lower case can be converted to indexes.  
For example, when you have a string i.e. char *str, you can index its letters using `*(str + i) - 'a'`.  
To check if the i th character in str is in the 26 alphabets you can do `(*(str + i) - 'a') >= 0 && (*(str + i) - 'a') < 26`.

### How to loop through a string

```bash
for (int i = 0; i < strlen(str); i++)
```

#### How to detect if the character of the string is a special character
```bash
if ((str[i]) > 'z' || (str[i]) < 'a')
```

#### How to "delete" items from a string
```bash
for (int i = index; i < strlen(str)-1 ; i++){
    str[i] = str[i + 1];
}

str[strlen(str) - 1] = '\0';

index --;
```

### How to find the number of occurances of a string in another string
```bash
int numOcr(const char* str, const char* search){
    int ocr = 0;

    while(strstr(str, search) != NULL){
        ocr ++;
        str = strstr(str, search) + strlen(search);
    }

    return ocr;
}
```/

## Recursion
Recursion is signitured by appearing as a function that calls itself.  
When a recursive function calls itself, the only thing that will change is the agument of the function.  
```bash
void printRow(int n) {
  if (n == 1) {
    printf("*\n");
  } else {
    printRow(n - 1);
    printf("*");
  }
```
If there is code below the recursive call, the function will not execute the code until it reaches the end.
Imagine recursive call as a break, it stops doing whatever it is about to do and start the function all over again with a different agument.

### How to use recursion to find the number of times a string occurred inside another string
Use the strstr() string function, which takes in two aguments the main_string and the string that is to be searched.
```bash
int numOcr(const char* str, const char* search){
    if (strstr(str, search) != NULL){
        str = strstr(str, search) + strlen(search);
        return 1 + numOcur(str, search);
    }else{
        return 0;
    }
}
```
the strstr() function returns a pointer, pointing to the first occurance of the second string in the first.



## Linked List

Review with [notes](../docs/linkedlist.md)

### How to initialize a data structure
```bash

struct Neuron {
    int neuron_number;
    char *neuron_name;
} big_neuron = {1, "bigboi"};
```


### How to access members in a Node

If what you are accessing is a variable, e.g. Node students, you should use the dot operator to access its values.
```bash
typedef struct student{
    int studentNumber;
    int height;
    Name *name;
}Students;

int student_number = students.studentNumber; 
```
However, when accessing the values from a pointer, you have to use the arrow operator.  
```bash
typedef struct name{
    char *firstName;
    char *lastName;
}Name;

char *last_name = students.name -> firstName;
``` 


## Sorting and Searching

### Binary Search Tree
A binary Search Tree is formed such that each node will lead to two child nodes -- the left child and the right child.
The left child must be smaller than its parent and the right child must be larger than its parent.

```bash
typedef struct node {
int data;
struct node *left;
struct node *right;
} Node;
```
Above shows a typical node in a binary search tree.  

In order to check that the tree is neither empty or have only one node, use the code:
```bash
Node *current = tree -> head;
if ((current == NULL) || ((current -> left == NULL) && (current -> right == NULL))){
    return NULL;
}
```
#### How to access the smallest element in the tree

We know that the left child of each node must be smaller than itself, so the leftmost child must hold the smallest value.
```bash
while (current -> left != NULL){
    current = current -> left;
}
```
In order to find the smallest element in the binary search tree, firstly we must find the smallest element in the tree, if the tree does not have a right branch, then the second smallest would be its parent node.  
If thetree does have a right branch, the second smallest would be the leftmost branch of that node.

```bash
if (current -> right == NULL){
    second_smallest = parent;
    return second_smallest;
}else{
    current = current -> right;
    while(current -> left != NULL){
        current = current -> left;
    }
    second_smallest = current;
    return second_smallest;
}
```
+


