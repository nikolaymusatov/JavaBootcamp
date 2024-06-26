package ex00;

public class MyThread extends Thread {
  public MyThread(String name, int repeat) {
    this.repeat = repeat;
    this.name = name;
  }

  @Override
  public void run() {
    for (int i = 0; i < repeat; i++) {
      System.out.println(name);
    }
  }

  private int repeat;
  private String name;
}
