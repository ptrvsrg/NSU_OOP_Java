package ru.nsu.ccfit.petrov.task1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Stream;

class WordCollectorTest
{

    private static Stream <Arguments> getHistogramArgs()
    {
        return Stream.of(Arguments.of(new InputStreamReader(new ByteArrayInputStream("a b c a b a".getBytes())),
                                      Arrays.asList("a",
                                                    "b",
                                                    "c"),
                                      Arrays.asList(3L,
                                                    2L,
                                                    1L)),
                         Arguments.of(new InputStreamReader(new ByteArrayInputStream("".getBytes())),
                                      new ArrayList <String>(),
                                      new ArrayList <Long>()),
                         Arguments.of(new InputStreamReader(new ByteArrayInputStream("a A a bB c C1 43231".getBytes())),
                                      Arrays.asList("a",
                                                    "bb",
                                                    "c",
                                                    "c1",
                                                    "43231"),
                                      Arrays.asList(3L,
                                                    1L,
                                                    1L,
                                                    1L,
                                                    1L)));
    }

    @ParameterizedTest
    @MethodSource("getHistogramArgs")
    void getHistogram(InputStreamReader reader,
                      List <String> keys,
                      List <Long> values)
    {
        WordCollector wordCollector = new WordCollector(reader);
        SortedHistogram actual = null;

        try
        {
            actual = wordCollector.getHistogram();
        }
        catch (IOException ex)
        {
            Assertions.fail(ex);
        }

        SortedHistogram expected = new SortedHistogram();
        for (int i = 0; i < keys.size(); ++i)
            for (int j = 0; j < values.get(i); ++j)
                expected.add(keys.get(i));

        Iterator <CountedWord> actualIter = actual.iterator();
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

    @Test
    void getWordCount()
    {
        WordCollector wordCollector = new WordCollector(new InputStreamReader(new ByteArrayInputStream("a b c a b a".getBytes())));

        try
        {
            wordCollector.getHistogram();
        }
        catch (IOException ex)
        {
            Assertions.fail(ex);
        }

        Assertions.assertEquals(wordCollector.getWordCount(),
                                6L);
    }
}