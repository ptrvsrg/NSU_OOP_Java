package ru.nsu.ccfit.petrov.task1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
import java.util.stream.Stream;

class SortedHistogramTest
{
    private static Stream<Arguments> sortedHistogramTestArgs()
    {
        return Stream.of(
                Arguments.of(new ArrayList<>(),
                             new ArrayList<>()),
                Arguments.of(List.of(new AbstractMap.SimpleEntry<>("a", 3L),
                                     new AbstractMap.SimpleEntry<>("c", 1L),
                                     new AbstractMap.SimpleEntry<>("b", 2L)),
                             List.of(new AbstractMap.SimpleEntry<>("a", 3L),
                                     new AbstractMap.SimpleEntry<>("b", 2L),
                                     new AbstractMap.SimpleEntry<>("c", 1L)))
        );
    }

    @ParameterizedTest
    @MethodSource("sortedHistogramTestArgs")
    void sortedHistogramTest(List <Map.Entry<String, Long>> actual,
                             List <Map.Entry<String, Long>> expected)
    {
        SortedHistogram histogram = new SortedHistogram();

        for (Map.Entry <String, Long>  entry : actual)
            for (int i = 0; i < entry.getValue(); ++i)
                histogram.add(entry.getKey());

        Iterator <Map.Entry<String, Long>> actualIter = histogram.iterator();
        Iterator <Map.Entry<String, Long>> expectedIter = expected.iterator();

        while (actualIter.hasNext() && expectedIter.hasNext())
        {
            Map.Entry<String, Long> actualEntry = actualIter.next();
            Map.Entry<String, Long> expectedEntry = expectedIter.next();
            Assertions.assertEquals(actualEntry.getValue(),
                                    expectedEntry.getValue());
            Assertions.assertEquals(actualEntry.getKey(),
                                    expectedEntry.getKey());
        }

        Assertions.assertFalse(actualIter.hasNext());
        Assertions.assertFalse(expectedIter.hasNext());
    }
}