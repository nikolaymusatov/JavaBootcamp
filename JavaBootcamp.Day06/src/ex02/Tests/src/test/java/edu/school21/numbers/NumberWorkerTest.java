package edu.school21.numbers;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class NumberWorkerTest
{
    @ParameterizedTest
    @ValueSource(ints = {5, 11, 71})
    public void isPrimeForPrimes(int number) {
        NumberWorker numberWorker = new NumberWorker();
        assertTrue(numberWorker.isPrime(number));
    }
    
    @ParameterizedTest
    @ValueSource(ints = {44, 6, 30, 100})
    public void isPrimeForNotPrimes(int number) {
        NumberWorker numberWorker = new NumberWorker();
        assertFalse(numberWorker.isPrime(number));
    }
    
    @ParameterizedTest
    @ValueSource(ints = {0, 1, -10})
    public void isPrimeForIncorrectNumbers(int number) {
        NumberWorker numberWorker = new NumberWorker();
        assertThrows(IllegalNumberException.class, () -> {
                numberWorker.isPrime(number);
        });
    }
    
    @ParameterizedTest
    @CsvFileSource(resources = "/data.csv")
    public void digitsSumCheck(int number, int expectedSum) {
        NumberWorker numberWorker = new NumberWorker();
        assertEquals(expectedSum, numberWorker.digitsSum(number));
    }
}
