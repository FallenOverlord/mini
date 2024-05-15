#include <stdio.h>

void printArray(int list[], int listLength) {
    for (int i = 0; i < listLength; i++) {
        printf("%d ", list[i]);
    }
    printf("\n");
}

void selectionSort(int list[], int length) {
    int temp = 0;

    for (int i = 0; i < length; i++) {
        for (int j = i+1; j < length; j++){
            if (list[j] < list[i]){
                temp = list[i];
                list[i] = list[j];
                list[j] = temp;
            }
        }

    }
    
}


int main(void){

    int length = 6;
    int list[] = {9, 5, 18, 8, 5, 2};
    selectionSort(list, length);
    printArray(list, length);
    
}