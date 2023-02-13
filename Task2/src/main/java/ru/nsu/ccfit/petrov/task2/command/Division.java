package ru.nsu.ccfit.petrov.task2.command;

import ru.nsu.ccfit.petrov.task2.Context;
import ru.nsu.ccfit.petrov.task2.exception.EnoughStackValuesException;
import ru.nsu.ccfit.petrov.task2.exception.ArgumentsNumberException;
import ru.nsu.ccfit.petrov.task2.exception.DivisionByZeroException;

import java.util.List;

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
        if (context.peekCalculatingValue() == null)
            throw new EnoughStackValuesException();
        Double divisor = context.popCalculatingValue();

        // Pop dividend from stack
        if (Math.abs(divisor) < 1.0E-09)
        {
            // Push divisor onto stack
            context.pushCalculatingValue(divisor);
            throw new DivisionByZeroException();
        }
        if (context.peekCalculatingValue() == null)
        {
            // Push divisor onto stack
            context.pushCalculatingValue(divisor);
            throw new EnoughStackValuesException();
        }
        Double dividend = context.popCalculatingValue();

        // Push result onto stack
        context.pushCalculatingValue(dividend / divisor);
    }
}
