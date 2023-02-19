package ru.nsu.ccfit.petrov.task2.context.exception;

/**
 * Thrown to indicate that variable name is already in use, i.e. overwrite attempt has been detected
 */
public class VariableOverwritingException
    extends ContextException
{
    /**
     * Cretae new {@code VariableOverwritingException}.
     */
    public VariableOverwritingException()
    {
        super("Attempting to overwrite a variable");
    }
}
