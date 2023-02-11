package ru.nsu.fit.oop.task2.command;

import ru.nsu.fit.oop.task2.Command;
import ru.nsu.fit.oop.task2.Context;
import ru.nsu.fit.oop.task2.command.exception.ArgumentsNumberException;
import ru.nsu.fit.oop.task2.command.exception.EnoughStackValuesException;

import java.io.IOException;
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
            throws IOException
    {
        Double addend2 = context.popCalculatingValue();
        Double addend1 = context.popCalculatingValue();

        if (addend1 == null || addend2 == null)
            throw new EnoughStackValuesException();

        context.pushCalculatingValue(addend1 + addend2);
    }
}
