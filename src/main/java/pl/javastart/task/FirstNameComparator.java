package pl.javastart.task;

import java.util.Comparator;

public class FirstNameComparator implements Comparator<Person> {
    @Override
    public int compare(Person p1, Person p2) {
        int result = -1;
        if (p1 == null && p2 == null)
            result = 0;
        if (p1 != null && p2 == null)
            result = -1;
        if (p1 == null && p2 != null)
            result = 1;

        if(p1.getFirstName() == null && p2.getFirstName() == null)
            result = 0;
        if(p1.getFirstName() == null)
            result = -1;
        if (p1 != null && p2 != null && p2.getFirstName() != null && p1.getFirstName() != null) {
            result = p1.getFirstName().compareTo(p2.getFirstName());
        }
        return result;
    }
}
