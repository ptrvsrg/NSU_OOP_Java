package ru.nsu.ccfit.petrov.task2.command;

import ru.nsu.ccfit.petrov.task2.Context;

import java.util.List;

public interface Command
{
    void setArgs(List <String> args);
    void run(Context context);
}
