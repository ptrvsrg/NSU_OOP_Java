package ru.nsu.ccfit.petrov.task2.command;

import ru.nsu.ccfit.petrov.task2.util.Variable;
import ru.nsu.ccfit.petrov.task2.Context;
import ru.nsu.ccfit.petrov.task2.exception.ArgumentsNumberException;
import ru.nsu.ccfit.petrov.task2.exception.ArgumentsFormatException;
import ru.nsu.ccfit.petrov.task2.exception.VariableNameException;

import java.util.List;

public class Define
        implements Command
{
    @Override
    public void run(List<String> args, Context context)
    {
        // Check args count
        if (args.size() != 2)
            throw new ArgumentsNumberException();

        // Get correct variable name
        if (!Variable.isCorrectVariableName(args.get(0)))
            throw new VariableNameException();
        String name = args.get(0);

        // Get correct variable value
        double value;
        try
        {
            value = Double.parseDouble(args.get(1));
        }
        catch (NumberFormatException ex)
        {
            throw new ArgumentsFormatException();
        }

        // Add variable to context
        context.addVariable(name, value);
    }
}
