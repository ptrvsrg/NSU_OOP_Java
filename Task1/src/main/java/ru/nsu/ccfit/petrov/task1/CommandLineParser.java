package ru.nsu.ccfit.petrov.task1;

import org.apache.commons.cli.*;

public class CommandLineParser
{
    private CommandLine commandLine;

    public boolean parse(String[] args)
            throws ParseException {
        // Add help option
        Options opts = new Options();
        opts.addOption(Option.builder()
                             .option("h")
                             .longOpt("help")
                             .desc("Print command help")
                             .build());

        // Parse command line with help option
        org.apache.commons.cli.CommandLineParser clParser = new DefaultParser();
        commandLine = clParser.parse(opts,
                                     args,
                                     true);

        // Add other options
        opts.addOption(Option.builder()
                             .option("i")
                             .longOpt("input")
                             .desc("Input file")
                             .hasArg()
                             .argName("path")
                             .numberOfArgs(1)
                             .type(String.class)
                             .required()
                             .build())
            .addOption(Option.builder()
                             .option("o")
                             .longOpt("output")
                             .desc("Output csv file")
                             .hasArg()
                             .argName("path")
                             .numberOfArgs(1)
                             .type(String.class)
                             .required()
                             .build());

        // Print help
        if (args.length == 0 || commandLine.hasOption("help")) {
            HelpFormatter helpFormatter = new HelpFormatter();
            helpFormatter.printHelp("app",
                                    opts,
                                    true);
            return false;
        }

        // Parse command line with all options
        commandLine = clParser.parse(opts,
                                     args);
        return true;
    }

    public String getInput() {
        return commandLine.getOptionValue("input");
    }

    public String getOutput() {
        return commandLine.getOptionValue("output");
    }
}
