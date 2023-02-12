package ru.nsu.ccfit.petrov.task2;

import ru.nsu.ccfit.petrov.task2.command.Command;
import ru.nsu.ccfit.petrov.task2.exception.ResourceException;

import java.io.InputStream;
import java.util.Properties;

public class CommandCreator
{
    private final Properties properties;

    public CommandCreator()
    {
        try (InputStream resourceIn = Main.class
                                          .getClassLoader()
                                          .getResourceAsStream("config.properties"))
        {
            // Load properties from config file
            properties = new Properties();
            properties.load(resourceIn);
        }
        catch (Exception ex)
        {
            throw new ResourceException();
        }
    }

    public Command create(String commandName)
    {
        try
        {
            // Get class name by command name from properties
            String className = properties.getProperty(commandName.toUpperCase());
            return (Command) Class.forName(className)
                                  .getDeclaredConstructor()
                                  .newInstance();
        }
        catch (Exception ignored)
        {
            // Unrecognized command
            return null;
        }
    }
}
