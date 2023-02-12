package ru.nsu.ccfit.petrov.task2.exception;

public class DivisionByZeroException
    extends RuntimeException
{
    public DivisionByZeroException()
    {
        super("Division by zero");
    }
}
