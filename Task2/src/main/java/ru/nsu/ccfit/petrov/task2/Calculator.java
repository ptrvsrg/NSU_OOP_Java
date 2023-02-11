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
        CommandParser cmdParser = new CommandParser();
        CommandCreator cmdCreator = new CommandCreator();

        Context context = new Context();
        context.setOut(out);

        try (BufferedReader buffIn = new BufferedReader(new InputStreamReader(in)))
        {
            String line;
            while ((line = buffIn.readLine()) != null)
            {
                cmdParser.parse(line);

                Command cmd = cmdCreator.create(cmdParser.getCommandName());
                if (cmd == null)
                    continue;

                cmd.setArgs(cmdParser.getArgs());
                cmd.run(context);
            }
        }
        catch (IOException ex)
        {
            throw new RuntimeException(ex);
        }
    }
}
