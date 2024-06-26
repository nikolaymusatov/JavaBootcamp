package ex00;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class Program {
  public static void main(String[] args) {
    try {
      HashMap<String, int[]> signaturesMap = parseFile("signatures.txt");
      checkFile(signaturesMap);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  private static HashMap<String, int[]> parseFile(String path) throws IOException {
    FileReader fr = new FileReader(path);
    BufferedReader br = new BufferedReader(fr);
    String line = null;
    HashMap<String, int[]> signaturesMap = new HashMap<String, int[]>();
    while ((line = br.readLine()) != null) {
      String[] splittedLine = line.split(", ");
      String[] splittedNumbers = splittedLine[1].split(" ");
      int[] numbers = new int[splittedNumbers.length];
      for (int i = 0; i < numbers.length; i++) {
        numbers[i] = Integer.parseInt(splittedNumbers[i], 16);
      }
      signaturesMap.put(splittedLine[0], numbers);
    }
    br.close();
    fr.close();
    return signaturesMap;
  }

  private static void checkFile(HashMap<String, int[]> signaturesMap) throws IOException {
    Scanner scan = new Scanner(System.in);
    FileOutputStream fos = new FileOutputStream("result.txt");
    System.out.print("Enter file path: ");
    String filePath = scan.nextLine();
    while (!filePath.equals("42")) {
      try {
        int numbers[] = parseFileType(filePath);
        String type = findType(numbers, signaturesMap);
        if (type.equals("notFound")) {
          System.out.println("UNDEFINED");
        } else {
          fos.write((type + "\n").getBytes());
          System.out.println("PROCESSED");
        }
      } catch (Exception e) {
        System.out.println(e.getMessage());
      }
      System.out.print("Enter file path: ");
      filePath = scan.nextLine();
    }
    fos.close();
    scan.close();
  }

  private static int[] parseFileType(String path) throws IOException {
    int numbers[] = new int[10];
    FileInputStream fin = new FileInputStream(path);
    for (int i = 0; i < 10; i++) {
      int num = fin.read();
      numbers[i] = num;
    }
    fin.close();
    return numbers;
  }

  private static String findType(int numbers[], HashMap<String, int[]> signaturesMap) {
    String type = "notFound";
    for (String key : signaturesMap.keySet()) {
      int[] tmp = new int[signaturesMap.get(key).length];
      for (int i = 0; i < tmp.length; i++)
        tmp[i] = numbers[i];
      if (compareNumbers(signaturesMap.get(key), tmp)) {
        type = key;
        break;
      }
    }
    return type;
  }

  private static boolean compareNumbers(int[] nums1, int[] nums2) {
    boolean res = true;
    for (int i = 0; i < nums1.length; i++) {
      if (nums1[i] != nums2[i]) {
        res = false;
        break;
      }
    }
    return res;
  }
}