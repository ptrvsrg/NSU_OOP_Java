package ru.nsu.ccfit.petrov.task2.command;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import ru.nsu.ccfit.petrov.task2.Context;
import ru.nsu.ccfit.petrov.task2.exception.ArgumentsNumberException;

import java.util.ArrayList;
import java.util.List;

class PopTest
{
    private final Context context = new Context(System.out);
    private final Pop popCmd = new Pop();

    @Test
    void popTest()
    {
        context.pushCalculatingValue(1.1);
        context.pushCalculatingValue(2.2);

        // Non-empty stack
        Assertions.assertDoesNotThrow(() -> popCmd.run(new ArrayList<>(), context));
        Assertions.assertEquals(1.1,
                                context.popCalculatingValue());

        // Empty stack
        Assertions.assertDoesNotThrow(() -> popCmd.run(new ArrayList<>(), context));
        Assertions.assertNull(context.popCalculatingValue());
    }

    @Test
    void exceptionTest()
    {
        Assertions.assertThrows(ArgumentsNumberException.class,
                                () -> popCmd.run(new ArrayList<>(List.of("a")), context));
    }
}