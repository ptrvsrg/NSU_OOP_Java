package ru.nsu.ccfit.petrov.task2.commands;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import ru.nsu.ccfit.petrov.task2.commands.exception.ArgumentsNumberException;
import ru.nsu.ccfit.petrov.task2.context.Context;

import java.util.ArrayList;
import java.util.List;

class PopTest
{
    private final Context context = new Context(System.out);
    private final Pop popCmd = new Pop();

    @Test
    void popTest()
    {
        context.pushStackValue(1.1);
        context.pushStackValue(2.2);
        Assertions.assertDoesNotThrow(() -> popCmd.run(new ArrayList<>(), context));
        Assertions.assertEquals(1.1,
                                context.popStackValue());
    }

    @Test
    void exceptionTest()
    {
        Assertions.assertThrows(ArgumentsNumberException.class,
                                () -> popCmd.run(new ArrayList<>(List.of("a")), context));
    }
}