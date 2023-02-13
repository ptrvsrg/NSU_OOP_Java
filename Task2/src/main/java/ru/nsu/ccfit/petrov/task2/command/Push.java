package ru.nsu.ccfit.petrov.task2.command;

import ru.nsu.ccfit.petrov.task2.util.Variable;
import ru.nsu.ccfit.petrov.task2.Context;
import ru.nsu.ccfit.petrov.task2.exception.ArgumentsNumberException;
import ru.nsu.ccfit.petrov.task2.exception.ArgumentsFormatException;

import java.util.List;

public class Push
    implements Command
{
    @Override
    public void run(List <String> args, Context context)
    {
        // Check args count
        if (args.size() != 1)
            throw new ArgumentsNumberException();

        // Get variable name or number value
        String value = args.get(0);

        // Push number or variable value onto stack
        try
        {
            if (Variable.isCorrectVariableName(value))
                context.pushCalculatingValue(context.getVariable(value));
            else
                context.pushCalculatingValue(Double.valueOf(value));
        }
        catch (NumberFormatException ex)
        {
            throw new ArgumentsFormatException();
        }
    }
}
