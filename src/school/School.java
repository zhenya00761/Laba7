package school;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class School {

    private List<SchoolClass> classes;

    public School(List<SchoolClass> classes){
        this.classes = classes;
    }

    public List<SchoolClass> getClasses() {
        return classes;
    }

    public void setClasses(List<SchoolClass> classes) {
        this.classes = classes;
    }

    public void searchStudent(String name, String lastName){
        boolean flag = false;
        for (SchoolClass schoolClass: classes){
            for (Student student: schoolClass.getStudents()){
                if (student.getLastName().equals(lastName) && student.getName().equals(name)){
                    System.out.printf("Данный ученик из класса %d\n", schoolClass.getNumber());
                }
            }
        }
    }

    public String bestSubject(){
        HashMap<String, Double> mass = new HashMap<>();
        for (SchoolClass klacc: classes){
            for (Student student: klacc.getStudents()){
                String nameSub = student.getSubject().getNameSubject();
                int gradeSub = student.getSubject().getGrade();
                mass.merge(nameSub, (double) gradeSub, Double::sum); // Double::sum == (oldValue, newValue) -> Double.sum(oldValue, newValue)
            }
        }

        double maxValue = Double.MIN_VALUE;
        String maxKey = "";

        for (Map.Entry<String, Double> entry: mass.entrySet()){
            if (entry.getValue() > maxValue){
                maxValue = entry.getValue();
                maxKey = entry.getKey();
            }
        }

        return maxKey;
    }
}
