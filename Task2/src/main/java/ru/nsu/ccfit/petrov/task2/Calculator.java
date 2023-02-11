package ru.nsu.ccfit.petrov.task2;

import ru.nsu.ccfit.petrov.task2.command.Command;

import java.io.*;

public class Calculator
{
    private final InputStream in;
    private final OutputStream out;

    public Calculator(InputStream in,
                      OutputStream out)
    {
        this.in = in;
        this.out = out;
    }

    void run()
    {
        // Create parser of lines with commands
        CommandParser cmdParser = new CommandParser();

        // Create command creator
        CommandCreator cmdCreator = new CommandCreator();

        // Create calculator context
        Context context = new Context();
        context.setOut(out);

        // Start reading lines with commands from input stream
        try (BufferedReader buffIn = new BufferedReader(new InputStreamReader(in)))
        {
            String line;
            while ((line = buffIn.readLine()) != null)
            {
                // Parse line (Extract command name and arguments)
                cmdParser.parse(line);

                // Create command
                Command cmd = cmdCreator.create(cmdParser.getCommandName());
                if (cmd == null)
                    continue;

                // Set command arguments
                cmd.setArgs(cmdParser.getArgs());

                // Launch command
                cmd.run(context);
            }
        }
        catch (IOException ex)
        {
            throw new RuntimeException(ex);
        }
    }
}
