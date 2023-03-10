package ru.nsu.ccfit.petrov.task2.commands;

import ru.nsu.ccfit.petrov.task2.commands.exception.ArgumentsNumberException;
import ru.nsu.ccfit.petrov.task2.context.Context;

import java.util.List;

/**
 * Class {@code Pop} implements command to pop values from stack of calculator context
 */
public class Pop
        implements Command
{
    @Override
    public void run(List<String> args, Context context) {
        // Check args count
        if (!args.isEmpty())
            throw new ArgumentsNumberException();

        // Pop value from stack
        context.popStackValue();
    }
}
