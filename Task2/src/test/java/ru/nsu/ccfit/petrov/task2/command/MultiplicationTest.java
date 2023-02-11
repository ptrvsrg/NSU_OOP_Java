package ru.nsu.ccfit.petrov.task2.command;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import ru.nsu.ccfit.petrov.task2.Context;
import ru.nsu.ccfit.petrov.task2.command.exception.EnoughStackValuesException;
import ru.nsu.ccfit.petrov.task2.command.exception.ArgumentsNumberException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

class MultiplicationTest
{
    private final Context context = new Context();
    private final Multiplication multiplicationCmd = new Multiplication();

    private static Stream <Arguments> multiplicationTestArgs()
    {
        return Stream.of(
                Arguments.of(1.23, 23.54),
                Arguments.of(1.23, -23.54),
                Arguments.of(-1.23, 23.54),
                Arguments.of(-1.23, -23.54)
        );
    }

    @ParameterizedTest
    @MethodSource("multiplicationTestArgs")
    void multiplicationTest(Double multiplier1, Double multiplier2)
    {
        context.pushCalculatingValue(multiplier1);
        context.pushCalculatingValue(multiplier2);
        Assertions.assertDoesNotThrow(() ->
                                      {
                                          multiplicationCmd.setArgs(new ArrayList <>());
                                          multiplicationCmd.run(context);
                                      });

        Assertions.assertEquals(multiplier1 * multiplier2,
                                context.popCalculatingValue());
    }

    @Test
    void exceptionTest()
    {
        Assertions.assertThrows(ArgumentsNumberException.class,
                                () ->
                                {
                                    multiplicationCmd.setArgs(new ArrayList<>(List.of("1.34")));
                                    multiplicationCmd.run(context);
                                }
        );

        Assertions.assertThrows(EnoughStackValuesException.class,
                                () ->
                                {
                                    multiplicationCmd.setArgs(new ArrayList<>());
                                    context.pushCalculatingValue(12.42);
                                    multiplicationCmd.run(context);
                                }
        );
    }
}