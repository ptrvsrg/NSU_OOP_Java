package ru.nsu.ccfit.petrov.task2.command.exception;

public class ArgumentsFormatException
    extends RuntimeException
{
    public ArgumentsFormatException()
    {
        super("Incorrect format of command arguments");
    }
}
