package ru.nsu.ccfit.petrov.task2.command;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.nsu.ccfit.petrov.task2.Context;
import ru.nsu.ccfit.petrov.task2.exception.ArgumentsNumberException;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

class PrintTest
{
    private final Context context = new Context();
    private final Print printCmd = new Print();

    @Test
    void printTest()
    {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        context.setOut(out);
        List <String> args = new ArrayList <>();

        context.pushCalculatingValue(1.1);
        Assertions.assertDoesNotThrow(
                () ->
                {
                    printCmd.setArgs(args);
                    printCmd.run(context);
                }
        );
        Assertions.assertEquals(out.toString(),
                                "1.1\n");
    }

    @Test
    void exceptionTest()
    {
        List<String> args = new ArrayList<>(List.of("a"));
        Assertions.assertThrows(ArgumentsNumberException.class,
                                () ->
                                {
                                    printCmd.setArgs(args);
                                    printCmd.run(context);
                                }
        );
    }
}