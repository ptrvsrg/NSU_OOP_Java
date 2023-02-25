package ru.nsu.ccfit.petrov.task2.creator.exception;

/**
 * Thrown to indicate that command is not created
 */
public class CommandNotCreatedException
        extends CreatorException
{
    /**
     * Create new {@code CommandNotCreatedException}
     */
    public CommandNotCreatedException() {
        super("Command is not created");
    }
}
