package ex03;

import java.util.LinkedList;
import java.util.Queue;

public class FilesQueue {
  private Queue<String> filesQueue;
  private int maxSize;

  public FilesQueue() {
    this.filesQueue = new LinkedList<String>();
    this.maxSize = 10;
  }

  public synchronized void addFile(String url) {
    while (this.filesQueue.size() == this.maxSize) {
      try {
        wait();
      } catch (Exception e) {
      }
    }
    filesQueue.add(url);
    notify();
  }

  public synchronized String getFile() {
    while (this.filesQueue.size() < 1) {
      try {
        wait();
      } catch (Exception e) {
      }
    }
    String file = filesQueue.poll();
    notify();
    return file;
  }

}
