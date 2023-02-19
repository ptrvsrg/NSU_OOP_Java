package ru.nsu.ccfit.petrov.task2.commands;

import ru.nsu.ccfit.petrov.task2.commands.exception.ArgumentsNumberException;
import ru.nsu.ccfit.petrov.task2.context.Context;
import ru.nsu.ccfit.petrov.task2.util.Variable;

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
        if (Variable.isCorrectVariableName(value))
            context.pushStackValue(context.getVariable(value));
        else
            context.pushStackValue(Double.valueOf(value));
    }
}
