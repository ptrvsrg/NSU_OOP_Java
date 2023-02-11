package ru.nsu.fit.oop.task2.command;

import ru.nsu.fit.oop.task2.Context;

import java.util.List;

public interface Command
{
    void setArgs(List <String> args);
    void run(Context context);
}
