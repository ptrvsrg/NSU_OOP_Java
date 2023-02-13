package ru.nsu.ccfit.petrov.task1;

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
            CommandLineParser clParser = new CommandLineParser();
            if (!clParser.parse(args))
                return;

            String inputFile = clParser.getInput();
            String outputFile = clParser.getOutput();

            try (InputStreamReader inputStreamReader = new FileReader(inputFile);
                 OutputStreamWriter outputStreamWriter = new FileWriter(outputFile, false))
            {
                WordCollector wordCollector = new WordCollector(inputStreamReader);
                Map <String, Long> histogram = wordCollector.getHistogram();
                SortedSet <Map.Entry <String, Long>> sortedSet = Sorter.mapSortingByValue(histogram);
                CsvBuilder csvBuilder = new CsvBuilder(outputStreamWriter);
                csvBuilder.build(sortedSet,
                                 wordCollector.getWordCount());
            }
        }
        catch (ParseException |
               IOException ex)
        {
            ex.printStackTrace(System.err);
        }
    }
}
