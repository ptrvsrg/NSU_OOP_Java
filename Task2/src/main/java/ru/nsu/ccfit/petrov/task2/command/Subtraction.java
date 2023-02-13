package ru.nsu.ccfit.petrov.task2.command;

import ru.nsu.ccfit.petrov.task2.Context;
import ru.nsu.ccfit.petrov.task2.exception.ArgumentsNumberException;
import ru.nsu.ccfit.petrov.task2.exception.EnoughStackValuesException;

import java.util.List;

public class Subtraction
    implements Command
{
    @Override
    public void run(List <String> args, Context context)
    {
        // Check args count
        if (!args.isEmpty())
            throw new ArgumentsNumberException();

        // Pop subtrahend from stack
        if (context.peekCalculatingValue() == null)
            throw new EnoughStackValuesException();
        Double subtrahend = context.popCalculatingValue();

        // Pop addend1 from stack
        if (context.peekCalculatingValue() == null)
        {
            // Push subtrahend onto stack
            context.pushCalculatingValue(subtrahend);
            throw new EnoughStackValuesException();
        }
        Double minuend = context.popCalculatingValue();

        // Push result onto stack
        context.pushCalculatingValue(minuend - subtrahend);
    }
}
