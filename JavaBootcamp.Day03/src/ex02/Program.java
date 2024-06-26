package ex02;

import javax.management.RuntimeErrorException;

public class Program {
  public static void main(String[] args) {
    try {
      if (args.length != 2)
        throw new RuntimeErrorException(null, "Wrong argument");

      final int numberOfElements = Integer.parseInt(args[0]);
      final int numberOfThreads = Integer.parseInt(args[1]);
      final int[] array = ArrayGenerator.generateArray(numberOfElements);
      System.out.printf("Total sum = %d\n", Summator.calculate(array, numberOfThreads));
    } catch (Exception e) {
      System.out.println("Error: " + e.getMessage());
    }
  }
}
