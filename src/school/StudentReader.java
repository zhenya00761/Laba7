package school;

import CinExceptions.NameExceptions;
import CinExceptions.LastNameExceptions;
import CinExceptions.NumSchoolExceptions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentReader {

    public static School readStudents(String file) throws IOException {

        HashMap<Integer, List<Student>> school = new HashMap<>();

        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;

        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(" ");

            String lastName = parts[0];
            String name = parts[1];
            int num = Integer.parseInt(parts[2]);
            String nameSubject = parts[3];
            int grade = Integer.parseInt(parts[4]);

            try {
                Subject subject = new Subject(nameSubject, grade);

                try {
                    Student student = new Student(name, lastName, subject);

                    if (!school.containsKey(num)) {
                        school.put(num, new ArrayList<>());
                    }
                    school.get(num).add(student);

                } catch (NameExceptions | LastNameExceptions e) {
                    System.out.println("Ошибка при создании студента: " + e.getMessage());
                    System.out.println("Пропускаем запись: " + line);
                }

            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка при создании предмета: " + e.getMessage());
                System.out.println("Пропускаем запись: " + line);
            }
        }
        reader.close();

        List<SchoolClass> classes = new ArrayList<>();
        for (Map.Entry<Integer, List<Student>> entry : school.entrySet()) {
            try {
                SchoolClass schoolClass = new SchoolClass(entry.getKey(), entry.getValue());
                classes.add(schoolClass);
            } catch (NumSchoolExceptions e) {
                System.out.println("Ошибка при создании класса " + entry.getKey() + ": " + e.getMessage());
                System.out.println("Класс не будет добавлен");
            }
        }

        return new School(classes);
    }
}