package ru.nsu.fit.oop.task2.command;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import ru.nsu.fit.oop.task2.Context;
import ru.nsu.fit.oop.task2.command.exception.ArgumentsNumberException;

import java.util.ArrayList;
import java.util.List;

class PopTest
{
    private final Context context = new Context();
    private final Pop popCmd = new Pop();

    @Test
    void popTest()
    {
        context.pushCalculatingValue(1.1);
        context.pushCalculatingValue(2.2);
        List<String> args = new ArrayList<>();

        // Non-empty stack
        Assertions.assertDoesNotThrow(() ->
                                      {
                                          popCmd.setArgs(args);
                                          popCmd.run(context);
                                      });
        Assertions.assertEquals(1.1,
                                context.popCalculatingValue());

        // Empty stack
        Assertions.assertDoesNotThrow(() -> popCmd.run(context));
        Assertions.assertNull(context.popCalculatingValue());
    }

    @Test
    void exceptionTest()
    {
        List<String> args = new ArrayList<>(List.of("a"));
        Assertions.assertThrows(ArgumentsNumberException.class,
                                () ->
                                {
                                    popCmd.setArgs(args);
                                    popCmd.run(context);
                                }
        );
    }
}