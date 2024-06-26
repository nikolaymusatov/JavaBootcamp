package edu.school21.numbers;

import edu.school21.exeptions.IllegalNumberException;

public class NumberWorker
{
    public boolean isPrime(int number) {
        if (number < 2) {
            throw new IllegalNumberException();
        }
        boolean res = true;
        int counter = 1;
        for (long i = 2; i < Math.sqrt(number); i++) {
            counter++;
            if (number % i == 0)
                res = false;
        }
        return res;
    }
    
    public int digitsSum(int number) {
        int sum = 0;
        while (number > 0) {
            sum += number % 10;
            number /= 10;
        }
        return sum;
    }
}
