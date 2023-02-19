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

class MultiplicationTest
{
    private final Context context = new Context(System.out);
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
        context.pushStackValue(multiplier1);
        context.pushStackValue(multiplier2);
        Assertions.assertDoesNotThrow(() -> multiplicationCmd.run(new ArrayList <>(), context));
        Assertions.assertEquals(multiplier1 * multiplier2,
                                context.popStackValue());
    }

    @Test
    void exceptionTest()
    {
        Assertions.assertThrows(ArgumentsNumberException.class,
                                () -> multiplicationCmd.run(new ArrayList<>(List.of("1.34")), context));

        context.pushStackValue(12.42);
        Assertions.assertThrows(EmptyStackException.class,
                                () -> multiplicationCmd.run(new ArrayList<>(), context));
        Assertions.assertEquals(context.popStackValue(),
                                12.42);
        Assertions.assertThrows(EmptyStackException.class,
                                context::popStackValue);
    }
}