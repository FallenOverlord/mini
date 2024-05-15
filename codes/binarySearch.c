#include <stdio.h>

int binarySearch(int list[], int listLength, int item) {
    
    int lowerBound = 0, higherBound = listLength - 1;
    int mid = (higherBound + lowerBound)/2;
    while(mid != higherBound && mid != lowerBound){
        if (item < list[mid]){
            higherBound = mid;
            mid = (higherBound + lowerBound)/2;
            printf("comparing %d and %d.\n", list[mid], item);
        }else if (item > list[mid]){
            lowerBound = mid;
            mid = (higherBound + lowerBound)/2;
            printf("comparing %d and %d.\n", list[mid], item);
        }else{
            return mid;
        }
    }
    return -1;
    
}

int main(void) {
    int list[] = {1, 3, 5, 7, 9, 10, 12};
    printf("Found 7 at index %d.\n", binarySearch(list, 7, 9));
    return 0;
}