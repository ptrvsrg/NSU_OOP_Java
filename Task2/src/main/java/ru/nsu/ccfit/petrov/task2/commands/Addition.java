package ru.nsu.ccfit.petrov.task2.commands;

import ru.nsu.ccfit.petrov.task2.commands.exception.ArgumentsNumberException;
import ru.nsu.ccfit.petrov.task2.context.Context;
import ru.nsu.ccfit.petrov.task2.context.exception.EmptyStackException;

import java.util.List;

/**
 * Class {@code Addition} implements addition operation
 */
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
        Double addend2 = context.popStackValue();

        // Pop addend1 from stack
        Double addend1;
        try
        {
            addend1 = context.popStackValue();
        }
        catch (EmptyStackException ex)
        {
            // Push addend2 onto stack
            context.pushStackValue(addend2);
            throw ex;
        }

        // Push result onto stack
        context.pushStackValue(addend1 + addend2);
    }
}
