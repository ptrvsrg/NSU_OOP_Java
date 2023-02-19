package ru.nsu.ccfit.petrov.task2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

class CommandParserTest
{
    private static Stream <Arguments> parseArgs() {
        return Stream.of(
                Arguments.of(null, null, new ArrayList<String>()),
                Arguments.of("", null, new ArrayList<String>()),
                Arguments.of("  ", null, new ArrayList<String>()),
                Arguments.of("#PRINT", null, new ArrayList<String>()),
                Arguments.of("PRINT", "PRINT", new ArrayList<String>()),
                Arguments.of("PRINT #PRINT", "PRINT", new ArrayList<String>()),
                Arguments.of("PUSH a", "PUSH", List.of("a")),
                Arguments.of("DEFINE a 4", "DEFINE", List.of("a", "4")),
                Arguments.of("DEFINE a 4 #b 5", "DEFINE", List.of("a", "4"))
        );
    }
    @ParameterizedTest
    @MethodSource("parseArgs")
    void parse(String line, String name, List <String> args)
    {
        CommandParser cmdParser = new CommandParser();
        cmdParser.parse(line);

        Assertions.assertEquals(name, cmdParser.getCommandName());
        Assertions.assertEquals(args, cmdParser.getCommandArgs());
    }
}