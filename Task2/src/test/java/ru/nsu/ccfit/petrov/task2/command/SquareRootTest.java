package ru.nsu.ccfit.petrov.task2.command;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import ru.nsu.ccfit.petrov.task2.Context;
import ru.nsu.ccfit.petrov.task2.exception.ArgumentsNumberException;
import ru.nsu.ccfit.petrov.task2.exception.EnoughStackValuesException;
import ru.nsu.ccfit.petrov.task2.exception.NegativeNumberException;

import java.util.ArrayList;
import java.util.List;

class SquareRootTest
{
    private final Context context = new Context(System.out);
    private final SquareRoot squareRootCmd = new SquareRoot();

    @Test
    void squareRootTest()
    {
        double num = 213.9402;
        context.pushCalculatingValue(num);
        Assertions.assertDoesNotThrow(() -> squareRootCmd.run(new ArrayList <>(), context));
        Assertions.assertEquals(Math.sqrt(num),
                                context.popCalculatingValue());
    }

    @Test
    void exceptionTest()
    {
        Assertions.assertThrows(ArgumentsNumberException.class,
                                () -> squareRootCmd.run(new ArrayList<>(List.of("1.34")), context));
        Assertions.assertThrows(EnoughStackValuesException.class,
                                () -> squareRootCmd.run(new ArrayList<>(), context));
        context.pushCalculatingValue(-431.12);
        Assertions.assertThrows(NegativeNumberException.class,
                                () -> squareRootCmd.run(new ArrayList<>(), context));
    }
}