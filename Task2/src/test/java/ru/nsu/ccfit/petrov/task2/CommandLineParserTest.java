package ru.nsu.ccfit.petrov.task2;

import org.apache.commons.cli.MissingArgumentException;
import org.apache.commons.cli.UnrecognizedOptionException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class CommandLineParserTest
{
    private final CommandLineParser commandLineParser = new CommandLineParser();

    private static Stream <Arguments> checkInputOutputArgs()
    {
        return Stream.of(Arguments.of(new String[]{},
                                      null,
                                      null),
                         Arguments.of(new String[]{"-i", "in_path"},
                                      "in_path",
                                      null),
                         Arguments.of(new String[]{"-o", "out_path"},
                                      null,
                                      "out_path"),
                         Arguments.of(new String[]{"--input", "in_path"},
                                      "in_path",
                                      null),
                         Arguments.of(new String[]{"--output", "out_path"},
                                      null,
                                      "out_path"),
                         Arguments.of(new String[]{"--input", "in_path", "--output", "out_path"},
                                      "in_path",
                                      "out_path")
        );
    }

    @ParameterizedTest
    @MethodSource("checkInputOutputArgs")
    void checkInputOutput(String[] args,
                        String expectedInput,
                        String expectedOutput)
    {
        Assertions.assertDoesNotThrow(
                () ->
                {
                    Assertions.assertTrue(commandLineParser.parse(args));
                    Assertions.assertEquals(commandLineParser.getInput(),
                                            expectedInput);
                    Assertions.assertEquals(commandLineParser.getOutput(),
                                            expectedOutput);
                }
        );
    }

    private static Stream <Arguments> checkHelpArgs()
    {
        return Stream.of(Arguments.of((Object) new String[]{"-h"}),
                         Arguments.of((Object) new String[]{"--help"})
        );
    }

    @ParameterizedTest
    @MethodSource("checkHelpArgs")
    void checkHelp(String[] args)
    {
        Assertions.assertDoesNotThrow( () -> Assertions.assertFalse(commandLineParser.parse(args)) );
    }

    private static Stream <Arguments> checkExceptionArgs()
    {
        return Stream.of(Arguments.of(new String[]{"-c"}, UnrecognizedOptionException.class),
                         Arguments.of(new String[]{"--mode"}, UnrecognizedOptionException.class),
                         Arguments.of(new String[]{"--input"}, MissingArgumentException.class),
                         Arguments.of(new String[]{"--output"}, MissingArgumentException.class)
        );
    }

    @ParameterizedTest
    @MethodSource("checkExceptionArgs")
    void checkException(String[] args, Class<? extends Throwable> exception)
    {
        Assertions.assertThrows(exception, () -> Assertions.assertFalse(commandLineParser.parse(args)));
    }
}