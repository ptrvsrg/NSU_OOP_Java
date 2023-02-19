package ru.nsu.ccfit.petrov.task2;

import org.apache.log4j.Logger;
import ru.nsu.ccfit.petrov.task2.command.Command;

import java.io.*;

public class Calculator
    implements Closeable
{
    private static final Logger logger = Logger.getRootLogger();
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
        // Create calculator context
        logger.info("Create calculator context");
        Context context = new Context(out);

        // Create parser of lines with commands
        logger.info("Create parser of lines with commands");
        CommandParser cmdParser = new CommandParser();

        // Create command creator
        logger.info("Create command creator");
        CommandCreator cmdCreator = new CommandCreator();

        // Start reading lines with commands from input stream
        logger.info("Start reading lines with commands from input stream");
        try (BufferedReader buffIn = new BufferedReader(new InputStreamReader(in)))
        {
            String line;
            while ((line = buffIn.readLine()) != null)
            {
                // Parse read line (Extract command name and arguments)
                logger.info("Parse read line");
                cmdParser.parse(line);

                // Empty line or comment
                if (cmdParser.getCommandName() == null)
                    continue;

                // Create command
                logger.info("Create command \"" + cmdParser.getCommandName() + "\"");
                Command cmd = cmdCreator.create(cmdParser.getCommandName());

                // Command is not found
                if (cmd == null)
                {
                    logger.warn("Command \"" + cmdParser.getCommandName() + "\" is not found");
                    continue;
                }

                // Run command
                logger.info("Launch command \"" + cmdParser.getCommandName() + "\"");
                try
                {
                    cmd.run(cmdParser.getArgs(), context);
                }
                catch (Exception ex)
                {
                    logger.warn("", ex);
                }
            }
        }
        catch (IOException ex)
        {
            logger.error("", ex);
        }
    }

    @Override
    public void close()
            throws IOException
    {
        close(in);
        close(out);
    }

    private void close(Closeable obj)
            throws IOException
    {
        if (obj != null)
            obj.close();
    }
}
