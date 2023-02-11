package ru.nsu.fit.oop.task2.command.exception;

public class NegativeNumberException
    extends RuntimeException
{
    public NegativeNumberException()
    {
        super("Number is negative, but command needs non-negative number");
    }
}
