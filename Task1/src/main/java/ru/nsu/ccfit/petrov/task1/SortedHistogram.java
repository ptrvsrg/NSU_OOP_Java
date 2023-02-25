package ru.nsu.ccfit.petrov.task1;

import java.util.*;

public class SortedHistogram
        implements Iterable<CountedWord>
{
    private final SortedSet<CountedWord> histogram;

    public SortedHistogram() {
        Comparator<CountedWord> comparator = (o1, o2) -> {
            if (o1.getCount()
                  .equals(o2.getCount()))
                return o1.getWord()
                         .compareTo(o2.getWord());

            return o2.getCount()
                     .compareTo(o1.getCount());
        };

        histogram = new TreeSet<>(comparator);
    }

    void add(String word) {
        for (CountedWord entry : histogram)
            if (entry.getWord()
                     .equals(word)) {
                histogram.remove(entry);
                entry.setCount(entry.getCount() + 1L);
                histogram.add(entry);
                return;
            }

        histogram.add(new CountedWord(word,
                                      1L));
    }

    @Override
    public Iterator<CountedWord> iterator() {
        return histogram.iterator();
    }
}
