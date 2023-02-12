package ru.nsu.ccfit.petrov.task2.exception;

public class ResourceException
    extends RuntimeException
{
    public ResourceException()
    {
        super("Resource file is not found");
    }
}
