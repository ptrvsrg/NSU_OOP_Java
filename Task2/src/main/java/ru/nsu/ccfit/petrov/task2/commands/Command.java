package ru.nsu.ccfit.petrov.task2.commands;

import ru.nsu.ccfit.petrov.task2.context.Context;

import java.util.List;

/**
 * Root interface for all commands
 */
public interface Command
{
    /**
     * Run command with given arguments and context
     *
     * @param args    command arguments
     * @param context calculator context
     */
    void run(List <String> args, Context context);
}
