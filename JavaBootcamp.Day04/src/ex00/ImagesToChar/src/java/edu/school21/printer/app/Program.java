package ImagesToChar.src.java.edu.school21.printer.app;

import ImagesToChar.src.java.edu.school21.printer.logic.*;
import java.io.File;
import javax.management.RuntimeErrorException;

public class Program {
  public static void main(String[] args) {
    try {
      if (args.length != 3)
        throw new RuntimeErrorException(null, "Wrong arguments");

      File file = new File(args[0]);
      char blank = args[1].charAt(0);
      char filled = args[2].charAt(0);
      Converter.convert(file, blank, filled);
    } catch (Exception e) {
      System.out.println("Error: " + e.getMessage());
    }
  }
}
