package ru.nsu.ccfit.petrov.task2.commands.exception;

/**
 * Thrown to indicate that argument is negative
 */
public class NegativeNumberException
        extends CommandException
{
    /**
     * Create new {@code NegativeNumberException}
     */
    public NegativeNumberException() {
        super("Command needs non-negative number");
    }
}
