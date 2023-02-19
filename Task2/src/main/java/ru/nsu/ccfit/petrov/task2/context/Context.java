package ru.nsu.ccfit.petrov.task2.context;

import ru.nsu.ccfit.petrov.task2.context.exception.ArgumentsFormatException;
import ru.nsu.ccfit.petrov.task2.context.exception.EmptyStackException;
import ru.nsu.ccfit.petrov.task2.context.exception.VariableNameException;
import ru.nsu.ccfit.petrov.task2.context.exception.VariableOverwritingException;
import ru.nsu.ccfit.petrov.task2.util.Variable;

import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Stack;

public class Context
    implements Closeable
{
    private final Stack<Double> values;
    private final HashMap<String, Double> variables;
    private final OutputStream out;

    public Context(OutputStream out)
    {
        values = new Stack<>();
        variables = new HashMap<>();
        this.out = out;
    }

    public OutputStream getOut()
    {
        return out;
    }

    public void pushStackValue(Object value)
    {
        try
        {
            values.push(Double.valueOf(value.toString()));
        }
        catch (NumberFormatException |
               NullPointerException ex)
        {
            throw new ArgumentsFormatException();
        }
    }

    public Double peekStackValue()
    {
        try
        {
            return values.peek();
        }
        catch (java.util.EmptyStackException ex)
        {
            throw new EmptyStackException();
        }
    }

    public Double popStackValue()
    {
        try
        {
            return values.pop();
        }
        catch (java.util.EmptyStackException ex)
        {
            throw new EmptyStackException();
        }
    }

    public void addVariable(Object name, Object value)
    {
        if (name == null || !Variable.isCorrectVariableName(name.toString()))
            throw new VariableNameException();

        if (variables.containsKey(name.toString()))
            throw new VariableOverwritingException();

        try
        {
            variables.put(name.toString(),
                          Double.valueOf(value.toString()));
        }
        catch (NumberFormatException |
               NullPointerException ex)
        {
            throw new ArgumentsFormatException();
        }
    }

    public Double getVariable(String name)
    {
        return variables.get(name);
    }

    @Override
    public void close()
            throws IOException
    {
        if (out != null)
            out.close();
    }
}
