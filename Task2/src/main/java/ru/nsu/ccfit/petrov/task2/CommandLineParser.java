package ru.nsu.ccfit.petrov.task2;

import org.apache.commons.cli.*;

public class CommandLineParser
{
    private CommandLine commandLine;

    public boolean parse(String[] args)
            throws ParseException
    {
        Options opts = new Options();
        opts.addOption(Option.builder()
                             .option("h")
                             .longOpt("help")
                             .desc("Print command help")
                             .build())
            .addOption(Option.builder()
                             .option("i")
                             .longOpt("input")
                             .desc("Input file")
                             .hasArg()
                             .argName("path")
                             .numberOfArgs(1)
                             .type(String.class)
                             .build())
            .addOption(Option.builder()
                             .option("o")
                             .longOpt("output")
                             .desc("Output file")
                             .hasArg()
                             .argName("path")
                             .type(String.class)
                             .build());

        org.apache.commons.cli.CommandLineParser clParser = new DefaultParser();
        commandLine = clParser.parse(opts,
                                     args);

        if (commandLine.hasOption("help"))
        {
            HelpFormatter helpFormatter = new HelpFormatter();
            helpFormatter.printHelp("calc",
                                    opts,
                                    true);
            return false;
        }

        return true;
    }

    public String getInput()
    {
        if (commandLine.hasOption("input"))
            return commandLine.getOptionValue("input");
        else
            return null;
    }

    public String getOutput()
    {
        if (commandLine.hasOption("output"))
            return commandLine.getOptionValue("output");
        else
            return null;
    }
}
