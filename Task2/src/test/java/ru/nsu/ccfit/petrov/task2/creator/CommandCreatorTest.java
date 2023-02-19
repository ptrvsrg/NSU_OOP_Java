package ru.nsu.ccfit.petrov.task2.creator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import ru.nsu.ccfit.petrov.task2.commands.*;
import ru.nsu.ccfit.petrov.task2.creator.exception.CommandNotCreatedException;

import java.util.stream.Stream;

class CommandCreatorTest
{
    CommandCreator cmdCreator = new CommandCreator();

    private static Stream<Arguments> createTestArgs()
    {
        return Stream.of(
                Arguments.of("+", Addition.class),
                Arguments.of("-", Subtraction.class),
                Arguments.of("*", Multiplication.class),
                Arguments.of("/", Division.class),
                Arguments.of("POP", Pop.class),
                Arguments.of("PUSH", Push.class),
                Arguments.of("SQRT", SquareRoot.class),
                Arguments.of("PRINT", Print.class),
                Arguments.of("DEFINE", Define.class),
                Arguments.of("pop", Pop.class),
                Arguments.of("push", Push.class),
                Arguments.of("sqrt", SquareRoot.class),
                Arguments.of("print", Print.class),
                Arguments.of("define", Define.class)
        );
    }

    @ParameterizedTest
    @MethodSource("createTestArgs")
    public void createTest(String cmdName, Class<? extends Command> cmdClass)
    {
        Assertions.assertDoesNotThrow(() -> Assertions.assertEquals(cmdCreator.create(cmdName).getClass(),
                                                                    cmdClass));
    }

    private static Stream<Arguments> exceptionTestArgs()
    {
        return Stream.of(
                Arguments.of(null, CommandNotCreatedException.class),
                Arguments.of("", CommandNotCreatedException.class),
                Arguments.of("%", CommandNotCreatedException.class),
                Arguments.of("DELETE", CommandNotCreatedException.class)
        );
    }

    @ParameterizedTest
    @MethodSource("exceptionTestArgs")
    public void exceptionTest(String cmdName,
                              Class<? extends Throwable> exceptionClass)
    {
        Assertions.assertThrows(exceptionClass,
                                () -> cmdCreator.create(cmdName));
    }
}