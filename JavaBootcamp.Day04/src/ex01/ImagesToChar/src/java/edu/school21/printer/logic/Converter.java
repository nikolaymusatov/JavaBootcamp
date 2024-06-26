package ImagesToChar.src.java.edu.school21.printer.logic;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Converter {
  public static void convert(File file, char blank, char filled) throws IOException {
    BufferedImage image = ImageIO.read(file);
    for (int yPixel = 0; yPixel < image.getHeight(); yPixel++) {
      for (int xPixel = 0; xPixel < image.getWidth(); xPixel++) {
        int color = image.getRGB(xPixel, yPixel);
        if (color == Color.BLACK.getRGB())
          System.out.printf("%c", filled);
        else
          System.out.printf("%c", blank);
      }
      System.out.println();
    }
  }
}
