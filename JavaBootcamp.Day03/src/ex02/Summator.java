package ex02;

import java.util.ArrayList;
import java.util.List;

public class Summator implements Runnable {
  private int id;
  private int[] array;
  private int lowLimit;
  private int highLimit;
  private int sumPerThread;
  private static int result;
  private static int qntOfThread;

  private Summator(int[] array, int lowLimit, int highLimit) {
    this.id = ++qntOfThread;
    this.array = array;
    this.lowLimit = lowLimit;
    this.highLimit = highLimit;
    this.sumPerThread = 0;
  }

  public static int calculate(int[] array, int numberOfThreads) {
    result = 0;
    qntOfThread = 0;
    int elementsPerThread = array.length / numberOfThreads;
    int elementsForLastThread = array.length - (elementsPerThread * (numberOfThreads - 1));
    List<Thread> threadList = new ArrayList<>();
    for (int i = 0; i < numberOfThreads; i++) {
      if (i == numberOfThreads - 1) {
        Thread thread = new Thread(new Summator(array, i * elementsPerThread, i * elementsPerThread +
            elementsForLastThread - 1));
        threadList.add(thread);
      } else {
        Thread thread = new Thread(new Summator(array, i * elementsPerThread, (i + 1) *
            elementsPerThread - 1));
        threadList.add(thread);
      }
    }
    threadList.forEach(Thread::start);
    threadList.forEach(t -> {
      try {
        t.join();
      } catch (InterruptedException e) {
      }
    });
    return result;
  }

  @Override
  public void run() {
    for (int i = lowLimit; i <= highLimit; i++) {
      this.sumPerThread += array[i];
    }
    System.out.printf("Thread %d, from %d to %d sum = %d\n", this.id, this.lowLimit, this.highLimit, this.sumPerThread);
    result += this.sumPerThread;
  }
}
