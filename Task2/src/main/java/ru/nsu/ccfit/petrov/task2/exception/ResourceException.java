package ru.nsu.ccfit.petrov.task2.exception;

public class ResourceException
    extends RuntimeException
{
    public ResourceException()
    {
        super("Unrecognized resource file");
    }
}
