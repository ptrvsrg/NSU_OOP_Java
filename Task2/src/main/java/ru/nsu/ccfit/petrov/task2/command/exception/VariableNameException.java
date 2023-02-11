package ru.nsu.ccfit.petrov.task2.command.exception;

public class VariableNameException
    extends RuntimeException
{
    public VariableNameException()
    {
        super("Incorrect variable name");
    }
}
