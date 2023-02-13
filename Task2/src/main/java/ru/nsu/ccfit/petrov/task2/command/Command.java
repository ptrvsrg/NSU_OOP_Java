package ru.nsu.ccfit.petrov.task2.command;

import ru.nsu.ccfit.petrov.task2.Context;

import java.util.List;

public interface Command
{
    void run(List <String> args, Context context);
}
