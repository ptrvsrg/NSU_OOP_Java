package ru.nsu.ccfit.petrov.task2.commands.exception;

public class ArgumentsNumberException
    extends CommandException
{
    public ArgumentsNumberException()
    {
        super("Incorrect number of arguments");
    }
}
