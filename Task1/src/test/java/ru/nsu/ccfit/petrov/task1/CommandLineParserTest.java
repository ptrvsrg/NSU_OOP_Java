package ru.nsu.ccfit.petrov.task1;

import org.apache.commons.cli.MissingArgumentException;
import org.apache.commons.cli.MissingOptionException;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.UnrecognizedOptionException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class CommandLineParserTest
{
    private final CommandLineParser clParser = new CommandLineParser();

    private static Stream <Arguments> parserTestArgs()
    {
        return Stream.of(
                Arguments.of(new String[]{"--input", "path", "--output", "path.csv"},
                             "path",
                             "path.csv"),
                Arguments.of(new String[]{"-i", "path", "--output", "path.csv"},
                             "path",
                             "path.csv"),
                Arguments.of(new String[]{"--input", "path", "-o", "path.csv"},
                             "path",
                             "path.csv"),
                Arguments.of(new String[]{"-i", "path", "-o", "path.csv"},
                             "path",
                             "path.csv")
        );
    }

    @ParameterizedTest
    @MethodSource("parserTestArgs")
    void parserTest(String[] args,
                    String expectedInput,
                    String expectedOutput)
    {
        Assertions.assertDoesNotThrow(() -> Assertions.assertTrue(clParser.parse(args)));
        Assertions.assertEquals(clParser.getInput(),
                                expectedInput);
        Assertions.assertEquals(clParser.getOutput(),
                                expectedOutput);
    }

    private static Stream <Arguments> exceptionTestArgs()
    {
        return Stream.of(
                Arguments.of(new String[]{"--input", "path"},
                             MissingOptionException.class),
                Arguments.of(new String[]{"--output", "path.csv"},
                             MissingOptionException.class),
                Arguments.of(new String[]{"--input"},
                             MissingArgumentException.class),
                Arguments.of(new String[]{"--output"},
                             MissingArgumentException.class),
                Arguments.of(new String[]{"-i", "path"},
                             MissingOptionException.class),
                Arguments.of(new String[]{"-o", "path.csv"},
                             MissingOptionException.class),
                Arguments.of(new String[]{"-i"},
                             MissingArgumentException.class),
                Arguments.of(new String[]{"-o"},
                             MissingArgumentException.class),
                Arguments.of(new String[]{"--config", "path.json"},
                             UnrecognizedOptionException.class),
                Arguments.of(new String[]{"-c", "path.json"},
                             UnrecognizedOptionException.class),
                Arguments.of(new String[]{"--input", "path", "--output", "path.csv", "--config", "path.json"},
                             UnrecognizedOptionException.class)
        );
    }

    @ParameterizedTest
    @MethodSource("exceptionTestArgs")
    void exceptionTest(String[] args,
                       Class <? extends ParseException> exceptionClass)
    {
        Assertions.assertThrows(exceptionClass,
                                () -> clParser.parse(args));
    }

    private static Stream <Arguments> printHelpTestArgs()
    {
        return Stream.of(
                Arguments.of((Object) new String[]{}),
                Arguments.of((Object) new String[]{"--help"}),
                Arguments.of((Object) new String[]{"-h"}),
                Arguments.of((Object) new String[]{"--help", "--input", "path"}),
                Arguments.of((Object) new String[]{"--help", "--input"}),
                Arguments.of((Object) new String[]{"--help", "--config"})
        );
    }

    @ParameterizedTest
    @MethodSource("printHelpTestArgs")
    void printHelpTest(String[] args)
    {
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(clParser.parse(args)));
        Assertions.assertNull(clParser.getInput());
        Assertions.assertNull(clParser.getOutput());
    }
}