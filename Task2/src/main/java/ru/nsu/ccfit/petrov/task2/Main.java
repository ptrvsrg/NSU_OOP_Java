package ru.nsu.ccfit.petrov.task2;

import org.apache.commons.cli.ParseException;
import org.apache.log4j.Logger;

import java.io.*;


public class Main
{
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args)
    {
        // Parse command line arguments
        logger.info("Parse command line arguments");
        CommandLineParser commandLineParser = new CommandLineParser();
        try
        {
            if (!commandLineParser.parse(args))
                return;
        }
        catch (ParseException ex)
        {
            logger.error("Exception: ",
                         ex);
            return;
        }

        // Get i/o file names or null
        String inputFile = commandLineParser.getInput();
        String outputFile = commandLineParser.getOutput();

        // Get file streams or standard console stream and create calculator
        logger.info("Create calculator");
        try (InputStream in = (inputFile != null) ?
                new FileInputStream(inputFile) :
                System.in;
             OutputStream out = (outputFile != null) ?
                new FileOutputStream(outputFile) :
                System.out;
            Calculator calculator = new Calculator(in, out))
        {
            // Launch calculator
            logger.info("Launch calculator");
            calculator.run();
        }
        catch (Exception ex)
        {
            logger.error("Exception: ",
                         ex);
        }
    }
}
