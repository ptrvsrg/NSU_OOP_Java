package ru.nsu.ccfit.petrov.task2.creator;

import ru.nsu.ccfit.petrov.task2.Main;
import ru.nsu.ccfit.petrov.task2.commands.Command;
import ru.nsu.ccfit.petrov.task2.creator.exception.ClassLoaderException;
import ru.nsu.ccfit.petrov.task2.creator.exception.CommandNotCreatedException;
import ru.nsu.ccfit.petrov.task2.creator.exception.ResourceException;

import java.io.InputStream;
import java.util.Properties;

public class CommandCreator
{
    private final Properties properties;

    public CommandCreator()
    {
        ClassLoader classLoader;
        try
        {
            classLoader = Main.class.getClassLoader();
            if (classLoader == null)
                throw new ClassLoaderException();
        }
        catch (SecurityException ex)
        {
            throw new ClassLoaderException();
        }

        try (InputStream resourceIn = classLoader.getResourceAsStream("commands.properties"))
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
        catch (Exception ex)
        {
            throw new CommandNotCreatedException();
        }
    }
}
