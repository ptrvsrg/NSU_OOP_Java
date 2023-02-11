package ru.nsu.ccfit.petrov.task2.command;

import ru.nsu.ccfit.petrov.task2.command.exception.EnoughStackValuesException;
import ru.nsu.ccfit.petrov.task2.command.exception.NegativeNumberException;
import ru.nsu.ccfit.petrov.task2.Context;
import ru.nsu.ccfit.petrov.task2.command.exception.ArgumentsNumberException;

import java.util.List;

public class SquareRoot
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
        Double num = context.popCalculatingValue();

        if (num == null)
            throw new EnoughStackValuesException();
        if (num < 0)
            throw new NegativeNumberException();

        context.pushCalculatingValue(Math.sqrt(num));
    }
}
