package ru.nsu.ccfit.petrov.task2.creator.exception;

/**
 * {@code ContextException} is superclass of those exceptions that can be thrown during working with command creator
 */
public class CreatorException
    extends RuntimeException
{
    /**
     * Create new {@code CreatorException}
     *
     * @param message message from subclass
     */
    public CreatorException(String message)
    {
        super("Creator exception: " + message);
    }
}
