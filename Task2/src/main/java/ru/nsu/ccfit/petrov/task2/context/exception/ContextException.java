package ru.nsu.ccfit.petrov.task2.context.exception;

public class ContextException
    extends RuntimeException
{
    public ContextException(String message)
    {
        super("Context exception: " + message);
    }
}
