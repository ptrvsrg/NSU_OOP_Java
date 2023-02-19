package ru.nsu.ccfit.petrov.task2.commands;

import ru.nsu.ccfit.petrov.task2.commands.exception.ArgumentsNumberException;
import ru.nsu.ccfit.petrov.task2.commands.exception.DivisionByZeroException;
import ru.nsu.ccfit.petrov.task2.context.Context;
import ru.nsu.ccfit.petrov.task2.context.exception.EmptyStackException;

import java.util.List;

/**
 * Class {@code Division} implements division operation
 */
public class Division
    implements Command
{
    @Override
    public void run(List <String> args, Context context)
    {
        // Check args count
        if (!args.isEmpty())
            throw new ArgumentsNumberException();

        // Pop divisor from stack
        Double divisor = context.popStackValue();

        // Check division by zero
        if (Math.abs(divisor) < 1.0E-09)
        {
            // Push divisor onto stack
            context.pushStackValue(divisor);
            throw new DivisionByZeroException();
        }

        // Pop dividend from stack
        Double dividend;
        try
        {
            dividend = context.popStackValue();
        }
        catch (EmptyStackException ex)
        {
            // Push divisor onto stack
            context.pushStackValue(divisor);
            throw ex;
        }

        // Push result onto stack
        context.pushStackValue(dividend / divisor);
    }
}
