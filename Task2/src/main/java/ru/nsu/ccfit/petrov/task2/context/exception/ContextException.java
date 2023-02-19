package ru.nsu.ccfit.petrov.task2.context.exception;

/**
 * {@code ContextException} is superclass of those exceptions that can be thrown during working with calculator context
 */
public class ContextException
    extends RuntimeException
{
    /**
     * Create new {@code ContextException}.
     *
     * @param message message from subclass
     */
    public ContextException(String message)
    {
        super("Context exception: " + message);
    }
}
