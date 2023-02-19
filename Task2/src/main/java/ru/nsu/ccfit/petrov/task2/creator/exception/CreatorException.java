package ru.nsu.ccfit.petrov.task2.creator.exception;

public class CreatorException
    extends RuntimeException
{
    public CreatorException(String message)
    {
        super("Creator exception: " + message);
    }
}
