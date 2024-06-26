package ImagesToChar.src.java.edu.school21.printer.app;

import ImagesToChar.src.java.edu.school21.printer.logic.*;
import java.io.File;

import javax.management.RuntimeErrorException;

public class Program {
  public static void main(String[] args) {
    try {
      if (args.length != 2)
        throw new RuntimeErrorException(null, "Wrong arguments");

      File file = new File("resourses/it.bmp");
      char blank = args[0].charAt(0);
      char filled = args[1].charAt(0);
      Converter.convert(file, blank, filled);
    } catch (Exception e) {
      System.out.println("Error: " + e.getMessage());
      e.printStackTrace();
    }
  }
}
