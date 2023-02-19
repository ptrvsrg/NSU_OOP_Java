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

class AdditionTest
{
    private final Context context = new Context(System.out);
    private final Addition additionCmd = new Addition();

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
    void additionTest(Double addend1, Double addend2)
    {
        context.pushStackValue(addend1);
        context.pushStackValue(addend2);
        Assertions.assertDoesNotThrow(() -> additionCmd.run(new ArrayList<>(), context));
        Assertions.assertEquals(addend1 + addend2,
                                context.popStackValue());
    }

    @Test
    void exceptionTest()
    {
        Assertions.assertThrows(ArgumentsNumberException.class,
                                () -> additionCmd.run(new ArrayList<>(List.of("1.34")), context));

        Assertions.assertThrows(EmptyStackException.class,
                                () -> additionCmd.run(new ArrayList<>(), context));

        context.pushStackValue(12.23);
        Assertions.assertThrows(EmptyStackException.class,
                                () -> additionCmd.run(new ArrayList<>(), context));
        Assertions.assertEquals(context.popStackValue(),
                                12.23);
        Assertions.assertThrows(EmptyStackException.class,
                                context::popStackValue);
    }
}