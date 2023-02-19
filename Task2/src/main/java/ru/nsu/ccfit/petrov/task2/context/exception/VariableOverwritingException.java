package ru.nsu.ccfit.petrov.task2.context.exception;

public class VariableOverwritingException
    extends ContextException
{
    public VariableOverwritingException()
    {
        super("Attempting to overwrite a variable");
    }
}
