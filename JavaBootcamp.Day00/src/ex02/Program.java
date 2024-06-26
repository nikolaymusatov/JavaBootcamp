package ex02;

import java.util.Scanner;

public class Program {
  public static void main(String[] args) {
    program();
  }

  public static void program() {
    int counter = 0;
    Scanner scan = new Scanner(System.in);
    long num;
    do {
      num = scan.nextLong();
      if (isPrime(getSum(num)))
        counter++;
    } while (num != 42);
    scan.close();
    System.out.println("Count of coffee-request - " + counter);
  }

  public static int getSum(long num) {
    int sum = 0;
    while (num > 0) {
      sum += num % 10;
      num /= 10;
    }
    return sum;
  }

  public static boolean isPrime(long num) {
    boolean res = true;
    for (long i = 2; i < Math.sqrt(num); i++)
      if (num % i == 0)
        res = false;
    return res;
  }
}
