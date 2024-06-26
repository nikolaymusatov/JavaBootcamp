package ex00;

import javax.management.RuntimeErrorException;

public class Program {
  public static void main(String[] args) {
    try {
      if (args.length != 1)
        throw new RuntimeErrorException(null, "Wrong arguments");

      MyThread Egg = new MyThread("Egg", Integer.parseInt(args[0]));
      MyThread Hen = new MyThread("Hen", Integer.parseInt(args[0]));

      Egg.start();
      Hen.start();
      startHuman(Integer.parseInt(args[0]));
    } catch (Exception e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

  public static void startHuman(int repeat) {
    for (int i = 0; i < repeat; i++) {
      System.out.println("Human");
    }
  }
}