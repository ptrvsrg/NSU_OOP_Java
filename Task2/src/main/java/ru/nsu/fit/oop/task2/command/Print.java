package ru.nsu.fit.oop.task2.command;

import ru.nsu.fit.oop.task2.Command;
import ru.nsu.fit.oop.task2.Context;
import ru.nsu.fit.oop.task2.command.exception.ArgumentsNumberException;

import java.io.IOException;
import java.util.List;

public class Print
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
        Double value = context.peekCalculatingValue();
        if (value != null)
            context.getOut().write(value.toString().getBytes());
    }
}
