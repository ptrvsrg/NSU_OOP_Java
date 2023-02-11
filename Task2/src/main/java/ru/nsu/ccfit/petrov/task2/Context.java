package ru.nsu.ccfit.petrov.task2;

import java.io.OutputStream;
import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.Stack;

public class Context
{
    private final Stack<Double> calculatingValues;
    private final HashMap<String, Double> variables;
    private OutputStream out;

    public Context()
    {
        calculatingValues = new Stack<>();
        variables = new HashMap<>();
        out = System.out;
    }

    public OutputStream getOut()
    {
        return out;
    }

    public void setOut(OutputStream out)
    {
        this.out = out;
    }

    public void pushCalculatingValue(Double value)
    {
        calculatingValues.push(value);
    }

    public Double peekCalculatingValue()
    {
        try
        {
            return calculatingValues.peek();
        }
        catch (EmptyStackException ex)
        {
            return null;
        }
    }

    public Double popCalculatingValue()
    {
        try
        {
            return calculatingValues.pop();
        }
        catch (EmptyStackException ex)
        {
            return null;
        }
    }

    public void addVariable(String name, Double value)
    {
        variables.put(name, value);
    }

    public Double getVariable(String name)
    {
        return variables.get(name);
    }
}
