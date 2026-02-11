package school;

import CinExceptions.LetterSchoolExceptions;
import CinExceptions.NumSchoolExceptions;
import java.util.List;

public class SchoolClass implements Comparable<SchoolClass>{

    private int number;
    private List<Student> students;

    // Конструкторы с throws
    public SchoolClass(int number, List<Student> students) throws NumSchoolExceptions {
        setNumber(number);
        setStudents(students);
    }

    public SchoolClass(SchoolClass other) throws NumSchoolExceptions {
        setNumber(other.getNumber());
        setStudents(other.getStudents());
    }

    // Геттеры
    public int getNumber() {
        return number;
    }

    public List<Student> getStudents() {
        return students;
    }

    // Сеттеры
    public void setNumber(int number) throws NumSchoolExceptions {
        try {
            if (number > 0 && number < 12) {
                this.number = number;
            } else {
                throw new NumSchoolExceptions("Классы только от 1 до 11");
            }
        } catch (NumSchoolExceptions e) {
            System.out.println("Не подходит: " + e.getMessage());
        }
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public void info(){
        System.out.printf("Информация про класс %d\n", getNumber());
        System.out.printf("Количество учеников: %d\n", getStudents().size());
        for (Student student: students){
            student.Info(0, this.getNumber());
        }
    }

    public double AverageGrade(){
        double x = 0, cnt = 0;
        for (Student student: students){
            x += student.getSubject().getGrade();
            cnt++;
        }
        return x / cnt;
    }

    public int compareTo(SchoolClass other){
        return Double.compare(this.AverageGrade(), other.AverageGrade());
    }
}