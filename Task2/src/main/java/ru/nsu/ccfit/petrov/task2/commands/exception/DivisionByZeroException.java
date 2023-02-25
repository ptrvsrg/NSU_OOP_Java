package ru.nsu.ccfit.petrov.task2.commands.exception;

/**
 * Thrown to indicate that division by zero has been detected
 */
public class DivisionByZeroException
        extends CommandException
{
    /**
     * Create new {@code DivisionByZeroException}
     */
    public DivisionByZeroException() {
        super("Division by zero");
    }
}
