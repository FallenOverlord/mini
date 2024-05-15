# Linked Lists

alternative to arrays

arrays are not flexible
::::{grid}
:gutter: 2

:::{grid-item-card} {bdg-primary}`Append`
    appending elements may lead to the change of the size of the array
:::

:::{grid-item-card} {bdg-secondary}`Modification`
    not possible to modify elements in the middle of the array,  
    or delete them
:::


::::

## how to make a linked list?

### prerequsit knowledge
a linked list is consists of multiple nodes.  
each node is like an element in the array.

**Anatomy of a Node**
::::{grid}
:gutter: 2

:::{grid-item-card} {bdg-primary}`Value`
    the information that the element holds
    e.g. `int data;`
:::

:::{grid-item-card} {bdg-secondary}`Link`
    a pointer to a node
    e.g. `struct node *next;`
:::


::::


tip: to give the node data type ( struct node ) a nickname as "Node":  
`typedef <original_name> <nickname>;  `
`typedef sturct node {...} Node;`


### Step by Step guide on Creating a linked list!
````{tab-set}
```{tab-item} create data structure
create a data structure and give it the nickname of "Node" data type.
`typedef struct node {
    int value;
    struct node *next;
} Node;`
```

```{tab-item} Declearing Variables

`int main(void) {
    Node firstElement;
    firstElement.value = 1;
    firstElement.next = NULL;

    Node secondElement;
    secondElement.value = 2;
    secondElement.next = NULL;
}`


```

```{tab-item} Create link
    link the pointer equipped in the first node to point to the next item
    `firstElement.next = &secondElement;`

```

```{tab-item} Test it Out
    printf("the vlaue hold in the second item is %d", (* firstElement.next).value);

```
````

### Define a linked list dynamically
1. create a data structure and give it the nickname of "Node" data type.
```
typedef struct node {
    int data;
    struct node *next;
} Node;
```
2. First & Last
the first node will always be pointed by a pointer named "head"

```
Node *head;
Node *newNode = (Node *)malloc(sizeof(Node));
```

newNode is the first Node in the linked list!

```
newNode->data = 1;
newNode->next = NULL;
```

initiating the values within the first node!
  
  Here is where the magic starts!  
  Make the already-defined first node the "head" and define the second node based on the "position" the first node holds.

```
head = newNode;

newNode = (Node *)malloc(sizeof(Node));
newNode->data = 2;
newNode->next = NULL;
```

after that, if we want to define more nodes we can just follow the same procedure.

### Use a function to create a linked list

#### What happens when there is no memery to allocate

in the previous part, where we attempted to use melloc function to dynamically allocate memory to a pointer and assign the pointer to a pointer variable, next.  The operation could be risky if there is no more memory to allocate to the pointer, and if we try to allocation membmer that is not yet freed to a pointer, a segmentation error will occur.

  To avioding allocating inaccessable memory, we can use the if( next != NULL ) to detect if the pointer is valid.

#### Create the function that have the type Node pointer

the aim of this function is to create a new node on our little linked list train, and the way we are doing that is to dynamically create a new pointer with the data type node.  
we then check if the pointer is successfully created, and if yes, we will procced to assign the value and the link to the pointer.  
we will finally return the pointer.

Node *createNode(int number){

    Node *node = (Node *) malloc(sizeof(Node));

    if (node != NULL){
        node->data = number;
        node->next = NULL;
    }

    return node;
    }

#### Use the function in the main function

the reason why we return the pointer is because we can assign to pointer to a new node.  
we have to create the head of this train first, after that each time we create a new node pointer and assign that pointer to the link within the existing node.

    Node *head = NULL;

    head = createNode(2);

    head->next = createNode(5);

    head->next->next = createNode(7);

#### the working code

```
#include <stdio.h>
#include <stdlib.h>

typedef struct Node {
    int data;
    struct Node *next;

} Node;

Node *createNode(int number){

    Node *node = (Node *) malloc(sizeof(Node));

    if (node != NULL){
        node->data = number;
        node->next = NULL;
    }

    return node;
}

int main(void){

    Node *head = NULL;

    head = createNode(2);

    head->next = createNode(5);

    head->next->next = createNode(7);

    printf("the value of the last node is %d.\n", head->next->next->data);
    

    return 0;
}

```

### Insert a new head to an existing linked list.

ok, it is a bit hard and there are several ways, so let me introduce the easiest way first.  
we first need to create a node that is going to subsitute the head of the linked list, newhead, by using the existing createNode function. and check if the pointer points to a valid memory space  

warning: we cannot call the newhead function by value, we must pass in a pointer that points to the head(which is a pointer)  
if we have a variable that we want to modify its value through a function, we pass in the pointer of the the variable.  
```

int newhead(Node **head, int value){

    int status = 0;

    Node *newhead = createNode(value);

    if (newhead != NULL){
        status = 1;
        newhead->next = *head;
        *head = newhead;
    }else{
        printf("memory allocation not successful.\n");
        status = 0;
    }

    return status;
}

```
which means that is logical that if we want to modify a pointer, we have to have another pointer that points to that pointer.


`newhead(&head, 0);`
we need to pass in the address of the old head pointer in order to change its address to the second node.


### Create a New Data Sturcture

make a new data sturcture that holds the heads of linked lists

```
typedef struct list {
    Node *head;
} LinkedList;

```
the new data type struct list has been assigned a nickname LinkedList  
any variable that have the type LinkedList can access a pointer value by using `<var_name>.head`.

```
int main(void) {

    LinkedList list;

    list.head = createNode(1);

    (list.head)->next = createNode(2);

    return 0;
}
```
next we call a function, insertAtFront, and pass in the pointer to the head of the linked list.  
insertAtFront will create a new node, serving as the new head.
  then it will valideate the new head.
  after that it links the old head after the new node by using `temp->next = list->head`.
  Finally it changes the old head(a pointer in the list) to point to the new node using `list->head = temp`.

