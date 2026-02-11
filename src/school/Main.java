package school;

import CinExceptions.NumSchoolExceptions;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        try {
            School school = StudentReader.readStudents("students.txt");

            //1 ZADANIE
            System.out.println("1 ZADANIE");
            for (SchoolClass klass: school.getClasses()){
                klass.info();
                System.out.println("=====================");
            }

            //2 ZADANIE
            System.out.println("2 ZADAMIE");
            Collections.sort(school.getClasses());

            for (SchoolClass klass: school.getClasses()){
                System.out.print(klass.getNumber() + " ");
                System.out.println();
            }
            System.out.println("=====================");
            //3 ZADANIE
            System.out.println("3 ZADANIE");
            System.out.println("Введите название предмета: ");
            List<Student> mass = new ArrayList<>();
            Scanner in = new Scanner(System.in);
            String subForSearch;
            subForSearch = in.nextLine();
            for (SchoolClass klass: school.getClasses()){
                for (Student student: klass.getStudents()){
                    if (student.getSubject().getNameSubject().equals(subForSearch)){
                        mass.add(student);
                    }
                }
            }

            Collections.sort(mass);

            for (Student student: mass){
                student.Info(0, 0);
            }

            System.out.println("=====================");

            //4 ZADANIE

            Collections.sort(school.getClasses(),new SchoolClassComparator());

            BufferedWriter out = new BufferedWriter(new FileWriter("vedomost.txt"));
            out.write("Ведомости всех классов");
            out.newLine();
            out.write("=========================");
            out.newLine();

            for (SchoolClass klass: school.getClasses()){
                HashMap<String, List<Student>> vedomost = new HashMap<>();

                out.write(klass.getNumber() + " класс");
                out.newLine();
                out.write("=========================");
                out.newLine();

                for (Student student: klass.getStudents()){
                    vedomost.computeIfAbsent(student.getSubject().getNameSubject(), k -> new ArrayList<>()).add(student);
                }

                for (Map.Entry<String, List<Student>> entry : vedomost.entrySet()){

                    try {
                        SchoolClass classForVed = new SchoolClass(1, entry.getValue());


                    out.write(entry.getKey());
                    out.newLine();
                    out.write("--------------------");
                    out.newLine();

                    for (Student student: entry.getValue()){
                        out.write(student.getName() + " " + student.getLastName() + " - " + student.getSubject().getGrade());
                        out.newLine();
                    }
                    double average = classForVed.AverageGrade();
                    out.write("Средний балл: " + String.format("%.2f", average));
                    out.newLine();
                    out.write("--------------------");
                    out.newLine();
                    } catch (NumSchoolExceptions e){
                        System.out.println("Не подходит: " + e.getMessage());
                    }
                }

            }

            out.close();


            //5 ZADANIE
            System.out.println("5 ZADAMIE");
            System.out.println("Введите имя: ");
            String name = in.nextLine();
            System.out.println("Введите фамилию: ");
            String lastName = in.nextLine();
            boolean flag = false;

            for (SchoolClass klass: school.getClasses()){
                for (Student student: klass.getStudents()){
                    if (student.getName().equals(name) && student.getLastName().equals(lastName)){
                        System.out.println(klass.getNumber() + " класс");
                        flag = true;
                        break;
                    }
                }
                if (flag){
                    break;
                }
            }

            //6 ZADANIE


            System.out.println("=====================");
            System.out.println("6 ZADANIE");
            HashMap<String, Double> bestGrade = new HashMap<>();
            double maxGrade = Double.MIN_VALUE;
            String maxName = "";

            for (SchoolClass klass: school.getClasses()){
                for (Student student: klass.getStudents()){

                    String nameSubject = student.getSubject().getNameSubject();
                    double grade = (double) student.getSubject().getGrade();
                    bestGrade.merge(nameSubject, grade, Double::sum);

                    if (bestGrade.get(nameSubject) > maxGrade){
                        maxGrade = bestGrade.get(nameSubject);
                        maxName = nameSubject;
                    }
                }
            }

            System.out.println("Лучший предмет по успеваемости: " + maxName);

        } catch (IOException e) {
            System.out.println("Ошибка чтения файла:" + e.getMessage());
        }
    }
}