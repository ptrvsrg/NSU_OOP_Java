package ru.nsu.fit.oop.task2.command;

import ru.nsu.fit.oop.task2.Command;
import ru.nsu.fit.oop.task2.Context;
import ru.nsu.fit.oop.task2.command.exception.ArgumentsNumberException;
import ru.nsu.fit.oop.task2.command.exception.EnoughStackValuesException;

import java.io.IOException;
import java.util.List;

public class Subtraction
    implements Command
{
    @Override
    public void setArgs(List <String> args)
    {
        if (!args.isEmpty())
            throw new ArgumentsNumberException();
    }

    @Override
    public void run(Context context)
            throws IOException
    {
        Double subtrahend = context.popCalculatingValue();
        Double minuend = context.popCalculatingValue();

        if (minuend == null || subtrahend == null)
            throw new EnoughStackValuesException();

        context.pushCalculatingValue(minuend - subtrahend);
    }
}
