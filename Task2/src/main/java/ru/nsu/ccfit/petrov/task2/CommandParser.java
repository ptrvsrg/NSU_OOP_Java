package ru.nsu.ccfit.petrov.task2;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class {@code CommandParser} extracts command name and command arguments from input line.
 */
public class CommandParser
{
    private String commandName = null;
    private ArrayList<String> commandArgs = new ArrayList <>();

    /**
     * Extract command name and its arguments from input line.
     * Extracted data is contained in fields {@code commandName} and {@code args}.
     * Skip comments starting with symbol '#'.
     * Assign {@code null} to field {@code commandName} and empty {@code ArrayList} to field {@code commandArgs}
     * if required data is not found in input line
     *
     * @param line
     *        Input line
     */
    public void parse(String line)
    {
        if (line == null)
        {
            commandName = null;
            commandArgs = new ArrayList <>();
            return;
        }

        // Remove comments
        Pattern commentPattern = Pattern.compile("#.*");
        Matcher matcher = commentPattern.matcher(line);
        String command = matcher.replaceAll("");

        // Divide command into words
        Pattern wordPattern = Pattern.compile("\\s+");
        commandArgs = new ArrayList <>(List.of(wordPattern.split(command)));

        // Split into command name and command arguments
        try
        {
            commandName = commandArgs.remove(0);
            if (commandName.equals(""))
                commandName = null;
        }
        catch (IndexOutOfBoundsException ex)
        {
            commandName = null;
        }
    }

    /**
     * Returns command name extracted by method {@code void parse(String)}
     *
     * @return Command name or {@code null} if data is not found in input line.
     */
    public String getCommandName()
    {
        return commandName;
    }

    /**
     * Returns argument list extracted by method {@code void parse(String)}
     *
     * @return Argument list
     */
    public List <String> getCommandArgs()
    {
        return commandArgs;
    }
}
