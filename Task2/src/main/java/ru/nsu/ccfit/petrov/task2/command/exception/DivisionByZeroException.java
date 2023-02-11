package ru.nsu.ccfit.petrov.task2.command.exception;

public class DivisionByZeroException
    extends RuntimeException
{
    public DivisionByZeroException()
    {
        super("Division by zero");
    }
}
