#include <stdio.h>

void printArray(int list[], int listLength) {
    for (int i = 0; i < listLength; i++) {
        printf("%d ", list[i]);
    }
    printf("\n");
}

void insertionSort(int list[], int length) {
    int i, j, temp;
    for (i = 1; i < length; i++) {
        temp = list[i];
        j = i - 1;
        while (j >= 0 && list[j] > temp) {
            list[j + 1] = list[j];
            j--;
        }
        list[j + 1] = temp;
    }
}


int main(void){

    int length = 6;
    int list[] = {9, 2, 6, 5, 1, 7};
    insertionSort(list, length);
    printArray(list, length);
    
}