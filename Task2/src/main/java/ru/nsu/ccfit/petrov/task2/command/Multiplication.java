package ru.nsu.ccfit.petrov.task2.command;

import ru.nsu.ccfit.petrov.task2.exception.EnoughStackValuesException;
import ru.nsu.ccfit.petrov.task2.Context;
import ru.nsu.ccfit.petrov.task2.exception.ArgumentsNumberException;

import java.util.List;

public class Multiplication
    implements Command
{
    @Override
    public void run(List <String> args, Context context)
    {
        // Check args count
        if (!args.isEmpty())
            throw new ArgumentsNumberException();

        // Pop multiplier2 from stack
        if (context.peekCalculatingValue() == null)
            throw new EnoughStackValuesException();
        Double multiplier2 = context.popCalculatingValue();

        // Pop multiplier1 from stack
        if (context.peekCalculatingValue() == null)
        {
            context.pushCalculatingValue(multiplier2);
            throw new EnoughStackValuesException();
        }
        Double multiplier1 = context.popCalculatingValue();

        // Push result onto stack
        context.pushCalculatingValue(multiplier1 * multiplier2);
    }
}
