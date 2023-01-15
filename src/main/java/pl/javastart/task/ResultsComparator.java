package pl.javastart.task;

import java.util.Comparator;

public class ResultsComparator implements Comparator<Person> {
    @Override
    public int compare(Person p1, Person p2) {
        if(p1 == null && p2 == null)
            return 0;
        if(p1 != null && p2 == null)
            return -1;
        if(p1 == null && p2 != null)
            return 1;

        if (p1.getResult() < p2.getResult()) {
            return -1;
        } else if (p1.getResult() > p2.getResult()) {
            return 1;
        } else {
            return 0;
        }
    }
}
