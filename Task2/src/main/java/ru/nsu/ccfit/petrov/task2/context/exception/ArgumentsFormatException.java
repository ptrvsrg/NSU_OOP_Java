package ru.nsu.ccfit.petrov.task2.context.exception;

public class ArgumentsFormatException
    extends ContextException
{
    public ArgumentsFormatException()
    {
        super("Incorrect format of arguments");
    }
}