```
bool insertAtFront (LinkedList *list, int value){
    Node *temp = createNode(value);
    if (tem == NULL){ return False;}
    temp->next = list->head;
    list->head = temp;
    retrun True;
}
```
also add in main function `insertAtFront(&list, 0);`.

## how to print a linked list

remember the pointer next defined when we created the list?  
we can have a variable called current (just like int i in a for loop that's used to print the array[i]) make it switch to next using `current = current->next' to loop through all the nodes in a linked list.  
the loop will stop because the last node's next pointer is pointing to 'NULL' and has not being modified.

```
void printLinkedList(LinkedList *list){

    Node *current = list->head;

    while(current != NULL){
        printf("this node has a value of %d.\n", current->data);
        current = current.next;
    }

}
```

## how to insert at the end

we can employ the same logic as above to find out where is the end of the linked list.  
we will know because the last node's next pointer is NULL.  
```
void appendLinkedList(linkedList *list, int value){

    Node *endnode = createNode(value);

    if (endnode != NULL){
        Node *current = list->head;
        if (current == NULL){
            //the linnked list is empty
            list->head = endnode;

        }else{

            while(current != NULL){
                current = current.next;
            }

            current->next = endnode;

        }

    }

}
```

## Insert in the middle

if we are given a list that has a current ascending value in the "data" and we need to insert an item
in such a position that its value fits in the right place
   
  we need to traverse the linked list in the same way until current->data is smaller than newnode->data.
  we will first link newnode->next to current->next, so now current->next is linked to two elements.
  we will sever the first connection and relink it to newnode.

```
bool insertInMid(LinkedList *list, int value){
    
    //create the newnode and validate it
    Node *newnode = createNode(value);
    if (newnode == NULL){
        return False;
    }

    //loop through the list and compare values
    Node *current = list->head;
    if (current == NULL){
        //the list is empty
        current->next = newnode;
        return True;
    }

    if (current->data > value) {
    // The value to insert comes before the current head, so insert before it.
    return insertAtFront(list, value);
    }

    while(current->data > value){
        if (current->next == NULL){
            //if the node is the end of the list
            current->next = newnode;
            return True;
        }
        current = current->next
    }

    //now current->data < value
    newnode->next = current->next;
    current->next = newnode;
    return True;
}
```

## Find Node

Sometimes we need to search for a node with a particular value.  
We want to implment a function that takes in the list of heads and the target value.
the function will return a pointer that points to the target found or NULL if thereis none.

```
Node *searchNode(LinkedList *list, int target_value){
    Node *current = list->head;

    while (current != NULL){
        if (current->data == target_value){
            printf("found match.\n");
            return current;
        }else{
            current = current->next;
        }
    }

    return NULL;
}
```
there is no need to worry about empty lists because te while loop already took care of it

## Delete Node

to complete the lifecycle of a node, we must delete them to free up the memory.

### Empty linked list
For example, we may be in a situation where there is nothing to delete.  
we have to test if the linked list even exist by doing `list->head == NULL` validation

### Delete the head of the list
Or we may find ourselves in a situation where there is only one element in the list.
we need to set the new head pointer to the node->next of the head, which in this case is NULL.  
`*newhead = list->head->next;` after that we can free up the memory using `free(list->head);`
finally we need to point the list->head to the newhead using `list->head = newhead;`.

```
void deleteHead(LinkedList *list){
    if (list->head != NULL){
        Node *newhead = list->head->next;
        free(list->head);
        list->head = newhead;
    }
}
```
### Delete the end of a list

It has to ensure that the node after the next is NULL so it can delete the current->next and set the new current->next position to NULL.

```
void deleteButt(LinkedList *list){

    if (list->head == NULL) {
        // The list is empty, there is nothing to delete.
        return;
    }

    if (list->head->next == NULL) {
        // There is only one node in this list.
        deleteFront(list);
        return;
    }

    Node *current = list->head;
    while (current->next->next != NULL){
        current = current->next;
    }


    free(current->next);
    current->next = NULL;

}
```
There is two special case to consider:
::::{grid}
:gutter: 2

:::{grid-item-card} {bdg-primary}`empty list`
    list->head == NULL

:::

:::{grid-item-card} {bdg-secondary}`only one element`
    list->head->next == NULL

    which means that the head is the only element  
    proceed to delete the head
:::

::::

### annhialation
```
void deleteAllNodes(LinkedList *list) {

  while (list->head != NULL) {
    deleteFront(list);
  }

  list->head = NULL;
}
```
brute force

### Detete First Match

1. Find the target node.
2. Fix the link before and after the node.
3. Delete the target node.
4. Fix head/butt issues

```
bool deleteFirstMatch(LinkedList *list, int value) {
    //head/butt issues
    if (list->head == NULL) {
        return false;
    }
    if (list->head->data == value) {
        deleteFront(list);
        return true;
    }

    // Search for a node that matches the value, but maintain a pointer to the node just before it.
    Node *current = list->head;
    while (current->next != NULL && current->next->data != value) {
        current = current->next;
    }

    // current now points to a node just before the node that matched, OR current points to the last node.
    if (current->next != NULL) {
        // current does not point to the last node.
        Node *temp = current->next;  // temp is the node we must delete.
        current->next = temp->next;  // Update n so that temp is no longer linked.
        free(temp);

        return true;
    }

    return false;
}
```

### Delete all matches

composite weapon
```
void deleteAllNodes(LinkedList *list, value) {

  while (deleteFirstMatch(list, value)) {
  }

}
```