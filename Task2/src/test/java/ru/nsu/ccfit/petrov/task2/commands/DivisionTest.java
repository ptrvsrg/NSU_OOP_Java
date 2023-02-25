package ru.nsu.ccfit.petrov.task2.commands;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import ru.nsu.ccfit.petrov.task2.commands.exception.ArgumentsNumberException;
import ru.nsu.ccfit.petrov.task2.commands.exception.DivisionByZeroException;
import ru.nsu.ccfit.petrov.task2.context.Context;
import ru.nsu.ccfit.petrov.task2.context.exception.EmptyStackException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

class DivisionTest
{
    private final Context context = new Context(System.out);
    private final Division divisionCmd = new Division();

    private static Stream<Arguments> divisionTestArgs() {
        return Stream.of(Arguments.of(23.54,
                                      1.23),
                         Arguments.of(23.54,
                                      -1.23),
                         Arguments.of(-23.54,
                                      1.23),
                         Arguments.of(-23.54,
                                      -1.23));
    }

    @ParameterizedTest
    @MethodSource("divisionTestArgs")
    void divisionTest(Double dividend, Double divisor) {
        context.pushStackValue(dividend);
        context.pushStackValue(divisor);
        Assertions.assertDoesNotThrow(() -> divisionCmd.run(new ArrayList<>(),
                                                            context));
        Assertions.assertEquals(dividend / divisor,
                                context.popStackValue());
    }

    @Test
    void exceptionTest() {
        Assertions.assertThrows(ArgumentsNumberException.class,
                                () -> divisionCmd.run(new ArrayList<>(List.of("1.34")),
                                                      context));

        Assertions.assertThrows(EmptyStackException.class,
                                () -> divisionCmd.run(new ArrayList<>(),
                                                      context));

        context.pushStackValue(12.34);
        Assertions.assertThrows(EmptyStackException.class,
                                () -> divisionCmd.run(new ArrayList<>(),
                                                      context));
        Assertions.assertEquals(12.34,
                                context.popStackValue());
        Assertions.assertThrows(EmptyStackException.class,
                                context::popStackValue);

        context.pushStackValue(12.34);
        context.pushStackValue(0.0);
        Assertions.assertThrows(DivisionByZeroException.class,
                                () -> divisionCmd.run(new ArrayList<>(),
                                                      context));
    }
}