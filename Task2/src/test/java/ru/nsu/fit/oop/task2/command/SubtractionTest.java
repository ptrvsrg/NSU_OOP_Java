package ru.nsu.fit.oop.task2.command;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import ru.nsu.fit.oop.task2.Context;
import ru.nsu.fit.oop.task2.command.exception.ArgumentsNumberException;
import ru.nsu.fit.oop.task2.command.exception.EnoughStackValuesException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

class SubtractionTest
{
    private final Context context = new Context();
    private final Subtraction subtractionCmd = new Subtraction();

    private static Stream <Arguments> additionTestArgs()
    {
        return Stream.of(
                Arguments.of(1.23, 23.54),
                Arguments.of(1.23, -23.54),
                Arguments.of(-1.23, 23.54),
                Arguments.of(-1.23, -23.54)
        );
    }

    @ParameterizedTest
    @MethodSource("additionTestArgs")
    void additionTest(Double minuend, Double subtrahend)
    {
        context.pushCalculatingValue(minuend);
        context.pushCalculatingValue(subtrahend);
        Assertions.assertDoesNotThrow(() ->
                                      {
                                          subtractionCmd.setArgs(new ArrayList <>());
                                          subtractionCmd.run(context);
                                      });

        Assertions.assertEquals(minuend - subtrahend,
                                context.popCalculatingValue());
    }

    @Test
    void exceptionTest()
    {
        Assertions.assertThrows(ArgumentsNumberException.class,
                                () ->
                                {
                                    subtractionCmd.setArgs(new ArrayList<>(List.of("1.34")));
                                    subtractionCmd.run(context);
                                }
        );

        Assertions.assertThrows(EnoughStackValuesException.class,
                                () ->
                                {
                                    subtractionCmd.setArgs(new ArrayList<>());
                                    context.pushCalculatingValue(12.43);
                                    subtractionCmd.run(context);
                                }
        );
    }
}