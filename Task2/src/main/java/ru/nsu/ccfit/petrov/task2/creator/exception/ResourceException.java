package ru.nsu.ccfit.petrov.task2.creator.exception;

/**
 * Thrown to indicate that resource is not loaded
 */
public class ResourceException
    extends CreatorException
{
    /**
     * Create new {@code ResourceException}
     */
    public ResourceException()
    {
        super("Resource file is not found");
    }
}
