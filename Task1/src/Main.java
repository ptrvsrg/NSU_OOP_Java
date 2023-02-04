import org.apache.commons.cli.ParseException;

import java.io.*;
import java.util.Map;
import java.util.SortedSet;

public class Main
{
    public static void main(String[] args)
    {
        try
        {
            ClParser clParser = new ClParser(args);
            String inputFile = (String) clParser.getInput();
            String outputFile = (String) clParser.getOutput();

            try (InputStreamReader inputStreamReader = new FileReader(inputFile);
                 OutputStreamWriter outputStreamWriter = new FileWriter(outputFile, false))
            {
                WordCollector wordCollector = new WordCollector(inputStreamReader);
                Map <String, Long> histogram = wordCollector.getHistogram();
                SortedSet <Map.Entry <String, Long>> sortedSet = Sorter.mapSortingByValue(histogram,
                                                                                          Sorter.SortingType.DESCENDING);
                CsvBuilder csvBuilder = new CsvBuilder(outputStreamWriter);
                csvBuilder.build(sortedSet,
                                 wordCollector.getWordCount());
            }
        }
        catch (ParseException ex)
        {
            System.err.println(ex.getLocalizedMessage());
        }
        catch (IOException ex)
        {
            System.err.println(ex.getMessage());
        }
    }
}
