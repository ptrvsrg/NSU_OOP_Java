package ru.nsu.fit.oop.task2.command;

import ru.nsu.fit.oop.task2.Command;
import ru.nsu.fit.oop.task2.Context;
import ru.nsu.fit.oop.task2.command.exception.ArgumentsNumberException;
import ru.nsu.fit.oop.task2.command.exception.ArgumentsTypeException;
import ru.nsu.fit.oop.task2.command.exception.VariableNameException;
import ru.nsu.fit.oop.task2.util.Variable;

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

        if (!Variable.isCorrectVariableName(args.get(0)))
            throw new VariableNameException();
        name = args.get(0);

        try
        {
            value = Double.valueOf(args.get(1));
        }
        catch (NumberFormatException ex)
        {
            throw new ArgumentsTypeException();
        }
    }

    @Override
    public void run(Context context)
    {
        context.addVariable(name, value);
    }
}
