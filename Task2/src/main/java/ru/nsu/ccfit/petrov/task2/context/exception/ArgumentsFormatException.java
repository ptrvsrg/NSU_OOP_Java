package ru.nsu.ccfit.petrov.task2.context.exception;

/**
 * Thrown to indicate that argument has incorrect format
 */
public class ArgumentsFormatException
        extends ContextException
{
    /**
     * Create new {@code ArgumentsFormatException}.
     */
    public ArgumentsFormatException() {
        super("Incorrect format of arguments");
    }
}
