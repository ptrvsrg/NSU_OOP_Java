package ru.nsu.ccfit.petrov.task2.context;

import ru.nsu.ccfit.petrov.task2.context.exception.ArgumentsFormatException;
import ru.nsu.ccfit.petrov.task2.context.exception.EmptyStackException;
import ru.nsu.ccfit.petrov.task2.context.exception.VariableNameException;
import ru.nsu.ccfit.petrov.task2.context.exception.VariableOverwritingException;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class {@code Context} contains calculator context (its variables, stack content and output stream for printing results)
 */
public class Context
{
    private final Stack<Double> values;
    private final HashMap<String, Double> variables;
    private final PrintStream out;

    /**
     * Create new Context with output stream {@code out}
     *
     * @param out Output stream for printing results
     */
    public Context(OutputStream out) {
        values = new Stack<>();
        variables = new HashMap<>();
        this.out = new PrintStream(out);
    }

    /**
     * Print object {@code obj} and terminate line in output stream {@code out}
     */
    public void println(Object obj) {
        out.println(obj);
    }

    /**
     * Push stack onto value.
     *
     * @param value value pushed onto stack
     * @throws ArgumentsFormatException value is {@code null} or type value is not {@code Double}
     */
    public void pushStackValue(Object value) {
        try {
            values.push(Double.valueOf(value.toString()));
        }
        catch (NumberFormatException |
               NullPointerException ex) {
            throw new ArgumentsFormatException();
        }
    }

    /**
     * Return without removing at value at top of stack
     *
     * @return value at top of stack
     * @throws EmptyStackException stack is empty
     */
    public Double peekStackValue() {
        try {
            return values.peek();
        }
        catch (java.util.EmptyStackException ex) {
            throw new EmptyStackException();
        }
    }

    /**
     * Remove and return at value at top of stack
     *
     * @return value at top of stack
     * @throws EmptyStackException stack is empty
     */
    public Double popStackValue() {
        try {
            return values.pop();
        }
        catch (java.util.EmptyStackException ex) {
            throw new EmptyStackException();
        }
    }

    /**
     * Determine correctness of variable name
     *
     * @param name potential variable name
     * @return {@code true} if variable name {@code name} is correct, otherwise {@code false}
     */
    public boolean isCorrectVariableName(String name) {
        Pattern namePattern = Pattern.compile("^[a-zA-Z]\\w*$");
        Matcher matcher = namePattern.matcher(name);
        return matcher.matches();
    }

    /**
     * Add variable.
     *
     * @param name  variable name
     * @param value variable value
     * @throws VariableNameException        variable name is null or incorrect
     * @throws VariableOverwritingException overwriting variable
     * @throws ArgumentsFormatException     variable value is null or variable value type is not {@code Double}
     */
    public void addVariable(Object name, Object value) {
        if (name == null || !isCorrectVariableName(name.toString()))
            throw new VariableNameException();

        if (variables.containsKey(name.toString()))
            throw new VariableOverwritingException();

        try {
            variables.put(name.toString(),
                          Double.valueOf(value.toString()));
        }
        catch (NumberFormatException |
               NullPointerException ex) {
            throw new ArgumentsFormatException();
        }
    }

    /**
     * Return value of variable with name {@code name}
     *
     * @param name variable name
     * @return value of variable with name {@code name} or {@code null} if variable with name {@code name} is not found
     */
    public Double getVariable(String name) {
        return variables.get(name);
    }
}
