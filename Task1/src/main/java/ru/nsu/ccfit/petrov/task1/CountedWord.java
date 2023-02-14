package ru.nsu.ccfit.petrov.task1;

public class CountedWord
{
    private final String word;
    private Long count;

    public CountedWord(String word,
                       Long count)
    {
        this.word = word;
        this.count = count;
    }

    public String getWord()
    {
        return word;
    }

    public Long getCount()
    {
        return count;
    }

    public void setCount(Long count)
    {
        this.count = count;
    }
}
