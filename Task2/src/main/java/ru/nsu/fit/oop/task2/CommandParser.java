package ru.nsu.fit.oop.task2;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandParser
{
    private String commandName = null;
    private ArrayList<String> args = new ArrayList <>();

    public void parse(String line)
    {
        if (line == null)
        {
            commandName = null;
            args = new ArrayList <>();
            return;
        }

        // Remove comments
        Pattern commentPattern = Pattern.compile("#.*");
        Matcher matcher = commentPattern.matcher(line);
        String command = matcher.replaceAll("");

        // Divide command into words
        Pattern wordPattern = Pattern.compile("\\s+");
        args = new ArrayList <>(List.of(wordPattern.split(command)));

        // Extract command name
        try
        {
            commandName = args.remove(0);
            if (commandName.equals(""))
                commandName = null;
        }
        catch (IndexOutOfBoundsException ex)
        {
            commandName = null;
        }
    }

    public String getCommandName()
    {
        return commandName;
    }

    public List <String> getArgs()
    {
        return args;
    }
}
