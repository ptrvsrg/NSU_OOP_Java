package ptrvsrg.task1;

import org.apache.commons.cli.ParseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class ClParserTest
{
    private static Stream <Arguments> getInputArgs()
    {
        return Stream.of(Arguments.of(new String[]{"--input", "path", "--output", "path.csv"},
                                      "path",
                                      "path.csv",
                                      false),
                         Arguments.of(new String[]{"--output", "path.csv"},
                                      "",
                                      "path.csv",
                                      true),
                         Arguments.of(new String[]{"--input", "path"},
                                      "path",
                                      "",
                                      true),
                         Arguments.of(new String[]{"--input", "path", "--output", "path.csv", "--config", "path.json"},
                                      "path",
                                      "path.csv",
                                      true),
                         Arguments.of(new String[]{"--input", "path", "--output", "path.csv", "--help"},
                                      "path",
                                      "path.csv",
                                      false));
    }

    @ParameterizedTest
    @MethodSource("getInputArgs")
    void getInputOutput(String[] args,
                        String expectedInput,
                        String expectedOutput,
                        boolean exception)
    {
        try
        {
            ClParser clParser = new ClParser(args);
            Assertions.assertEquals(clParser.getInput(),
                                    expectedInput,
                                    "Incorrect input file");
            Assertions.assertEquals(clParser.getOutput(),
                                    expectedOutput,
                                    "Incorrect output file");
        }
        catch (ParseException ex)
        {
            if (!exception)
                Assertions.fail();
        }
    }

    @Test
    void printHelp()
    {
        Assertions.assertDoesNotThrow(() ->
                                      {new ClParser(new String[]{"--help"});});
        Assertions.assertDoesNotThrow(() ->
                                      {new ClParser(new String[]{"--help", "--mode", "dark"});});
    }
}