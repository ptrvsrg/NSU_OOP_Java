package ru.nsu.fit.oop.task2.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Variable
{
    public static boolean isCorrectVariableName(String name)
    {
        Pattern namePattern = Pattern.compile("^[a-zA-Z]\\w*$");
        Matcher matcher = namePattern.matcher(name);
        return matcher.matches();
    }
}
