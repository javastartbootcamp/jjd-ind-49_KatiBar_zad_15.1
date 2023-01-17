package pl.javastart.task;

import java.util.Comparator;

public class FirstNameComparator implements Comparator<Person> {
    @Override
    public int compare(Person p1, Person p2) {
        if (p1 == null && p2 == null) {
            return 0;
        }
        if (p1 != null && p2 == null) {
            return -1;
        }
        if (p1 == null) {
            return 1;
        }

        if (p1.getFirstName() == null && p2.getFirstName() == null) {
            return 0;
        }
        if (p1.getFirstName() == null) {
            return -1;
        }
        return p1.getFirstName().compareTo(p2.getFirstName());
    }
}
