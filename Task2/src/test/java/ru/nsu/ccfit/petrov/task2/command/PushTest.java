package ru.nsu.ccfit.petrov.task2.command;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import ru.nsu.ccfit.petrov.task2.Context;
import ru.nsu.ccfit.petrov.task2.command.exception.ArgumentsNumberException;
import ru.nsu.ccfit.petrov.task2.command.exception.ArgumentsFormatException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

class PushTest
{
    private final Context context = new Context();
    private final Push pushCmd = new Push();

    private static Stream <Arguments> pushTestArgs()
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
    @MethodSource("pushTestArgs")
    void pushTest(ArrayList<String> args, Double expected)
    {
        Assertions.assertDoesNotThrow(() ->
                                      {
                                          pushCmd.setArgs(args);
                                          pushCmd.run(context);
                                      });

        Assertions.assertEquals(expected,
                                context.popCalculatingValue());
    }

    private static Stream<Arguments> exceptionTestArgs()
    {
        return Stream.of(
                Arguments.of(new ArrayList<>(),
                             ArgumentsNumberException.class),
                Arguments.of(new ArrayList<>(List.of("a", "1.986")),
                             ArgumentsNumberException.class),
                Arguments.of(new ArrayList<>(List.of("a-1")),
                             ArgumentsFormatException.class),
                Arguments.of(new ArrayList<>(List.of("1.2F-01")),
                             ArgumentsFormatException.class),
                Arguments.of(new ArrayList<>(List.of("1.2az")),
                             ArgumentsFormatException.class)
        );
    }

    @ParameterizedTest
    @MethodSource("exceptionTestArgs")
    void exceptionTest(ArrayList<String> args, Class<? extends Throwable> exceptionClass)
    {
        Assertions.assertThrows(exceptionClass,
                                () ->
                                {
                                    pushCmd.setArgs(args);
                                    pushCmd.run(context);
                                }
        );
    }
}