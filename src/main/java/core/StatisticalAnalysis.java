package core;

import java.util.*;

public class StatisticalAnalysis {

    private HashMap<String, Integer> map;

    public StatisticalAnalysis(Iterator<String> iter) {

        map = new HashMap<>();
        String currentKey;
        Integer currentValue;
        while(iter.hasNext()) {

            currentKey = iter.next();
            currentValue = map.get(currentKey);
            map.put(currentKey, (currentValue == null ? 1 : ++currentValue));
        }
    }

    public Set<String> occurMoreThan(Integer n) {

        Set<String> meetingCriterion = new HashSet<>();
        for (String distinct : map.keySet()) {

            if (map.get(distinct) > n) { // note: we should *never* get a null here

                meetingCriterion.add(distinct);
            }
        }
        return meetingCriterion;
    }

    public int countOf(String... tokens) {

        int total = 0;
        Integer currentValue;
        for (String tok : tokens) {

            currentValue = map.get(tok);
            total += (currentValue == null ? 0 : currentValue);
        }
        return total;
    }

    public int dictionarySize() {

        return map.size();
    }

    public int size() {

        int total = 0;
        for (Integer amount : map.values()) {

            total += amount;
        }
        return total;
    }
}
