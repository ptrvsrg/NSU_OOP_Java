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
    private final Context context = new Context();
    private final SquareRoot squareRootCmd = new SquareRoot();

    @Test
    void squareRootTest()
    {
        double num = 213.9402;
        context.pushCalculatingValue(num);
        Assertions.assertDoesNotThrow(() ->
                                      {
                                          squareRootCmd.setArgs(new ArrayList <>());
                                          squareRootCmd.run(context);
                                      });

        Assertions.assertEquals(Math.sqrt(num),
                                context.popCalculatingValue());
    }

    @Test
    void exceptionTest()
    {
        Assertions.assertThrows(ArgumentsNumberException.class,
                                () ->
                                {
                                    squareRootCmd.setArgs(new ArrayList<>(List.of("1.34")));
                                    squareRootCmd.run(context);
                                }
        );

        Assertions.assertThrows(EnoughStackValuesException.class,
                                () ->
                                {
                                    squareRootCmd.setArgs(new ArrayList<>());
                                    squareRootCmd.run(context);
                                }
        );

        Assertions.assertThrows(NegativeNumberException.class,
                                () ->
                                {
                                    squareRootCmd.setArgs(new ArrayList<>());
                                    context.pushCalculatingValue(-431.12);
                                    squareRootCmd.run(context);
                                }
        );
    }
}