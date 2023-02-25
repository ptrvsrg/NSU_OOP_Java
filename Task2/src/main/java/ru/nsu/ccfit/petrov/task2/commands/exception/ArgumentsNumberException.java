package ru.nsu.ccfit.petrov.task2.commands.exception;

/**
 * Thrown to indicate that arguments number is incorrect
 */
public class ArgumentsNumberException
        extends CommandException
{
    /**
     * Create new {@code ArgumentsNumberException}
     */
    public ArgumentsNumberException() {
        super("Incorrect number of arguments");
    }
}
