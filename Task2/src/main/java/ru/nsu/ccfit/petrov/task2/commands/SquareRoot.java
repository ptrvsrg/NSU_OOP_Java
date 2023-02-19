package ru.nsu.ccfit.petrov.task2.commands;

import ru.nsu.ccfit.petrov.task2.commands.exception.ArgumentsNumberException;
import ru.nsu.ccfit.petrov.task2.commands.exception.NegativeNumberException;
import ru.nsu.ccfit.petrov.task2.context.Context;

import java.util.List;

/**
 * Class {@code SquareRoot} implements square root operation
 */
public class SquareRoot
        implements Command
{
    @Override
    public void run(List <String> args, Context context)
    {
        // Check args count
        if (!args.isEmpty())
            throw new ArgumentsNumberException();

        // Get element from stack
        Double numSquare = context.popStackValue();

        if (numSquare < 0)
        {
            // Push numSquare onto stack
            context.pushStackValue(numSquare);
            throw new NegativeNumberException();
        }

        // Push result onto stack
        context.pushStackValue(Math.sqrt(numSquare));
    }
}
