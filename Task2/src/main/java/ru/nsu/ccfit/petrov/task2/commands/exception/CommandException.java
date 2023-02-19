package ru.nsu.ccfit.petrov.task2.commands.exception;

public class CommandException
    extends RuntimeException
{
    public CommandException(String message)
    {
        super("Command exception: " + message);
    }
}
