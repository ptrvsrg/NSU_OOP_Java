package ru.nsu.ccfit.petrov.task2.context.exception;

/**
 * Thrown to indicate that stack is empty
 */
public class EmptyStackException
        extends ContextException
{
    /**
     * Create new {@code EmptyStackException}.
     */
    public EmptyStackException() {
        super("Empty stack");
    }
}
