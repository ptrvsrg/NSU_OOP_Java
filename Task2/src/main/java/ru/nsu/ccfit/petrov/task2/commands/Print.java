package ru.nsu.ccfit.petrov.task2.commands;

import ru.nsu.ccfit.petrov.task2.commands.exception.ArgumentsNumberException;
import ru.nsu.ccfit.petrov.task2.context.Context;

import java.util.List;

/**
 * Class {@code Print} implements command to print top of stack of calculator context
 */
public class Print
        implements Command
{
    @Override
    public void run(List<String> args, Context context) {
        // Check args count
        if (!args.isEmpty())
            throw new ArgumentsNumberException();

        // Print extracted value
        context.println(context.peekStackValue());
    }
}
