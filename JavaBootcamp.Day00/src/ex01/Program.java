package ex01;

import java.util.Scanner;

public class Program {
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    long num = scan.nextLong();
    scan.close();
    if (num < 0 || num == 0 || num == 1) {
      System.err.println("Illegal argument");
      System.exit(-1);
    } else {
      program(num);
    }
  }

  public static void program(long num) {
    boolean res = true;
    int counter = 1;
    for (long i = 2; i < Math.sqrt(num); i++) {
      counter++;
      if (num % i == 0)
        res = false;
    }
    System.out.printf("%b %d", res, counter);
  }
}
