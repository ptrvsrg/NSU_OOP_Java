package ru.nsu.ccfit.petrov.task2.commands;

import ru.nsu.ccfit.petrov.task2.commands.exception.ArgumentsNumberException;
import ru.nsu.ccfit.petrov.task2.context.Context;
import ru.nsu.ccfit.petrov.task2.context.exception.EmptyStackException;

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
        Double multiplier2 = context.popStackValue();

        // Pop multiplier1 from stack
        Double multiplier1;
        try
        {
            multiplier1 = context.popStackValue();
        }
        catch (EmptyStackException ex)
        {
            // Push divisor onto stack
            context.pushStackValue(multiplier2);
            throw ex;
        }

        // Push result onto stack
        context.pushStackValue(multiplier1 * multiplier2);
    }
}
