package ru.nsu.ccfit.petrov.task1;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class WordCollector
{
    private long wordCount = 0;
    private final InputStreamReader in;

    public WordCollector(InputStreamReader inputStreamReader)
    {
        in = inputStreamReader;
    }

    public long getWordCount()
    {
        return wordCount;
    }

    private void addWord(HashMap <String, Long> histogram,
                         StringBuilder word)
    {
        histogram.compute(new String(word).toLowerCase(),
                          (key, value) -> (value == null) ? 1 : value + 1);
        word.setLength(0);
        ++wordCount;
    }

    public HashMap <String, Long> getHistogram()
            throws IOException
    {
        HashMap <String, Long> histogram = new HashMap <>();
        StringBuilder word = new StringBuilder();
        int symCode;

        while ((symCode = in.read()) != -1)
        {
            if (Character.isLetterOrDigit(symCode))
                word.append((char) symCode);
            else if (word.length() != 0)
                addWord(histogram,
                        word);
        }

        if (word.length() != 0)
            addWord(histogram,
                    word);

        return histogram;
    }
}
