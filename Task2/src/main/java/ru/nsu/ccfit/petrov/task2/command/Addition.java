package ru.nsu.ccfit.petrov.task2.command;

import ru.nsu.ccfit.petrov.task2.exception.EnoughStackValuesException;
import ru.nsu.ccfit.petrov.task2.Context;
import ru.nsu.ccfit.petrov.task2.exception.ArgumentsNumberException;

import java.util.List;

public class Addition
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
        Double addend2 = context.popCalculatingValue();
        Double addend1 = context.popCalculatingValue();

        if (addend1 == null || addend2 == null)
            throw new EnoughStackValuesException();

        context.pushCalculatingValue(addend1 + addend2);
    }
}
