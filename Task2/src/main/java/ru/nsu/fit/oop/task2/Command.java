package ru.nsu.fit.oop.task2;

import java.io.IOException;
import java.util.List;

public interface Command
{
    void setArgs(List <String> args);
    void run(Context context)
            throws IOException;
}
