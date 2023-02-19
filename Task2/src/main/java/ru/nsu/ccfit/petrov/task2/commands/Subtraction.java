package ru.nsu.ccfit.petrov.task2.commands;

import ru.nsu.ccfit.petrov.task2.commands.exception.ArgumentsNumberException;
import ru.nsu.ccfit.petrov.task2.context.Context;
import ru.nsu.ccfit.petrov.task2.context.exception.EmptyStackException;

import java.util.List;

/**
 * Class {@code Subtraction} implements subtraction operation
 */
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
        Double subtrahend = context.popStackValue();

        // Pop addend1 from stack
        Double minuend;
        try
        {
            minuend = context.popStackValue();
        }
        catch (EmptyStackException ex)
        {
            // Push subtrahend onto stack
            context.pushStackValue(subtrahend);
            throw ex;
        }

        // Push result onto stack
        context.pushStackValue(minuend - subtrahend);
    }
}
