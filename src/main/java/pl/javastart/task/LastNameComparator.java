package pl.javastart.task;

import java.util.Comparator;

public class LastNameComparator implements Comparator<Person> {
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

        if (p1.getLastName() == null && p2.getLastName() == null) {
            return 0;
        }
        if (p1.getLastName() == null) {
            return -1;
        }
        return p1.getLastName().compareTo(p2.getLastName());
    }
}
