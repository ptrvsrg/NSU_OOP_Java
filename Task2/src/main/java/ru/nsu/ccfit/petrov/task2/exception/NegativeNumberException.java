package ru.nsu.ccfit.petrov.task2.exception;

public class NegativeNumberException
    extends RuntimeException
{
    public NegativeNumberException()
    {
        super("Number is negative, but command needs non-negative number");
    }
}