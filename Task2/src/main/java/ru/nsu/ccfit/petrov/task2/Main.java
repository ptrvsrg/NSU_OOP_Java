package ru.nsu.ccfit.petrov.task2;

import org.apache.commons.cli.ParseException;

import java.io.*;

public class Main
{
    public static void main(String[] args)
    {
        // Parse command line arguments
        CommandLineParser commandLineParser = new CommandLineParser();
        try
        {
            if (!commandLineParser.parse(args))
                return;
        }
        catch (ParseException ex)
        {
            System.err.println("Warnings: " + ex.getLocalizedMessage());
        }

        // Get i/o file names or null
        String inputFile = commandLineParser.getInput();
        String outputFile = commandLineParser.getOutput();

        // Get file streams or standard console stream
        try (InputStream in = (inputFile != null) ? new FileInputStream(inputFile) : System.in;
             OutputStream out = (outputFile != null) ? new FileOutputStream(outputFile) : System.out)
        {
            // Launch calculator
            Calculator calculator = new Calculator(in, out);
            calculator.run();
        }
        catch (Exception ex)
        {
            System.err.println("Error: " + ex.getLocalizedMessage());
        }
    }
}
