package ru.nsu.ccfit.petrov.task2;

import org.apache.commons.cli.*;

/**
 * Class {@code CommandLineParser} extracts options and option arguments from command line arguments.
 */
public class CommandLineParser
{
    private CommandLine commandLine;

    /**
     * Extract options and option arguments from command line arguments.
     *
     * @param args command line arguments
     * @return {@code false}, if option {@code help} is found,
     * otherwise {@code true}
     * @throws ParseException exception during parsing command line arguments
     */
    public boolean parse(String[] args)
            throws ParseException {
        // Add available options
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

        // Create default command parser
        org.apache.commons.cli.CommandLineParser clParser = new DefaultParser();

        // Parse args
        commandLine = clParser.parse(opts,
                                     args);

        // Print help
        if (commandLine.hasOption("help")) {
            HelpFormatter helpFormatter = new HelpFormatter();
            helpFormatter.printHelp("calc",
                                    opts,
                                    true);
            return false;
        }

        return true;
    }

    /**
     * Return argument of option {@code input}
     *
     * @return argument of option {@code input} or {@code null}, if option {@code input} is not found
     */
    public String getInput() {
        return commandLine.getOptionValue("input");
    }

    /**
     * Return argument of option {@code output}
     *
     * @return argument of option {@code output} or {@code null}, if option {@code output} is not found
     */
    public String getOutput() {
        return commandLine.getOptionValue("output");
    }
}
