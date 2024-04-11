# Basics of Data Structres

** What is this chapter about?
Storing different data types, i.e. int and char, in one variable.

** How to declear data structure?
struct Student{
int stuID;
double height;
char name[20];
bool sex;
}

int main(void){
struct Student student;

return 0;
}

You can also declear a data stucture using:

struct Prof{
int staffID;
double rating;
char name[20];
}prof;

the variable for the data sturcre is defined as lower case prof.

** Access stuff in the data sturcture.

using the example above, if we want to know the name of the proffessor, we can write:

prof.name[20] = "Seica"

printf("the name of the prof is: %s\n", prof.name);

** Associate values with the variables.

struct Chestnut{

int floorNumber;
double rating;
char favorateFoodName[20];

} nut = {27, 1.2, "ribs"};

Or we can initialize value inside other functions:
struct Chestnut dong = {4, 1.5, "meat balls"};

** Give your data sturcture a nickname;

warning: only for an existing data sturcture.

use the magic words:
typedef <name_of_the_data_type> <nickname>;

<name_of_the_data_type> can be int, char and double,  
it can also be the data structure you've defined.

now you can call data types by their nicknames.

e.g. typedef char words;

words my_initial = "M";
words my_name = "Miles";





