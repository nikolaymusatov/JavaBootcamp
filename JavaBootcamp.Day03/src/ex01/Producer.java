package ex01;

public class Producer implements Runnable {
  private DataQueue dataQueue;
  private int qnt;
  private String name;
  private static String[] checkName = new String[] { "Egg", "Hen" };
  private static int currentIndex = 0;

  public Producer(DataQueue dataQueue, int qnt, String name) {
    this.dataQueue = dataQueue;
    this.qnt = qnt;
    this.name = name;
  }

  @Override
  public void run() {
    for (int i = 0; i < qnt; i++) {
      while (!this.name.equals(checkName[currentIndex])) {
        try {
          wait();
        } catch (Exception e) {
        }
      }
      dataQueue.add(this.name);
      currentIndex = (currentIndex == 0 ? 1 : 0);
    }
  }
}
