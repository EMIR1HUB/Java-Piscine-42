package edu.school21.numbers;

public class NumberWorker {
    public boolean isPrime(int number) {
        if (number < 2) {
            throw new IllegalNumberException();
        }
        double s = Math.sqrt(number);
        for (int i = 2; i <= s; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    public int digitsSum(int number) {
        int suma = 0;
        for (; number > 0; number /= 10) {
            suma += number % 10;
        }
        return suma;
    }
}
