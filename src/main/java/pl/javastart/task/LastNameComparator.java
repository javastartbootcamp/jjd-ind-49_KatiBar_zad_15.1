package pl.javastart.task;

import java.util.Comparator;

public class LastNameComparator implements Comparator<Person> {
    @Override
    public int compare(Person p1, Person p2) {
        int result = -1;
        if (p1 == null && p2 == null)
            result = 0;
        if (p1 != null && p2 == null)
            result = -1;
        if (p1 == null && p2 != null)
            result = 1;

        if(p1.getLastName() == null && p2.getLastName() == null)
            result = 0;
        if(p1.getLastName() == null)
            result = -1;
        if (p1 != null && p2 != null && p2.getLastName() != null && p1.getLastName() != null) {
            result = p1.getLastName().compareTo(p2.getLastName());
        }
        return result;
    }
}
