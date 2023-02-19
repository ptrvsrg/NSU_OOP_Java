package ru.nsu.ccfit.petrov.task2.commands;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import ru.nsu.ccfit.petrov.task2.commands.exception.ArgumentsNumberException;
import ru.nsu.ccfit.petrov.task2.context.Context;
import ru.nsu.ccfit.petrov.task2.context.exception.EmptyStackException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

class SubtractionTest
{
    private final Context context = new Context(System.out);
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
        context.pushStackValue(minuend);
        context.pushStackValue(subtrahend);
        Assertions.assertDoesNotThrow(() -> subtractionCmd.run(new ArrayList <>(), context));
        Assertions.assertEquals(minuend - subtrahend,
                                context.popStackValue());
    }

    @Test
    void exceptionTest()
    {
        Assertions.assertThrows(ArgumentsNumberException.class,
                                () -> subtractionCmd.run(new ArrayList<>(List.of("1.34")), context));

        Assertions.assertThrows(EmptyStackException.class,
                                () -> subtractionCmd.run(new ArrayList<>(), context));

        context.pushStackValue(12.43);
        Assertions.assertThrows(EmptyStackException.class,
                                () -> subtractionCmd.run(new ArrayList<>(), context));
        Assertions.assertEquals(context.popStackValue(),
                                12.43);
        Assertions.assertThrows(EmptyStackException.class,
                                context::popStackValue);
    }
}