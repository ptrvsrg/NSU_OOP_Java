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

    private void addWord(SortedHistogram histogram,
                         StringBuilder word)
    {
        histogram.add(new String(word).toLowerCase());
        word.setLength(0);
        ++wordCount;
    }

    public SortedHistogram getHistogram()
            throws IOException
    {
        SortedHistogram histogram = new SortedHistogram();
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
