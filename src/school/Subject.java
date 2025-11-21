package school;

public class Subject {

    private String nameSubject;
    private int grade;

    public Subject(){
        this.nameSubject = "";
        this.grade = 0;
    }

    public Subject(String nameSubject, int grade){
        this.nameSubject = nameSubject;
        this.grade = grade;
    }

    public Subject(Subject other){
        this.nameSubject = other.nameSubject;
        this.grade = other.grade;
    }

    public String getNameSubject() {
        return nameSubject;
    }

    public int getGrade() {
        return grade;
    }

    public void setNameSubject(String nameSubject) {
        this.nameSubject = nameSubject;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }
}