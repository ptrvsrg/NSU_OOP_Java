package ru.nsu.ccfit.petrov.task2.creator.exception;

public class CommandNotCreatedException
    extends CreatorException
{
    public CommandNotCreatedException()
    {
        super("Command is not created");
    }
}
