package ru.nsu.ccfit.petrov.task2.commands;

import ru.nsu.ccfit.petrov.task2.commands.exception.ArgumentsNumberException;
import ru.nsu.ccfit.petrov.task2.context.Context;

import java.util.List;

/**
 * Class {@code Define} implements command to add variables to calculator context
 */
public class Define
        implements Command
{
    @Override
    public void run(List<String> args, Context context) {
        // Check args count
        if (args.size() != 2)
            throw new ArgumentsNumberException();

        // Add variable to context
        context.addVariable(args.get(0),
                            args.get(1));
    }
}
