package ImagesToChar.src.java.edu.school21.printer.app;

import com.beust.jcommander.Parameter;

public class Args {
  @Parameter(names = { "--white", "-w" })
  String whiteColor;
  @Parameter(names = { "--black", "-b" })
  String blackColor;
}
