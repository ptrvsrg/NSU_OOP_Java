package ru.nsu.fit.oop.task2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
import java.util.stream.Stream;

class ContextTest
{
    private final Context context = new Context();

    private static Stream <Arguments> testStackArgs()
    {
        return Stream.of(
                Arguments.of(new ArrayList<>()),
                Arguments.of(new ArrayList <>(List.of(1.1, 2.2, 3.3, 4.4, 5.5)))
        );
    }

    private static Stream <Arguments> testMapArgs()
    {
        return Stream.of(
                Arguments.of(new HashMap<>()),
                Arguments.of(new HashMap <>(Map.of("a", 1.1, "b", 2.2, "c", 3.3)))
        );
    }

    @ParameterizedTest
    @MethodSource("testStackArgs")
    void testStack(ArrayList<Double> calculatingValues)
    {
        // Empty stack doesn't throw an exception
        Assertions.assertDoesNotThrow(context::popCalculatingValue);

        for (Double calculatingValue : calculatingValues)
            context.pushCalculatingValue(calculatingValue);

        Collections.reverse(calculatingValues);
        for (Double expected : calculatingValues)
        {
            Assertions.assertEquals(expected,
                                    context.peekCalculatingValue());
            Assertions.assertEquals(expected,
                                    context.popCalculatingValue());
        }

        // Empty stack return null
        Assertions.assertNull(context.peekCalculatingValue());
        Assertions.assertNull(context.popCalculatingValue());
    }

    @ParameterizedTest
    @MethodSource("testMapArgs")
    void testMap(Map <String, Double> variables)
    {
        for (Map.Entry <String, Double> variable : variables.entrySet())
            context.addVariable(variable.getKey(), variable.getValue());

        for (int i = 0; i < 3; ++i)
            for (Map.Entry <String, Double> expected : variables.entrySet())
                Assertions.assertEquals(expected.getValue(),
                                        context.getVariable(expected.getKey()));
    }
}