package ru.nsu.ccfit.petrov.task2.context.exception;

/**
 * Thrown to indicate that variable name is incorrect
 */
public class VariableNameException
        extends ContextException
{
    /**
     * Create new {@code VariableNameException}.
     */
    public VariableNameException() {
        super("Incorrect variable name");
    }
}
