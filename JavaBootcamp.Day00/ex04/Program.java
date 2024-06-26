package ex04;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Program {
  public static void main(String[] args) {
    int[][] data = new int[255][2];
    int[][] res = new int[10][2];
    String[][] stats = new String[12][10];

    inputData(data);
    int maxRep = findMax(data);
    sortData(data, res);
    setStats(stats, res, maxRep);
    printStats(stats);
  }

  public static void inputData(int[][] data) {
    Scanner scan = new Scanner(System.in);
    String inputLine = scan.nextLine();
    scan.close();
    for (int i = 0; i < inputLine.length(); i++) {
      data[inputLine.charAt(i)][0] = inputLine.charAt(i);
      data[inputLine.charAt(i)][1] += 1;
    }
  }

  public static int findMax(int[][] data) {
    int maxRep = 0;
    for (int i = 0; i < 255; i++) {
      if (data[i][1] > maxRep) {
        maxRep = data[i][1];
      }
    }
    return maxRep;
  }

  public static void sortData(int[][] data, int[][] res) {
    int tmp = 0;
    boolean sorted = false;
    Arrays.sort(data, Comparator.comparingInt(arr -> arr[1]));
    for (int i = 0; i < 10; i++) {
      res[i][0] = data[254 - i][0];
      res[i][1] = data[254 - i][1];
    }
    while (!sorted) {
      sorted = true;
      for (int i = 0; i < 9; i++) {
        if (res[i][1] == res[i + 1][1] && res[i][0] > res[i + 1][0]) {
          tmp = res[i][0];
          res[i][0] = res[i + 1][0];
          res[i + 1][0] = tmp;
          sorted = false;
        }
      }
    }
  }

  public static void setStats(String[][] stats, int[][] res, int maxRep) {
    double max = res[0][1];
    for (int i = 0; i < 12; i++) {
      for (int j = 0; j < 10; j++) {
        stats[i][j] = " ";
      }
    }
    for (int i = 0; i < 10; i++) {
      if (res[i][1] > 0) {
        int braces = (int) (res[i][1] / max * 10);
        for (int j = 1; j < 12; j++) {
          if (j <= braces) {
            stats[j][i] = "#";
          } else {
            stats[j][i] = " ";
          }
        }
        stats[0][i] = Character.toString((char) res[i][0]);
        stats[braces + 1][i] = Integer.toString(res[i][1]);
      }
    }
  }

  public static void printStats(String[][] stats) {
    for (int i = 11; i >= 0; i--) {
      for (int j = 0; j < 10; j++) {
        if (stats[i][j].length() > 1) {
          System.out.print(stats[i][j] + "\t");
        } else {
          System.out.print(" " + stats[i][j] + "  ");
        }
      }
      System.out.println("");
    }
  }
}
