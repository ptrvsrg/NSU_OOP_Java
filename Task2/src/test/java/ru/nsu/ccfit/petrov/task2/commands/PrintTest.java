package ru.nsu.ccfit.petrov.task2.commands;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.nsu.ccfit.petrov.task2.commands.exception.ArgumentsNumberException;
import ru.nsu.ccfit.petrov.task2.context.Context;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

class PrintTest
{
    private final Print printCmd = new Print();
    private final Context context = new Context(new ByteArrayOutputStream());

    @Test
    void printTest()
    {
        context.pushStackValue(1.1);
        Assertions.assertDoesNotThrow(() -> printCmd.run(new ArrayList <>(), context));
        Assertions.assertEquals(context.getOut().toString(),
                                "1.1\n");
    }

    @Test
    void exceptionTest()
    {
        Assertions.assertThrows(ArgumentsNumberException.class,
                                () -> printCmd.run(new ArrayList<>(List.of("a")), context));
    }
}