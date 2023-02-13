package ru.nsu.ccfit.petrov.task2.command;

import ru.nsu.ccfit.petrov.task2.exception.EnoughStackValuesException;
import ru.nsu.ccfit.petrov.task2.Context;
import ru.nsu.ccfit.petrov.task2.exception.ArgumentsNumberException;

import java.util.List;

public class Addition
    implements Command
{
    @Override
    public void run(List <String> args, Context context)
    {
        // Check args count
        if (!args.isEmpty())
            throw new ArgumentsNumberException();

        // Pop addend2 from stack
        if (context.peekCalculatingValue() == null)
            throw new EnoughStackValuesException();
        Double addend2 = context.popCalculatingValue();

        // Pop addend1 from stack
        if (context.peekCalculatingValue() == null)
        {
            // Push addend2 onto stack
            context.pushCalculatingValue(addend2);
            throw new EnoughStackValuesException();
        }
        Double addend1 = context.popCalculatingValue();

        // Push result onto stack
        context.pushCalculatingValue(addend1 + addend2);
    }
}
