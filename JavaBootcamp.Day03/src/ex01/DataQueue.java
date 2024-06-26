package ex01;

import java.util.LinkedList;
import java.util.Queue;

public class DataQueue {
  private Queue<String> queue;
  private int maxSize;

  public DataQueue(int maxSize) {
    this.queue = new LinkedList<String>();
    this.maxSize = maxSize;
  }

  public synchronized void add(String item) {
    while (this.queue.size() == this.maxSize) {
      try {
        wait();
      } catch (Exception e) {
      }
    }
    queue.add(item);
    notify();
  }

  public synchronized String get() {
    String item;
    while (this.queue.size() < 1) {
      try {
        wait();
      } catch (Exception e) {
      }
    }
    item = queue.poll();
    notify();
    return item;
  }
}
