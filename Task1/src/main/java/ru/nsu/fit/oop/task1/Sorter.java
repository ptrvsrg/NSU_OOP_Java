package ru.nsu.fit.oop.task1;

import java.util.Comparator;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

public class Sorter
{
    public static SortedSet <Map.Entry <String, Long>> mapSortingByValue(Map <String, Long> map)
    {
        Comparator <Map.Entry <String, Long>> comparator = (o1, o2) ->
        {
            if (o1.getValue().compareTo(o2.getValue()) == 0)
                return o1.getKey().compareTo(o2.getKey());

            return o2.getValue().compareTo(o1.getValue());
        };

        SortedSet <Map.Entry <String, Long>> sorted = new TreeSet <>(comparator);
        sorted.addAll(map.entrySet());
        return sorted;
    }
}
