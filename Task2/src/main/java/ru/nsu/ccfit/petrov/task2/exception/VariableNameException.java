package ru.nsu.ccfit.petrov.task2.exception;

public class VariableNameException
    extends RuntimeException
{
    public VariableNameException()
    {
        super("Incorrect variable name");
    }
}
