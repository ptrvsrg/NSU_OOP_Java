package ru.nsu.fit.oop.task2;

import java.util.HashMap;
import java.util.Stack;

public class Context
{
    private final Stack<Double> calculatingValues;
    private final HashMap<String, Double> variables;

    public Context()
    {
        calculatingValues = new Stack<>();
        variables = new HashMap<>();
    }

    public void pushCalculatingValue(Double value)
    {
        calculatingValues.push(value);
    }

    public Double popCalculatingValue()
    {
        return calculatingValues.pop();
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
