package edu.school21.numbers;

import edu.school21.exeptions.IllegalNumberException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class NumberWorkerTest
{
    private NumberWorker numberWorker;
    @BeforeEach
    public void init() {
        this.numberWorker = new NumberWorker();
    }
    
    @ParameterizedTest
    @ValueSource(ints = {5, 11, 71})
    public void isPrimeForPrimes(int number) {
        assertTrue(this.numberWorker.isPrime(number));
    }
    
    @ParameterizedTest
    @ValueSource(ints = {44, 6, 30, 100})
    public void isPrimeForNotPrimes(int number) {
        assertFalse(this.numberWorker.isPrime(number));
    }
    
    @ParameterizedTest
    @ValueSource(ints = {0, 1, -10})
    public void isPrimeForIncorrectNumbers(int number) {
        assertThrows(IllegalNumberException.class, () -> {
                this.numberWorker.isPrime(number);
        });
    }
    
    @ParameterizedTest
    @CsvFileSource(resources = "/data.csv")
    public void digitsSumCheck(int number, int expectedSum) {
        assertEquals(expectedSum, this.numberWorker.digitsSum(number));
    }
}
