package core;

import java.util.*;

public class StatisticalAnalysis {

    private Set<String> unique;
    private List<String> elems;

    public StatisticalAnalysis(Iterator<String> iter) {

        elems = new ArrayList<>();
        while(iter.hasNext()) {

            elems.add(iter.next());
        }
        unique = new HashSet<>(elems);
    }

    public Set<String> occurMoreThan(Integer n) {

        Set<String> meetingCriterion = new HashSet<>();
        for (String distinct : unique) {

            if (countElem(distinct) > n) {

                meetingCriterion.add(distinct);
            }
        }
        return meetingCriterion;
    }

    public int countOf(String... tokens) {

        int total = 0;

        for (String tok : tokens) {

            total += countElem(tok);
        }
        return total;
    }

    public int dictionarySize() {

        return unique.size();
    }

    public int size() {

        return elems.size();
    }

    private int countElem(String elem) {

        int count = 0;
        for (String current : elems) {

            if (current.equals(elem)) {

                count++;
            }
        }
        return count;
    }
}
