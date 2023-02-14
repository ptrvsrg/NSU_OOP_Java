package ru.nsu.ccfit.petrov.task1;

import org.apache.commons.cli.ParseException;

import java.io.*;

public class Main
{
    public static void main(String[] args)
    {
        CommandLineParser clParser = new CommandLineParser();

        try
        {
            if (!clParser.parse(args))
                return;
        }
        catch (ParseException ex)
        {
            ex.printStackTrace(System.err);
            return;
        }

        String inputFile = clParser.getInput();
        String outputFile = clParser.getOutput();

        try (WordCollector wordCollector = new WordCollector(new FileReader(inputFile));
             CsvBuilder csvBuilder = new CsvBuilder(new FileWriter(outputFile,
                                                                   false)))
        {
            SortedHistogram histogram = wordCollector.getHistogram();
            csvBuilder.build(histogram,
                             wordCollector.getWordCount());
        }
        catch (IOException ex)
        {
            ex.printStackTrace(System.err);
        }
    }
}
