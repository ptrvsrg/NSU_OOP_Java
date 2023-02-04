package ptrvsrg.task1;

import java.util.Comparator;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

public class Sorter
{
    public enum SortingType
    {
        ASCENDING,
        DESCENDING
    }

    public static SortedSet <Map.Entry <String, Long>> mapSortingByValue(Map <String, Long> map,
                                                                         SortingType sortingType)
    {
        Comparator <Map.Entry <String, Long>> comparator = null;

        switch (sortingType)
        {
            case ASCENDING:
                comparator = (o1, o2) ->
                {
                    if (o1.getValue().compareTo(o2.getValue()) == 0)
                        return o1.getKey().compareTo(o2.getKey());

                    return o1.getValue().compareTo(o2.getValue());
                };
                break;
            case DESCENDING:
                comparator = (o1, o2) ->
                {
                    if (o2.getValue().compareTo(o1.getValue()) == 0)
                        return o1.getKey().compareTo(o2.getKey());

                    return o2.getValue().compareTo(o1.getValue());
                };
                break;
        }

        SortedSet <Map.Entry <String, Long>> sorted = new TreeSet <>(comparator);
        sorted.addAll(map.entrySet());
        return sorted;
    }
}
