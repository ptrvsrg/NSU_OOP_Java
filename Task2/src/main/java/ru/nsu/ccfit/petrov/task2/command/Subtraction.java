package ru.nsu.ccfit.petrov.task2.command;

import ru.nsu.ccfit.petrov.task2.Context;
import ru.nsu.ccfit.petrov.task2.exception.ArgumentsNumberException;
import ru.nsu.ccfit.petrov.task2.exception.EnoughStackValuesException;

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
    {
        Double subtrahend = context.popCalculatingValue();
        Double minuend = context.popCalculatingValue();

        if (minuend == null || subtrahend == null)
            throw new EnoughStackValuesException();

        context.pushCalculatingValue(minuend - subtrahend);
    }
}
