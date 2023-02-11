package ru.nsu.fit.oop.task2.command.exception;

public class EnoughStackValuesException
    extends RuntimeException
{
    public EnoughStackValuesException()
    {
        super("Enough values on stack");
    }
}
