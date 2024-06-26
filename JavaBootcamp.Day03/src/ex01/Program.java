package ex01;

import javax.management.RuntimeErrorException;

public class Program {
  public static void main(String[] args) {
    try {
      if (args.length != 1)
        throw new RuntimeErrorException(null, "Wrong arguments");

      int qnt = Integer.parseInt(args[0]);
      DataQueue dataQueue = new DataQueue(10);
      Producer Egg = new Producer(dataQueue, qnt, "Egg");
      Producer Hen = new Producer(dataQueue, qnt, "Hen");
      Consumer consumer = new Consumer(dataQueue, qnt * 2);
      new Thread(Egg).start();
      new Thread(Hen).start();
      new Thread(consumer).start();
    } catch (Exception e) {
      System.out.println("Error: " + e.getMessage());
    }
  }
}
