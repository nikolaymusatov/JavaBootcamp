package ex02;

import java.util.Random;

public class ArrayGenerator {
  public static int[] generateArray(int size) {
    Random r = new Random();
    int[] array = new int[size];
    for (int i = 0; i < size; i++) {
      array[i] = Math.abs(r.nextInt() % 100);
    }
    return array;
  }
}
