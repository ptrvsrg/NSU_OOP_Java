package ru.nsu.ccfit.petrov.task2.commands;

import ru.nsu.ccfit.petrov.task2.context.Context;

import java.util.List;

public interface Command
{
    void run(List <String> args, Context context);
}
