package ru.nsu.ccfit.petrov.task2.creator.exception;

public class ClassLoaderException
    extends CreatorException
{
    public ClassLoaderException()
    {
        super("Class is not loaded");
    }
}
