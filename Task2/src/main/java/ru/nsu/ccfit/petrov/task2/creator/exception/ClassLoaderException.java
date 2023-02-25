package ru.nsu.ccfit.petrov.task2.creator.exception;

/**
 * Thrown to indicate that class is not loaded
 */
public class ClassLoaderException
        extends CreatorException
{
    /**
     * Create new {@code ClassLoaderException}
     */
    public ClassLoaderException() {
        super("Class is not loaded");
    }
}
