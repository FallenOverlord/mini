package teachers;

import java.util.ArrayList;
import java.util.Iterator;

public class helloworld {

    public static void main(String[] args){

    ArrayList<Teacher> list = new ArrayList<>();

    Teacher teacher1 = new Teacher();
    teacher1.setName("Blinda Wang");
    teacher1.setMajor("ECE");
    teacher1.setWage(22315);
    list.add(teacher1);

    Teacher teacher2 = new Teacher();
    teacher2.setName("Pirrer Sullivan");
    teacher2.setMajor("CIV");
    teacher2.setWage(235324);
    list.add(teacher2);

    Teacher teacher3 = new Teacher();
    teacher3.setName("Salma Elma");
    teacher3.setMajor("ECE");
    teacher2.setWage(324255);
    list.add(teacher3);

    Teacher teacher4 = new Teacher("Seica", "CIV" , 23425);
    list.add(teacher4);

    Teacher teacher5 = new Teacher("Scott Ramsay", "ChemEng", 3256343);
    list.add(teacher5);
    
    printTeacherInfo(list);

    checkSeica(list);
    printTeacherInfo(list);
    System.out.println("");

    delElma(list);
    printTeacherInfo(list);
    System.out.println("");

    RamsayWageRaise(list);
    printTeacherInfo(list);


    }

    public static void printTeacherInfo(ArrayList<Teacher> list){
        for (Teacher s : list) {
            System.out.println("the name of teacher is " + s.getName()
            + " their major is " + s.getMajor() + " their wage is " + s.getWage());
        }
    }

    public static void checkSeica(ArrayList<Teacher> list) {
        Iterator<Teacher> iterator = list.iterator();
        while (iterator.hasNext()) {
            Teacher s = iterator.next();
            if (s.getName().equals("Seica")) {
                s.setName("Big Seica");
            }
        }
    }

    public static void delElma(ArrayList<Teacher> list) {
        Iterator<Teacher> iterator = list.iterator();
        while (iterator.hasNext()) {
            Teacher s = iterator.next();
            if (s.getName().equals("Salma Elma")) {
                iterator.remove();
            }
        }
        
    }

    public static void RamsayWageRaise(ArrayList<Teacher> list) {
        Iterator<Teacher> iterator = list.iterator();
        while (iterator.hasNext()) {
            Teacher s = iterator.next();
            if (s.getName().equals("Scott Ramsay")) {
                s.setWage(s.getWage() + 500000);
            }
        }
    }
}

