package ru.nsu.ccfit.petrov.task2.commands.exception;

public class DivisionByZeroException
    extends CommandException
{
    public DivisionByZeroException()
    {
        super("Division by zero");
    }
}
