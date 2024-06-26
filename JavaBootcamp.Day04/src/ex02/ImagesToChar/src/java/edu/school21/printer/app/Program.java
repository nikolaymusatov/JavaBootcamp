package ImagesToChar.src.java.edu.school21.printer.app;

import ImagesToChar.src.java.edu.school21.printer.logic.*;
import java.io.File;
import com.beust.jcommander.JCommander;

import javax.management.RuntimeErrorException;

public class Program {
  public static void main(String[] args) {
    try {
      if (args.length != 4)
        throw new RuntimeErrorException(null, "Wrong arguments");

      Args arguments = new Args();
      JCommander.newBuilder().addObject(arguments).build().parse(args);

      File file = new File("resourses/it.bmp");
      String blank = arguments.whiteColor;
      String filled = arguments.blackColor;
      Converter.convert(file, blank, filled);
    } catch (Exception e) {
      System.out.println("Error: " + e.getMessage());
      e.printStackTrace();
    }
  }
}
