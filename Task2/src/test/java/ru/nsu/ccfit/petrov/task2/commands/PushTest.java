package ru.nsu.ccfit.petrov.task2.commands;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import ru.nsu.ccfit.petrov.task2.commands.exception.ArgumentsNumberException;
import ru.nsu.ccfit.petrov.task2.context.Context;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

class PushTest
{
    private final Context context = new Context(System.out);
    private final Push pushCmd = new Push();

    private static Stream <Arguments> pushValueTestArgs()
    {
        return Stream.of(
                Arguments.of(new ArrayList <>(List.of("1.57")),
                             1.57),
                Arguments.of(new ArrayList<>(List.of("1")),
                             1.0),
                Arguments.of(new ArrayList<>(List.of("-3.54")),
                             -3.54),
                Arguments.of(new ArrayList<>(List.of("3.5E-01")),
                             0.35),
                Arguments.of(new ArrayList<>(List.of(".5")),
                             0.5)
        );
    }

    @ParameterizedTest
    @MethodSource("pushValueTestArgs")
    void pushValueTest(ArrayList<String> args, Double expected)
    {
        Assertions.assertDoesNotThrow(() -> pushCmd.run(args, context));
        Assertions.assertEquals(expected,
                                context.popStackValue());
    }

    private static Stream <Arguments> pushVariableTestArgs()
    {
        return Stream.of(
                Arguments.of(new AbstractMap.SimpleEntry<>("a", 1.0)),
                Arguments.of(new AbstractMap.SimpleEntry<>("a", 3.14)),
                Arguments.of(new AbstractMap.SimpleEntry<>("a", 3.14))
        );
    }

    @ParameterizedTest
    @MethodSource("pushVariableTestArgs")
    void pushVariableTest(Map.Entry<String, Double> variable)
    {
        context.addVariable(variable.getKey(), 
                            variable.getValue());
        Assertions.assertDoesNotThrow(() -> pushCmd.run(List.of(variable.getKey()),
                                                        context));
        Assertions.assertEquals(context.getVariable(variable.getKey()),
                                context.popStackValue());
    }

    private static Stream<Arguments> exceptionTestArgs()
    {
        return Stream.of(
                Arguments.of(new ArrayList<>(),
                             ArgumentsNumberException.class),
                Arguments.of(new ArrayList<>(List.of("a", "1.986")),
                             ArgumentsNumberException.class)
        );
    }

    @ParameterizedTest
    @MethodSource("exceptionTestArgs")
    void exceptionTest(ArrayList<String> args, Class<? extends Throwable> exceptionClass)
    {
        Assertions.assertThrows(exceptionClass,
                                () -> pushCmd.run(args, context));
    }
}