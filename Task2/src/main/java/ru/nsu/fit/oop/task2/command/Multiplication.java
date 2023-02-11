package ru.nsu.fit.oop.task2.command;

import ru.nsu.fit.oop.task2.Command;
import ru.nsu.fit.oop.task2.Context;
import ru.nsu.fit.oop.task2.command.exception.ArgumentsNumberException;
import ru.nsu.fit.oop.task2.command.exception.EnoughStackValuesException;

import java.io.IOException;
import java.util.List;

public class Multiplication
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
        Double multiplier2 = context.popCalculatingValue();
        Double multiplier1 = context.popCalculatingValue();

        if (multiplier1 == null || multiplier2 == null)
            throw new EnoughStackValuesException();

        context.pushCalculatingValue(multiplier1 * multiplier2);
    }
}
