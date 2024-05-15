# Basics of Data Structres

## What is this chapter about?
Storing different data types, i.e. int and char, in one variable.

## How to declear data structure?
```bash
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

```

You can also declear a data stucture using:

```bash
struct Prof{
int staffID;
double rating;
char name[20];
}prof;

```

the variable for the data sturcre is defined as lower case prof.

## Access stuff in the data sturcture.

using the example above, if we want to know the name of the proffessor, we can write:

`prof.name[20] = "Seica"`

printf("the name of the prof is: %s\n", prof.name);

## Associate values with the variables.

```bash
struct Chestnut{

int floorNumber;
double rating;
char favorateFoodName[20];

} nut = {27, 1.2, "ribs"};
```


Or we can initialize value inside other functions:
struct Chestnut dong = {4, 1.5, "meat balls"};

## Give your data sturcture a nickname;

warning: only for an existing data sturcture.

use the magic words:
`typedef <name_of_the_data_type> <nickname>;`

<name_of_the_data_type> can be int, char and double,  
it can also be the data structure you've defined.

now you can call data types by their nicknames.

e.g.  
`typedef char words;`  

`words my_initial = "M";
words my_name = "Miles";`

## Data Stuctures and Pointers

after we define a data stucture:
```bash
#include <stdio.h>
#include <stdlib.h>

typedef struct Neuron {
    int neuronNum;
    double input;

} Neuron;

int main(void){

    Neuron *pNeuron;
    pNeuron  = (Neuron *)malloc(sizeof(Neuron));

    pNeuron->input = 23.96;

    printf("the input into the neuron is %.2lf.\n", pNeuron->input);
    
    free(pNeuron);

    return 0;
}
```

```bash
#include <stdio.h>
#include <stdlib.h>

typedef struct Neuron {
    int neuronNum;
    double input;

} Neuron;

int main(void){

    Neuron neuron = {12, 34.52};
    Neuron *pNeeuron = &neuron;

    (*pNeeuron).input<span style="color: red;"> </span> = 22.32;

    printf("The new input into the neuron is now %.2lf.\n", (*pNeeuron).input);

    return 0;
}
```

::::{grid}
:gutter: 2

:::{grid-item-card} {bdg-primary}`Arrow Operator`
    `pNeuron->input = 23.96;`
:::

:::{grid-item-card} {bdg-secondary}`Dereference Operator`
    `(*pNeeuron).input = 22.32;`
:::


::::

<span style="color: red;"></span>
