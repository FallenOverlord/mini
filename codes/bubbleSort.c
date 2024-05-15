#include <stdio.h>

void printArray(int list[], int listLength) {
    for (int i = 0; i < listLength; i++) {
        printf("%d ", list[i]);
    }
    printf("\n");
}

void bubbleSort(int list[], int length) {

    int temp = 0;

    for (int i = 0; i < length; i++) {
        for (int j = 0; j < length - 1; j++) {
            if(list[j] > list[j+1]){
                temp = list[j + 1];
                list[j+1] = list[j];
                list[j] = temp;
            }
        }
    }
    
}


int main(void){

    int length = 6;
    int list[] = {18, 15, 2, 5, 3, 1};
    bubbleSort(list, length);
    printArray(list, length);
    
}