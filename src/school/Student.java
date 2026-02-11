package school;


import CinExceptions.NameExceptions;
import CinExceptions.LastNameExceptions;
import CinExceptions.SexExceptions;
import CinExceptions.AgeExceptions;

public class Student implements Comparable<Student>{

    private String name;
    private String lastName;
    private Subject subject;

    //Конструкторы

    public Student() throws NameExceptions, LastNameExceptions{
        this.name = "";
        this.lastName = "";
    }

    public Student(String name, String lastName, Subject subject) throws NameExceptions, LastNameExceptions{
        setName(name);
        setLastName(lastName);
        setSubject(subject);
    }

    //Геттеры

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public Subject getSubject() {
        return subject;
    }

    //Сеттеры

    public void setName(String name) throws NameExceptions{

        if (name == null || name.trim().isEmpty()) {
            throw new NameExceptions("Имя не может быть пустым");
        }

        if (Character.isLowerCase(name.charAt(0))){
            throw new NameExceptions("Имя должно быть с заглавной буквы");
        }

        this.name = name;

    }

    public void setLastName(String lastName) throws LastNameExceptions{

        if (lastName == null || lastName.trim().isEmpty()) {
            throw new LastNameExceptions("Фамилия не может быть пустой");
        }

        if (Character.isLowerCase(lastName.charAt(0))){
            throw new LastNameExceptions("Фамилия должна быть с заглавной буквы");
        }

        this.lastName = lastName;
    }


    public void setSubject(Subject subject) {
        this.subject = subject;
    }


    public void Info(int x, int className) {
        System.out.println("Имя Фамилия: " + this.getName() + " " + this.getLastName());
        System.out.printf("%s: %d\n", subject.getNameSubject(), subject.getGrade());
        if (x != 0){
            System.out.printf("Ученик учится в %d\n", className);
        }
    }

    public int compareTo(Student other){
        if (this.getLastName().compareTo(other.getLastName()) == 0){
            return this.getName().compareTo(other.getName());
        }
        return this.getLastName().compareTo(other.getLastName());
    }

}
