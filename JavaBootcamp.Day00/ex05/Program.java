package ex05;

import java.util.Scanner;

public class Program {
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    String[] students = new String[10];
    String[] lessons = new String[10];
    String[] records = new String[100];
    int studentsQnt = getData(students, scan);
    int lessonsQnt = getData(lessons, scan);
    int recordsQnt = getData(records, scan);
    scan.close();
    students = setStudents(students, studentsQnt);
    lessons = setLessons(lessons, lessonsQnt);
    String[][] schedule = getSchedule(students, lessons, records, recordsQnt);
    printSchedule(schedule);
  }

  public static int getData(String[] data, Scanner scan) {
    String line;
    int counter = 0;
    for (int i = 0; i < data.length; i++) {
      line = scan.nextLine();
      if (line.equals("."))
        break;
      data[i] = line;
      counter++;
    }
    return counter;
  }

  public static String[] setStudents(String[] students, int studentsQnt) {
    String[] res = new String[studentsQnt];
    for (int i = 0; i < studentsQnt; i++) {
      res[i] = students[i];
    }
    return res;
  }

  public static String[] setLessons(String[] lessons, int lessonsQnt) {
    int counter = 0;
    String[] tmp = new String[30];
    String[] september = new String[] {
        "NO", "TU", "WE", "TH", "FR", "SA", "SU",
        "MO", "TU", "WE", "TH", "FR", "SA", "SU",
        "MO", "TU", "WE", "TH", "FR", "SA", "SU",
        "MO", "TU", "WE", "TH", "FR", "SA", "SU",
        "MO", "TU", "WE" };
    for (int i = 0; i < lessonsQnt; i++) {
      String[] words = lessons[i].split(" ");
      for (int d = 1; d <= 30; d++) {
        if (words[1].equals(september[d])) {
          tmp[d - 1] = words[0] + ":00" + " " + september[d] + " " + d;
          counter++;
        }
      }
    }
    String[] res = new String[counter];
    int j = 0;
    for (int i = 0; i < counter; i++) {
      while (j < tmp.length) {
        if (tmp[j] != null) {
          res[i] = tmp[j];
          j++;
          break;
        }
        j++;
      }
    }
    return res;
  }

  public static String[][] getSchedule(String[] students, String[] lessons, String[] records, int recordsQnt) {
    String[][] res = new String[students.length + 1][lessons.length + 1];
    for (int i = 0; i <= students.length; i++)
      for (int j = 0; j <= lessons.length; j++)
        res[i][j] = "";
    for (int i = 1; i <= lessons.length; i++)
      res[0][i] = lessons[i - 1];
    for (int i = 1; i <= students.length; i++)
      res[i][0] = students[i - 1];

    for (int r = 0; r < recordsQnt; r++) {
      int name = 0;
      int lesson = 0;
      String mark;
      String[] srecord = records[r].split(" ");
      for (int i = 0; i < students.length; i++)
        if (students[i].equals(srecord[0])) {
          name = i + 1;
          break;
        }
      for (int i = 0; i < lessons.length; i++) {
        String[] slesson = lessons[i].split(" ");
        if (slesson[2].equals(srecord[2]) && slesson[0].split(":")[0].equals(srecord[1])) {
          lesson = i + 1;
          break;
        }
      }
      if (srecord[3].equals("HERE"))
        mark = "1";
      else
        mark = "-1";
      res[name][lesson] = mark;
    }
    return res;
  }

  public static void printSchedule(String[][] schedule) {
    for (int i = 0; i < schedule.length; i++) {
      for (int j = 0; j < schedule[i].length; j++)
        System.out.printf("%10s|", schedule[i][j]);
      System.out.println();
    }
  }
}
