package ru.nsu.ccfit.petrov.task2.command;

import ru.nsu.ccfit.petrov.task2.util.Variable;
import ru.nsu.ccfit.petrov.task2.Context;
import ru.nsu.ccfit.petrov.task2.command.exception.ArgumentsNumberException;
import ru.nsu.ccfit.petrov.task2.command.exception.ArgumentsFormatException;
import ru.nsu.ccfit.petrov.task2.command.exception.VariableNameException;

import java.util.List;

public class Define
        implements Command
{
    private String name;
    private Double value;



    @Override
    public void setArgs(List<String> args)
    {
        if (args.size() != 2)
            throw new ArgumentsNumberException();

        // Get correct variable name
        if (!Variable.isCorrectVariableName(args.get(0)))
            throw new VariableNameException();
        name = args.get(0);

        // Get correct variable value
        try
        {
            value = Double.valueOf(args.get(1));
        }
        catch (NumberFormatException ex)
        {
            throw new ArgumentsFormatException();
        }
    }

    @Override
    public void run(Context context)
    {
        context.addVariable(name, value);
    }
}
