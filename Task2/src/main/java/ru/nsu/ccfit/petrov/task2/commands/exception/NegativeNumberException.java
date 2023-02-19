package ru.nsu.ccfit.petrov.task2.commands.exception;

public class NegativeNumberException
    extends CommandException
{
    public NegativeNumberException()
    {
        super("Command needs non-negative number");
    }
}
