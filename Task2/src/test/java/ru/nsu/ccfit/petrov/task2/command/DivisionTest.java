package ru.nsu.ccfit.petrov.task2.command;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import ru.nsu.ccfit.petrov.task2.Context;
import ru.nsu.ccfit.petrov.task2.exception.EnoughStackValuesException;
import ru.nsu.ccfit.petrov.task2.exception.ArgumentsNumberException;
import ru.nsu.ccfit.petrov.task2.exception.DivisionByZeroException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

class DivisionTest
{
    private final Context context = new Context(System.out);
    private final Division divisionCmd = new Division();

    private static Stream <Arguments> divisionTestArgs()
    {
        return Stream.of(
                Arguments.of(23.54, 1.23),
                Arguments.of(23.54, -1.23),
                Arguments.of(-23.54, 1.23),
                Arguments.of(-23.54, -1.23)
        );
    }

    @ParameterizedTest
    @MethodSource("divisionTestArgs")
    void divisionTest(Double dividend, Double divisor)
    {
        context.pushCalculatingValue(dividend);
        context.pushCalculatingValue(divisor);
        Assertions.assertDoesNotThrow(() ->
                                      {
                                          divisionCmd.setArgs(new ArrayList <>());
                                          divisionCmd.run(context);
                                      });

        Assertions.assertEquals(dividend / divisor,
                                context.popCalculatingValue());
    }

    @Test
    void exceptionTest()
    {
        Assertions.assertThrows(ArgumentsNumberException.class,
                                () ->
                                {
                                    divisionCmd.setArgs(new ArrayList<>(List.of("1.34")));
                                    divisionCmd.run(context);
                                }
        );

        Assertions.assertThrows(EnoughStackValuesException.class,
                                () ->
                                {
                                    divisionCmd.setArgs(new ArrayList<>());
                                    context.pushCalculatingValue(12.34);
                                    divisionCmd.run(context);
                                }
        );

        Assertions.assertThrows(DivisionByZeroException.class,
                                () ->
                                {
                                    divisionCmd.setArgs(new ArrayList<>());
                                    context.pushCalculatingValue(431.12);
                                    context.pushCalculatingValue(0.0);
                                    divisionCmd.run(context);
                                }
        );
    }
}