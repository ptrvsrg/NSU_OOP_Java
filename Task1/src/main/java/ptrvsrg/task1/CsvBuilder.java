package ptrvsrg.task1;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Map;
import java.util.Set;

public class CsvBuilder
{
    private final OutputStreamWriter csvWriter;

    public CsvBuilder(OutputStreamWriter outputStreamWriter)
    {
        csvWriter = outputStreamWriter;
    }

    public void build(Set <Map.Entry <String, Long>> set,
                      long wordCount)
            throws IOException
    {
        csvWriter.write("Word,Frequency,Frequency percentage\n");
        for (Map.Entry <String, Long> elem : set)
        {
            double frequencyPercentage = (double) elem.getValue() * 100 / wordCount;
            csvWriter.write(elem.getKey() + ",");
            csvWriter.write(elem.getValue() + ",");
            csvWriter.write(String.format("%.3f", frequencyPercentage) + "\n");
            csvWriter.flush();
        }
    }
}
