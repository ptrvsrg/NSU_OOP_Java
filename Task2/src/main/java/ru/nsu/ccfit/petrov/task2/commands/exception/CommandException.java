package ru.nsu.ccfit.petrov.task2.commands.exception;

/**
 * {@code CommandException} is superclass of those exceptions that can be thrown during working with commands
 */
public class CommandException
    extends RuntimeException
{
    /**
     * Create new {@code CommandException}
     *
     * @param message message from subclass
     */
    public CommandException(String message)
    {
        super("Command exception: " + message);
    }
}
