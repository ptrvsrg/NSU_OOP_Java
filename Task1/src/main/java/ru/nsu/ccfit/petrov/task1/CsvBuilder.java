package ru.nsu.ccfit.petrov.task1;

import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Map;

public class CsvBuilder
    implements Closeable
{
    private final OutputStreamWriter out;

    public CsvBuilder(OutputStreamWriter outputStreamWriter)
    {
        out = outputStreamWriter;
    }

    public void build(Set <Map.Entry <String, Long>> set,
                      long wordCount)
            throws IOException
    {
        out.write("Word,Frequency,Frequency percentage\n");
        for (Map.Entry <String, Long> entry : histogram)
        {
            double frequencyPercentage = (double) entry.getValue() * 100 / wordCount;
            out.write(entry.getKey() + ",");
            out.write(entry.getValue() + ",");
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
