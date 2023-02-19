package ru.nsu.ccfit.petrov.task2.creator.exception;

public class ResourceException
    extends CreatorException
{
    public ResourceException()
    {
        super("Resource file is not found");
    }
}
