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
        Map <String, Long> actual = null;

        try
        {
            actual = wordCollector.getHistogram();
        }
        catch (IOException ex)
        {
            Assertions.fail(ex.getLocalizedMessage());
        }

        Map <String, Long> expected = new HashMap <>();
        for (int i = 0; i < keys.size(); ++i)
            expected.put(keys.get(i),
                         values.get(i));

        Assertions.assertArrayEquals(actual.entrySet()
                                           .toArray(),
                                     expected.entrySet()
                                             .toArray(),
                                     "Actual: " + actual + "\n Expected: " + expected);
    }

    @Test
    void getWordCount()
    {
        WordCollector wordCollector = new WordCollector(new InputStreamReader(new ByteArrayInputStream("a b c a b a".getBytes())));
        Assertions.assertThrows(UnsupportedOperationException.class,
                                wordCollector::getWordCount);
        try
        {
            wordCollector.getHistogram();
        }
        catch (IOException ex)
        {
            Assertions.fail(ex.getLocalizedMessage());
        }

        long actual = wordCollector.getWordCount();
        long expected = 6L;

        Assertions.assertEquals(actual,
                                expected);
    }
}