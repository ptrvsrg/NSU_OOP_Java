package ru.nsu.ccfit.petrov.task1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
import java.util.stream.Stream;

class SortedHistogramTest
{
    private static Stream <Arguments> sortedHistogramTestArgs()
    {
        return Stream.of(Arguments.of(new ArrayList <>(),
                                      new ArrayList <>()),
                         Arguments.of(List.of(new CountedWord("a",
                                                              3L),
                                              new CountedWord("c",
                                                              1L),
                                              new CountedWord("b",
                                                              2L)),
                                      List.of(new CountedWord("a",
                                                              3L),
                                              new CountedWord("b",
                                                              2L),
                                              new CountedWord("c",
                                                              1L))));
    }

    @ParameterizedTest
    @MethodSource("sortedHistogramTestArgs")
    void sortedHistogramTest(List <CountedWord> actual,
                             List <CountedWord> expected)
    {
        SortedHistogram histogram = new SortedHistogram();

        for (CountedWord entry : actual)
            for (int i = 0; i < entry.getCount(); ++i)
                histogram.add(entry.getWord());

        Iterator <CountedWord> actualIter = histogram.iterator();
        Iterator <CountedWord> expectedIter = expected.iterator();

        while (actualIter.hasNext() && expectedIter.hasNext())
        {
            CountedWord actualEntry = actualIter.next();
            CountedWord expectedEntry = expectedIter.next();
            Assertions.assertEquals(actualEntry.getCount(),
                                    expectedEntry.getCount());
            Assertions.assertEquals(actualEntry.getWord(),
                                    expectedEntry.getWord());
        }

        Assertions.assertFalse(actualIter.hasNext());
        Assertions.assertFalse(expectedIter.hasNext());
    }
}