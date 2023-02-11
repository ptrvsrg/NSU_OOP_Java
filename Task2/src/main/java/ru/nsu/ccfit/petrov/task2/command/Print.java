package ru.nsu.ccfit.petrov.task2.command;

import ru.nsu.ccfit.petrov.task2.Context;
import ru.nsu.ccfit.petrov.task2.command.exception.ArgumentsNumberException;

import java.io.PrintStream;
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
    {
        Double value = context.peekCalculatingValue();
        PrintStream out = new PrintStream(context.getOut());
        out.println((value != null) ? value : "");
    }
}
