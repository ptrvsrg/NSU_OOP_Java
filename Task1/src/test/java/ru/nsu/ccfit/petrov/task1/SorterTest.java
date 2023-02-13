package ru.nsu.ccfit.petrov.task1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

class SorterTest
{
    @Test
    void mapSortingByValueTest()
    {
        List <String> keys = Arrays.asList("Albert",
                                           "John",
                                           "Ronald",
                                           "Michael");
        List <Long> values = Arrays.asList(62L,
                                           14L,
                                           31L,
                                           31L);
        List <AbstractMap.SimpleEntry <String, Long>> expected = Arrays.asList(new AbstractMap.SimpleEntry <>("Albert",
                                                                                                              62L),
                                                                               new AbstractMap.SimpleEntry <>("Michael",
                                                                                                              31L),
                                                                               new AbstractMap.SimpleEntry <>("Ronald",
                                                                                                              31L),
                                                                               new AbstractMap.SimpleEntry <>("John",
                                                                                                              14L));
        Map <String, Long> actualMap = new HashMap <>();
        for (int i = 0; i < keys.size(); ++i)
            actualMap.put(keys.get(i),
                          values.get(i));

        SortedSet <Map.Entry <String, Long>> actual = Sorter.mapSortingByValue(actualMap);
        Assertions.assertArrayEquals(expected.toArray(),
                                     actual.toArray());
    }
}