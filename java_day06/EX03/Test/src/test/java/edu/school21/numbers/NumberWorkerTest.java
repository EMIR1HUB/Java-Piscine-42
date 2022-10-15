package edu.school21.numbers;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;


public class NumberWorkerTest extends Assert {
    private NumberWorker numberWorker;

    @BeforeEach
    void createNumberWorker() {
        numberWorker = new NumberWorker();
    }

    @ParameterizedTest(name = "{index} - {0} is a negative")
    @ValueSource(ints = {0, 1, -200})
    public void isPrimeForIncorrectNumbers(final int number) {
        assertThrows(IllegalNumberException.class, () -> numberWorker.isPrime(number));
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 3, 5, 53, 191})
    public void isPrimeForPrime(int number){
        assertTrue(numberWorker.isPrime(number));
    }

    @ParameterizedTest
    @ValueSource(ints = {4, 8, 24, 265})
    public void isPrimeForNotPrime(int number){
        assertFalse(numberWorker.isPrime(number));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/data.csv", numLinesToSkip = 0)
    public void digitsum(int number, int suma){
        assertEquals(numberWorker.digitsSum(number), suma);
    }
}
