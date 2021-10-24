package be.helha.bdecrement.domain;

import java.util.Random;

public class RandomDecrementNumber implements IRandomDecrementNumber
{
    private final int value;
    private static final int DEFAULT_VALUE = 10;

    /*
     * @PRE value > 0
     */
    private RandomDecrementNumber( int value )
    {
        this.value = value;
    }

    public static RandomDecrementNumber createNew()
    {
        return new RandomDecrementNumber( createRandomValue() );
    }

    @Override
    public int getValue()
    {
        return value;
    }

    @Override
    public IRandomDecrementNumber decrement()
    {
        int valueDecremented = value - 1;
        if( valueDecremented == 0 )
        {
            return new RandomDecrementNumber( createRandomValue() );
        }
        return new RandomDecrementNumber( valueDecremented );
    }

    private static int createRandomValue()
    {
        Random rand = new Random();
        return rand.nextInt( DEFAULT_VALUE ) + 1;
    }
}
