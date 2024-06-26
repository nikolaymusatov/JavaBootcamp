package ex03;

import java.util.Scanner;

public class Program {
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    int graphData = 0;
    String line = "";
    short weekNumber = 1;
    boolean status = true;
    while (weekNumber <= 18) {
      System.out.println("Week " + weekNumber);
      line = scan.nextLine();
      if (line.equals("42"))
        break;
      status = checkMarks(line);
      if (status) {
        graphData += findMin(line) * Math.pow(10, weekNumber - 1);
      } else {
        System.err.println("Invalid argument");
        System.exit(-1);
      }
      weekNumber++;
    }
    scan.close();
    showGraph(graphData);
  }

  public static boolean checkMarks(String marks) {
    if (marks.length() != 9)
      return false;
    for (int i = 0; i < marks.length(); i++) {
      if (marks.charAt(i) == ' ')
        continue;
      if (marks.charAt(i) > '9' || marks.charAt(i) < '1')
        return false;
    }
    return true;
  }

  public static int findMin(String marks) {
    char min = '9';
    for (int i = 0; i < marks.length(); i++) {
      if (marks.charAt(i) == ' ')
        continue;
      if (marks.charAt(i) < min)
        min = marks.charAt(i);
    }
    return Character.getNumericValue(min);
  }

  public static void showGraph(int graphData) {
    int counter = 1;
    while (graphData > 0) {
      System.out.println("Week " + counter + " " + "=".repeat(graphData % 10) + '>');
      counter++;
      graphData /= 10;
    }
  }
}
