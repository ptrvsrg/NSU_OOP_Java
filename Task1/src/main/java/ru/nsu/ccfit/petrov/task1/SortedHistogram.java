package ru.nsu.ccfit.petrov.task1;

import java.util.*;

public class SortedHistogram
        implements Iterable <Map.Entry <String, Long>>
{
    private final SortedSet <Map.Entry <String, Long>> histogram;

    public SortedHistogram()
    {
        Comparator <Map.Entry <String, Long>> comparator = (o1, o2) ->
        {
            if (Objects.equals(o1.getValue(),
                               o2.getValue()))
                return o1.getKey()
                         .compareTo(o2.getKey());

            return o2.getValue()
                     .compareTo(o1.getValue());
        };

        histogram = new TreeSet <>(comparator);
    }

    void add(String word)
    {
        for (Map.Entry <String, Long> entry : histogram)
            if (entry.getKey()
                     .equals(word))
            {
                histogram.remove(entry);
                histogram.add(new AbstractMap.SimpleEntry <>(word,
                                                             entry.getValue() + 1L));
                return;
            }

        histogram.add(new AbstractMap.SimpleEntry <>(word,
                                                     1L));
    }

    @Override
    public Iterator <Map.Entry <String, Long>> iterator()
    {
        return histogram.iterator();
    }
}
