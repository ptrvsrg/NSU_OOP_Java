package ru.nsu.ccfit.petrov.task2.command;

import ru.nsu.ccfit.petrov.task2.Context;
import ru.nsu.ccfit.petrov.task2.exception.ArgumentsNumberException;
import ru.nsu.ccfit.petrov.task2.exception.EnoughStackValuesException;

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

        // Pop value from stack
        Double value = context.peekCalculatingValue();

        // Print value
        if (value != null)
            out.println(value);
        else
            throw new EnoughStackValuesException();
    }
}
