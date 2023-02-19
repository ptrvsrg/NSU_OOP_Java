package ru.nsu.ccfit.petrov.task2.commands;

import ru.nsu.ccfit.petrov.task2.commands.exception.ArgumentsNumberException;
import ru.nsu.ccfit.petrov.task2.context.Context;

import java.io.PrintStream;
import java.util.List;

public class Print
        implements Command
{
    @Override
    public void run(List <String> args, Context context)
    {
        // Check args count
        if (!args.isEmpty())
            throw new ArgumentsNumberException();

        // Get output stream from context
        PrintStream out = new PrintStream(context.getOut());

        // Print extracted value
        out.println(context.peekStackValue());
    }
}
