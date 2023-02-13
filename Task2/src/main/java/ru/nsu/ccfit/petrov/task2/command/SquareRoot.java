package ru.nsu.ccfit.petrov.task2.command;

import ru.nsu.ccfit.petrov.task2.exception.EnoughStackValuesException;
import ru.nsu.ccfit.petrov.task2.exception.NegativeNumberException;
import ru.nsu.ccfit.petrov.task2.Context;
import ru.nsu.ccfit.petrov.task2.exception.ArgumentsNumberException;

import java.util.List;

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
        Double num = context.peekCalculatingValue();

        if (num == null)
            throw new EnoughStackValuesException();
        if (num < 0)
            throw new NegativeNumberException();

        // Pop element from stack
        context.popCalculatingValue();

        // Push result onto stack
        context.pushCalculatingValue(Math.sqrt(num));
    }
}
