package ptrvsrg.task1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
import java.util.stream.Stream;

class SorterTest
{

    private static Stream <Arguments> mapSortingByValueArgs()
    {
        return Stream.of(Arguments.of(Arrays.asList("Albert",
                                                    "John",
                                                    "Ronald",
                                                    "Michael"),
                                      Arrays.asList(62L,
                                                    14L,
                                                    31L,
                                                    31L),
                                      Arrays.asList(new AbstractMap.SimpleEntry <>("John",
                                                                                   14L),
                                                    new AbstractMap.SimpleEntry <>("Michael",
                                                                                   31L),
                                                    new AbstractMap.SimpleEntry <>("Ronald",
                                                                                   31L),
                                                    new AbstractMap.SimpleEntry <>("Albert",
                                                                                   62L)),
                                      Sorter.SortingType.ASCENDING),
                         Arguments.of(Arrays.asList("Albert",
                                                    "John",
                                                    "Ronald",
                                                    "Michael"),
                                      Arrays.asList(62L,
                                                    14L,
                                                    31L,
                                                    31L),
                                      Arrays.asList(new AbstractMap.SimpleEntry <>("Albert",
                                                                                   62L),
                                                    new AbstractMap.SimpleEntry <>("Michael",
                                                                                   31L),
                                                    new AbstractMap.SimpleEntry <>("Ronald",
                                                                                   31L),
                                                    new AbstractMap.SimpleEntry <>("John",
                                                                                   14L)),
                                      Sorter.SortingType.DESCENDING));
    }

    @ParameterizedTest
    @MethodSource("mapSortingByValueArgs")
    void mapSortingByValue(List <String> keys,
                           List <Long> values,
                           List <AbstractMap.SimpleEntry <String, Long>> expected,
                           Sorter.SortingType sortingType)
    {
        Map <String, Long> actualMap = new HashMap <>();
        for (int i = 0; i < keys.size(); ++i)
            actualMap.put(keys.get(i),
                          values.get(i));

        SortedSet <Map.Entry <String, Long>> actual = Sorter.mapSortingByValue(actualMap,
                                                                               sortingType);
        Assertions.assertArrayEquals(expected.toArray(),
                                     actual.toArray());
    }
}