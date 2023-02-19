package ru.nsu.ccfit.petrov.task2.context.exception;

public class EmptyStackException
    extends ContextException
{
    public EmptyStackException()
    {
        super("Empty stack");
    }
}
