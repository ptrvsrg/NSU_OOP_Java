package ru.nsu.fit.oop.task2.command;

import ru.nsu.fit.oop.task2.Command;
import ru.nsu.fit.oop.task2.Context;
import ru.nsu.fit.oop.task2.command.exception.ArgumentsNumberException;

import java.util.List;

public class Pop
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
        context.popCalculatingValue();
    }
}
