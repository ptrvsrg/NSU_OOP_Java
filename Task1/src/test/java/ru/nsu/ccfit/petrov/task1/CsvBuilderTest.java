package ru.nsu.ccfit.petrov.task1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;

class CsvBuilderTest
{

    @Test
    void buildTest() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        CsvBuilder csvBuilder = new CsvBuilder(new OutputStreamWriter(out));
        SortedHistogram histogram = new SortedHistogram();
        histogram.add("Misha");
        histogram.add("Masha");
        histogram.add("Masha");
        histogram.add("Masha");

        Assertions.assertDoesNotThrow(() -> csvBuilder.build(histogram,
                                                             4L));
        Assertions.assertEquals(out.toString(),
                                "Word,Frequency,Frequency percentage\n" + "Masha,3,75.000\n" + "Misha,1,25.000\n");
    }
}