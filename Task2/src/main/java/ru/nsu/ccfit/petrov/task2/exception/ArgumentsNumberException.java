package ru.nsu.ccfit.petrov.task2.exception;

public class ArgumentsNumberException
    extends RuntimeException
{
    public ArgumentsNumberException()
    {
        super("Incorrect number of command arguments");
    }
}
