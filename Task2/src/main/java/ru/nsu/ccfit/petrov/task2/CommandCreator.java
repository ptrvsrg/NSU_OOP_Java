package ru.nsu.ccfit.petrov.task2;

import ru.nsu.ccfit.petrov.task2.command.Command;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class CommandCreator
{
    private final Properties properties;

    public CommandCreator()
    {
        try (InputStream resourceIn = Main.class.getResourceAsStream("config.properties"))
        {
            properties = new Properties();
            properties.load(resourceIn);
        }
        catch (IOException ex)
        {
            throw new RuntimeException(ex);
        }
    }

    public Command create(String commandName)
    {
        try
        {
            String className = properties.getProperty(commandName.toUpperCase());
            return (Command) Class.forName(className).getDeclaredConstructor().newInstance();
        }
        catch (Exception ignored)
        {
            return null;
        }
    }
}
