package ru.nsu.ccfit.petrov.task2;

import org.apache.commons.cli.ParseException;

import java.io.*;

public class Main
{
    public static void main(String[] args)
    {
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

        String inputFile = commandLineParser.getInput();
        String outputFile = commandLineParser.getOutput();

        try (InputStream in = (inputFile != null) ? new FileInputStream(inputFile) : System.in;
             OutputStream out = (outputFile != null) ? new FileOutputStream(outputFile) : System.out)
        {
            Calculator calculator = new Calculator(in, out);
            calculator.run();
        }
        catch (Exception ex)
        {
            System.err.println("Error: " + ex.getLocalizedMessage());
        }
    }
}
