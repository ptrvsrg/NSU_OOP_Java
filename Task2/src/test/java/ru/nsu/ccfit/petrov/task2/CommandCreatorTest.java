package ru.nsu.ccfit.petrov.task2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import ru.nsu.ccfit.petrov.task2.command.*;

import java.util.stream.Stream;

class CommandCreatorTest
{
    CommandCreator cmdCreator = new CommandCreator();
    
    private static Stream<Arguments> createTestArgs()
    {
        return Stream.of(
                Arguments.of("POP", Pop.class),
                Arguments.of("PUSH", Push.class),
                Arguments.of("+", Addition.class),
                Arguments.of("-", Subtraction.class),
                Arguments.of("*", Multiplication.class),
                Arguments.of("/", Division.class),
                Arguments.of("SQRT", SquareRoot.class),
                Arguments.of("PRINT", Print.class),
                Arguments.of("DEFINE", Define.class),
                Arguments.of("%", null),
                Arguments.of("LOG", null)
        );
    }
    
    @ParameterizedTest
    @MethodSource("createTestArgs")
    public void createTest(String cmdName, Class<? extends Command> cmdClass)
    {
        if (cmdClass == null)
            Assertions.assertNull(cmdCreator.create(cmdName));
        else
            Assertions.assertEquals(cmdCreator.create(cmdName).getClass(),
                                    cmdClass);
    }
}