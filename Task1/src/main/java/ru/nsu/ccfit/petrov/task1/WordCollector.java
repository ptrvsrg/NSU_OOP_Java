package ru.nsu.ccfit.petrov.task1;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class WordCollector
{
    private long wordCount = 0;
    private boolean hasHistogram = false;
    private final InputStreamReader textReader;

    public WordCollector(InputStreamReader inputStreamReader)
    {
        textReader = inputStreamReader;
    }

    public long getWordCount()
    {
        if (!hasHistogram)
            throw new UnsupportedOperationException("The operation is not supported. " +
                                                    "Histogram was not received");
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

        while ((symCode = textReader.read()) != -1)
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

        hasHistogram = true;
        return histogram;
    }
}
