package school;

import java.util.Comparator;

public class SchoolClassComparator implements Comparator<SchoolClass> {

    @Override
    public int compare(SchoolClass c1, SchoolClass c2) {
        if (c1.getNumber() > c2.getNumber())
            return 1;
        else if(c1.getNumber() < c2.getNumber())
            return -1;
        else
            return 0;
    }
}
