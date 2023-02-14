package ru.nsu.ccfit.petrov.task1;

import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class CsvBuilder
        implements Closeable
{
    private final OutputStreamWriter out;

    public CsvBuilder(OutputStreamWriter outputStreamWriter)
    {
        out = outputStreamWriter;
    }

    public void build(SortedHistogram histogram,
                      long wordCount)
            throws IOException
    {
        out.write("Word,Frequency,Frequency percentage\n");
        for (CountedWord entry : histogram)
        {
            double frequencyPercentage = (double) entry.getCount() * 100 / wordCount;
            out.write(entry.getWord() + ",");
            out.write(entry.getCount() + ",");
            out.write(String.format("%.3f", frequencyPercentage) + "\n");
            out.flush();
        }
    }

    @Override
    public void close()
            throws IOException
    {
        out.close();
    }
}
