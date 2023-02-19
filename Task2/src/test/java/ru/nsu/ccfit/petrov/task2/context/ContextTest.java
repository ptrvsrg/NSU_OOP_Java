package ru.nsu.ccfit.petrov.task2.context;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.nsu.ccfit.petrov.task2.context.exception.ArgumentsFormatException;
import ru.nsu.ccfit.petrov.task2.context.exception.EmptyStackException;
import ru.nsu.ccfit.petrov.task2.context.exception.VariableNameException;
import ru.nsu.ccfit.petrov.task2.context.exception.VariableOverwritingException;

import java.util.*;
import java.util.stream.Stream;

class ContextTest
{
    private static final Context context = new Context(System.out);

    @Test
    void stackTest()
    {
        List<Double> calculatingValues = new ArrayList <>(List.of(1.1,
                                                                  2.2,
                                                                  3.3,
                                                                  4.4,
                                                                  5.5));
        Assertions.assertDoesNotThrow(
            () ->
            {
                for (Double calculatingValue : calculatingValues)
                    context.pushStackValue(calculatingValue);

                Collections.reverse(calculatingValues);
                for (Double expected : calculatingValues)
                {
                    Assertions.assertEquals(expected,
                                            context.peekStackValue());
                    Assertions.assertEquals(expected,
                                            context.popStackValue());
                }
            }
        );
    }

    private static Stream <Arguments> mapTestArgs()
    {
        return Stream.of(
                Arguments.of(new HashMap<>()),
                Arguments.of(new HashMap <>(Map.of("a", 1.1421,
                                                   "b", 2,
                                                   "c", 5L)))
        );
    }

    @ParameterizedTest
    @MethodSource("mapTestArgs")
    void mapTest(Map <Object, Object> variables)
    {
        Assertions.assertDoesNotThrow(
            () ->
            {
                for (Map.Entry <Object, Object> variable : variables.entrySet())
                    context.addVariable(variable.getKey(),
                                        variable.getValue());

                for (int i = 0; i < 3; ++i)
                    for (Map.Entry <Object, Object> expected : variables.entrySet())
                        Assertions.assertEquals(Double.valueOf(expected.getValue().toString()),
                                                context.getVariable((String) expected.getKey()));
            }
        );
    }

    private static Stream <Arguments> pushStackTestArgs()
    {
        return Stream.of(
                Arguments.of("number",
                             ArgumentsFormatException.class),
                Arguments.of(null,
                             ArgumentsFormatException.class),
                Arguments.of('a',
                             ArgumentsFormatException.class),
                Arguments.of(new Object(),
                             ArgumentsFormatException.class)
        );
    }

    @ParameterizedTest
    @MethodSource("pushStackTestArgs")
    void pushStackTest(Object value,
                       Class<? extends Throwable> exceptionClass)
    {
        Assertions.assertThrows(exceptionClass,
                                () -> context.pushStackValue(value));
    }

    @Test
    void peekPopStackTest()
    {
        Assertions.assertThrows(EmptyStackException.class,
                                context::peekStackValue);
        Assertions.assertThrows(EmptyStackException.class,
                                context::popStackValue);
    }

    private static Stream <Arguments> addVariableTestArgs()
    {
        context.addVariable("MyVariable", 3.14);
        return Stream.of(
                Arguments.of(new AbstractMap.SimpleEntry<>(null, 12.43),
                             VariableNameException.class),
                Arguments.of(new AbstractMap.SimpleEntry<>("", 12.43),
                             VariableNameException.class),
                Arguments.of(new AbstractMap.SimpleEntry<>("1", 12.43),
                             VariableNameException.class),
                Arguments.of(new AbstractMap.SimpleEntry<>("a@1", 12.43),
                             VariableNameException.class),
                Arguments.of(new AbstractMap.SimpleEntry<>("a_1", "Number"),
                             ArgumentsFormatException.class),
                Arguments.of(new AbstractMap.SimpleEntry<>("a_1", 'a'),
                             ArgumentsFormatException.class),
                Arguments.of(new AbstractMap.SimpleEntry<>("a_1", new Object()),
                             ArgumentsFormatException.class),
                Arguments.of(new AbstractMap.SimpleEntry<>("MyVariable", 3.14),
                             VariableOverwritingException.class)
        );
    }

    @ParameterizedTest
    @MethodSource("addVariableTestArgs")
    void addVariableTest(Map.Entry <Object, Object> variable,
                         Class<? extends Throwable> exceptionClass)
    {
        Assertions.assertThrows(exceptionClass,
                                () -> context.addVariable(variable.getKey(),
                                                          variable.getValue()));
    }
}