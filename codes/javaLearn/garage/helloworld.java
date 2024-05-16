package garage;

import java.util.Scanner;

public class helloworld {
    
    public static void main(String[] args) {
    
        Scanner scanner = new Scanner(System.in);

        // Create an array to store Cars objects
        Cars[] myCars = new Cars[3];
        
        // Initialize each Cars object and set the brand
        for (int i = 0; i < myCars.length; i++) {
            myCars[i] = new Cars(); // Initialize the Cars object at index i
            System.out.println("please input brand for car #" + (i + 1));
            myCars[i].setBrand(scanner.nextLine());
        }

        // Set the color for each car
        for (int i = 0; i < myCars.length; i++) {
            System.out.println("please input color for car #" + (i + 1));
            myCars[i].setColor(scanner.nextLine());
        }

        // Set the price for each car
        for (int i = 0; i < myCars.length; i++) {
            System.out.println("please input price for car #" + (i + 1));
            myCars[i].setPrice(scanner.nextDouble());
            scanner.nextLine(); // Consume the remaining newline
        }

        scanner.close();

        printCarDetails(myCars);
    }

    public static void printCarDetails(Cars[] myCars) {
        System.out.println("Car Details:");
        for (Cars car : myCars) {
            System.out.println("Brand: " + car.getBrand() + ", Color: " + car.getColor() + ", Price: " + car.getPrice());
        }
    }
}




