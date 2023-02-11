package ru.nsu.fit.oop.task2.command;

import ru.nsu.fit.oop.task2.Context;
import ru.nsu.fit.oop.task2.command.exception.ArgumentsNumberException;
import ru.nsu.fit.oop.task2.command.exception.ArgumentsFormatException;
import ru.nsu.fit.oop.task2.util.Variable;

import java.util.List;

public class Push
    implements Command
{
    private String value;

    @Override
    public void setArgs(List <String> args)
    {
        if (args.size() != 1)
            throw new ArgumentsNumberException();

        value = args.get(0);
    }

    @Override
    public void run(Context context)
    {
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
