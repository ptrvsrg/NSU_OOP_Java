package ru.nsu.fit.oop.task2.command.exception;

public class ArgumentsNumberException
    extends RuntimeException
{
    public ArgumentsNumberException()
    {
        super("Incorrect number of command arguments");
    }
}
