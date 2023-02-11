package ru.nsu.ccfit.petrov.task2.command.exception;

public class EnoughStackValuesException
    extends RuntimeException
{
    public EnoughStackValuesException()
    {
        super("Enough values on stack");
    }
}
