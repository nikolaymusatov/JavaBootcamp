package ex01;

public class Consumer implements Runnable {
  private DataQueue dataQueue;
  private int counter;

  public Consumer(DataQueue dataQueue, int counter) {
    this.dataQueue = dataQueue;
    this.counter = counter;
  }

  @Override
  public void run() {
    int i = 0;
    while (i < counter) {
      System.out.println(dataQueue.get());
      i++;
    }
  }
}
