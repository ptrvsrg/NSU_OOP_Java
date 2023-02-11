package ru.nsu.ccfit.petrov.task2.command;

import ru.nsu.ccfit.petrov.task2.Context;
import ru.nsu.ccfit.petrov.task2.command.exception.EnoughStackValuesException;
import ru.nsu.ccfit.petrov.task2.command.exception.ArgumentsNumberException;
import ru.nsu.ccfit.petrov.task2.command.exception.DivisionByZeroException;

import java.util.List;

public class Division
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
        Double divisor = context.popCalculatingValue();
        Double dividend = context.popCalculatingValue();

        if (dividend == null || divisor == null)
            throw new EnoughStackValuesException();
        if (Math.abs(divisor) < 1.0E-09)
            throw new DivisionByZeroException();

        context.pushCalculatingValue(dividend / divisor);
    }
}
