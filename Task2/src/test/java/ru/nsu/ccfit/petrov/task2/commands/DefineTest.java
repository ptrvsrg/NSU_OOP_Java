package ru.nsu.ccfit.petrov.task2.commands;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import ru.nsu.ccfit.petrov.task2.commands.exception.ArgumentsNumberException;
import ru.nsu.ccfit.petrov.task2.context.Context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

class DefineTest
{
    private final Context context = new Context(System.out);
    private final Define defineCmd = new Define();

    private static Stream<Arguments> defineTestArgs() {
        return Stream.of(Arguments.of(new ArrayList<>(List.of("a",
                                                              "1.57")),
                                      new HashMap<>(Map.of("a",
                                                           1.57))),
                         Arguments.of(new ArrayList<>(List.of("a_1",
                                                              "1.57")),
                                      new HashMap<>(Map.of("a_1",
                                                           1.57))),
                         Arguments.of(new ArrayList<>(List.of("a",
                                                              "1")),
                                      new HashMap<>(Map.of("a",
                                                           1.0))),
                         Arguments.of(new ArrayList<>(List.of("a",
                                                              "-1.57")),
                                      new HashMap<>(Map.of("a",
                                                           -1.57))),
                         Arguments.of(new ArrayList<>(List.of("a",
                                                              "3.5E-01")),
                                      new HashMap<>(Map.of("a",
                                                           0.35))),
                         Arguments.of(new ArrayList<>(List.of("a",
                                                              ".5")),
                                      new HashMap<>(Map.of("a",
                                                           0.5))));
    }

    @ParameterizedTest
    @MethodSource("defineTestArgs")
    void defineTest(ArrayList<String> args, HashMap<String, Double> variables) {
        Assertions.assertDoesNotThrow(() -> defineCmd.run(args,
                                                          context));

        for (Map.Entry<String, Double> expected : variables.entrySet())
            Assertions.assertEquals(expected.getValue(),
                                    context.getVariable(expected.getKey()));
    }

    private static Stream<Arguments> exceptionTestArgs() {
        return Stream.of(Arguments.of(new ArrayList<>()),
                         Arguments.of(new ArrayList<>(List.of("a"))),
                         Arguments.of(new ArrayList<>(List.of("a",
                                                              "1.986",
                                                              "b"))),
                         Arguments.of(new ArrayList<>(List.of("a",
                                                              "1.986",
                                                              "b",
                                                              "32.532"))));
    }

    @ParameterizedTest
    @MethodSource("exceptionTestArgs")
    void exceptionTest(ArrayList<String> args) {
        Assertions.assertThrows(ArgumentsNumberException.class,
                                () -> defineCmd.run(args,
                                                    context));
    }
}