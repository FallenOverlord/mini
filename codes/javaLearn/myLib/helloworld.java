package myLib;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class helloworld {

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        ArrayList<Book> list = new ArrayList<>();

        tInterface(list, scanner);
        scanner.close();
    }

    public static void tInterface(ArrayList<Book> list, Scanner scanner) {
        printNotice();
        while (true) {
            System.out.println("");
            System.out.print(">");
            String input = scanner.nextLine();

            if (input.equals("quit") || input.equals("exit")) {
                break;
            } else if (input.equals("add")){
                add(list, scanner);

            } else if (input.equals("show")){
                showAll(list);
            } else if (input.equals("search")){
                search(list, scanner);
            } else if (input.equals("delete")){
                delete(list, scanner);
            } else if (input.equals("modify")){
                modify(list, scanner);
            } else {
                printNotice();
            }

        }
    }

    public static void add(ArrayList<Book> list, Scanner scanner){
        System.out.print(">New Book ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        if ( checkDuplicate(list, id) ){
            System.out.println("ID duplicate, quitting!");
            return;
        }
        System.out.print(">New Book Name: ");
        String name = scanner.nextLine();
        System.out.print(">New Book Author: ");
        String author = scanner.nextLine();
        System.out.print(">New Book Price: ");
        double price = scanner.nextDouble();
        Book newBook = new Book(id, name, author, price);
        list.add(newBook);
    }

    public static boolean checkDuplicate(ArrayList<Book> list, int key){
        Iterator<Book> iterator = list.iterator();
        while (iterator.hasNext()) {
            Book s = iterator.next();
            if (s.getId() == key){
                return true;
            }
        }

        return false;
    }

    public static void showAll(ArrayList<Book> list){
        Iterator<Book> iterator = list.iterator();
        while (iterator.hasNext()) {
            Book s = iterator.next();
            System.out.println("Name: " + s.getName()
            + " ID: " + s.getId() + " Author:" + s.getAuthor()
            + " Price: " + s.getPrice());
        }
    }

    public static void search(ArrayList<Book> list, Scanner scanner){
        boolean avail = false;
        Iterator<Book> iterator = list.iterator();
        System.out.print(">Book Name: ");
        String input = scanner.nextLine();
        while (iterator.hasNext()) {
            Book s = iterator.next();
                if (s.getName().equals(input)){
                    System.out.println("Name: " + s.getName()
                    + " ID: " + s.getId() + " Author:" + s.getAuthor()
                    + " Price: " + s.getPrice());
                    avail = true;
                }
        }
        if(avail == false) System.out.println("No such entry with name " + input);
    }

    public static void delete(ArrayList<Book> list, Scanner scanner){
        Iterator<Book> iterator = list.iterator();
        System.out.print(">ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        if (checkDuplicate(list, id)){
            while (iterator.hasNext()) {
                Book s = iterator.next();
                    if (s.getId() == id){
                        iterator.remove();
                        System.out.println(">Sucess");
                    }
            }
        } else {
            System.out.println(">ID# Does Not Exist, Quitting!");
        }
    }

    public static void modify(ArrayList<Book> list, Scanner scanner){
        Iterator<Book> iterator = list.iterator();
        //get ID
        System.out.print(">ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        //check ID validity
        if (checkDuplicate(list, id)){
            while (iterator.hasNext()) {
                Book s = iterator.next();
                    if (s.getId() == id){
                        System.out.print(">New Book Name: ");
                        String name = scanner.nextLine();
                        s.setName(name);
                        System.out.print(">New Book Author: ");
                        String author = scanner.nextLine();
                        s.setAuthor(author);
                        System.out.print(">New Book Price: ");
                        double price = scanner.nextDouble();
                        s.setPrice(price);
                        System.out.println(">Sucess");
                    }
            }
        }else{
            System.out.println(">ID# Does Not Exist, Quitting!");
        }
    }

    public static void printNotice(){
        System.out.println("pls type in 'quit' or 'exit' to quit the program," + 
        "'add' to add to lib, 'show' to show all book info, 'search' to check book info after providing a bookname, 'delete' to delete an entry accorind to its id, 'modify' to modify an entry after providing a valid id :)");
    }
}
