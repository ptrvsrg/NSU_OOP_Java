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

class AdditionTest
{
    private final Context context = new Context();
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
        context.pushCalculatingValue(addend1);
        context.pushCalculatingValue(addend2);
        Assertions.assertDoesNotThrow(() ->
                                      {
                                          additionCmd.setArgs(new ArrayList<>());
                                          additionCmd.run(context);
                                      });

        Assertions.assertEquals(addend1 + addend2,
                                context.popCalculatingValue());
    }

    @Test
    void exceptionTest()
    {
        Assertions.assertThrows(ArgumentsNumberException.class,
                                () ->
                                {
                                    additionCmd.setArgs(new ArrayList<>(List.of("1.34")));
                                    additionCmd.run(context);
                                }
        );

        Assertions.assertThrows(EnoughStackValuesException.class,
                                () ->
                                {
                                    additionCmd.setArgs(new ArrayList<>());
                                    context.pushCalculatingValue(12.23);
                                    additionCmd.run(context);
                                }
        );
    }
}