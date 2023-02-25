package ru.nsu.ccfit.petrov.task2.commands;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import ru.nsu.ccfit.petrov.task2.commands.exception.ArgumentsNumberException;
import ru.nsu.ccfit.petrov.task2.commands.exception.NegativeNumberException;
import ru.nsu.ccfit.petrov.task2.context.Context;
import ru.nsu.ccfit.petrov.task2.context.exception.EmptyStackException;

import java.util.ArrayList;
import java.util.List;

class SquareRootTest
{
    private final Context context = new Context(System.out);
    private final SquareRoot squareRootCmd = new SquareRoot();

    @Test
    void squareRootTest() {
        double num = 213.9402;
        context.pushStackValue(num);
        Assertions.assertDoesNotThrow(() -> squareRootCmd.run(new ArrayList<>(),
                                                              context));
        Assertions.assertEquals(Math.sqrt(num),
                                context.popStackValue());
    }

    @Test
    void exceptionTest() {
        Assertions.assertThrows(ArgumentsNumberException.class,
                                () -> squareRootCmd.run(new ArrayList<>(List.of("1.34")),
                                                        context));

        Assertions.assertThrows(EmptyStackException.class,
                                () -> squareRootCmd.run(new ArrayList<>(),
                                                        context));

        context.pushStackValue(-431.12);
        Assertions.assertThrows(NegativeNumberException.class,
                                () -> squareRootCmd.run(new ArrayList<>(),
                                                        context));
        Assertions.assertEquals(context.popStackValue(),
                                -431.12);
        Assertions.assertThrows(EmptyStackException.class,
                                context::popStackValue);
    }
}